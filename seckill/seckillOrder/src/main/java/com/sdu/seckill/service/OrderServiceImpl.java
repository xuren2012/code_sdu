package com.sdu.seckill.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdu.seckill.dao.OrderDao;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public Map<String, Object> insertOrder(Map<String, Object> orderInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap = orderDao.insertOrder(orderInfo);
        return resultMap;
    }

    @Override
    public Map<String, Object> createOrder(String sku_id, String user_id) {

        Map<String, Object> resultMap = new HashMap<>();

        //1、判断传入参数
        if (sku_id == null || sku_id.equals("")) {
            resultMap.put("result", false);
            resultMap.put("msg", "前端传入数据有误！");
            return resultMap;
        }
        if (user_id == null || user_id.equals("")) {
            resultMap.put("result", false);
            resultMap.put("msg", "会员没有登录！");
            return resultMap;
        }
        String order_id = String.valueOf(System.currentTimeMillis());

        //2、取政策
        String policy = stringRedisTemplate.opsForValue().get("LIMIT_POLICY_" + sku_id);
        if (policy != null && !policy.equals("")) {
            //3、从政策中判断时间
            Map<String, Object> policyMap = JSONObject.parseObject(policy, Map.class);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String now = restTemplate.getForObject("http://seckill-time-server/getTime", String.class);

            try {
                Date begin_time = simpleDateFormat.parse(policyMap.get("begin_time").toString());
                Date end_time = simpleDateFormat.parse(policyMap.get("end_time").toString());
                Date now_time = simpleDateFormat.parse(now);

                if (begin_time.getTime() <= now_time.getTime() && now_time.getTime() <= end_time.getTime()) {
                    //4、redis计数器
                    long limitQuanty = Long.parseLong(policyMap.get("quanty").toString());
                    if (stringRedisTemplate.opsForValue().increment("SKU_QUANTY_" + sku_id, 1) <= limitQuanty) {
                        //5、写入队列及redis
                        /**
                         * tb_order: order_id, total_fee, actual_fee, post_fee, payment_type, user_id, status, create_time
                         * tb_order_detail: order_id, sku_id, num, title, own_spec, price, image, create_time
                         * tb_sku: sku_id, title, images, stock, price, indexes, own_spec
                         */
                        Map<String, Object> orderInfo = new HashMap<>();
                        String sku = stringRedisTemplate.opsForValue().get("SKU_" + sku_id);
                        Map<String, Object> skuMap = JSONObject.parseObject(sku, Map.class);

                        orderInfo.put("order_id", order_id);
                        orderInfo.put("total_fee", skuMap.get("price"));
                        orderInfo.put("actual_fee", policyMap.get("price"));
                        orderInfo.put("post_fee", 0);
                        orderInfo.put("payment_type", 0);
                        orderInfo.put("user_id", user_id);
                        orderInfo.put("status", 1);
                        orderInfo.put("create_time", now);

                        orderInfo.put("sku_id", skuMap.get("sku_id"));
                        orderInfo.put("num", 1);
                        orderInfo.put("title", skuMap.get("title"));
                        orderInfo.put("own_spec", skuMap.get("own_spec"));
                        orderInfo.put("price", policyMap.get("price"));
                        orderInfo.put("image", skuMap.get("images"));

                        try {
                            String order = JSON.toJSONString(orderInfo);
                            amqpTemplate.convertAndSend("order_queue", order);
                            stringRedisTemplate.opsForValue().set("ORDER_" + order_id, order);

                        } catch (Exception e) {
                            resultMap.put("result", false);
                            resultMap.put("msg", "写入队列有误！");
                            return resultMap;
                        }
                    } else {
                        //6、没有通过计数器则返回错误信息，提示商品已售完
                        resultMap.put("result", false);
                        resultMap.put("msg", "商品已售完！");
                        return resultMap;
                    }
                } else {
                    //8、时间有误，返回错误信息，提示活动已结束
                    resultMap.put("result", false);
                    resultMap.put("msg", "活动已过期！");
                    return resultMap;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            //7、取政策不成功时，返回错误信息，提示活动已过期
            resultMap.put("result", false);
            resultMap.put("msg", "活动已经过期！");
            return resultMap;
        }
        //9、返回正常信息
        resultMap.put("order_id", order_id);
        resultMap.put("result", true);
        resultMap.put("msg", "订单已经生成！");
        return resultMap;
    }

    @Override
    public Map<String, Object> getOrder(String order_id) {

        String order = stringRedisTemplate.opsForValue().get("ORDER_" + order_id);

        return JSONObject.parseObject(order, Map.class);
    }

    @Override
    public Map<String, Object> payOrder(String order_id, String sku_id) {

        Map<String, Object> resultMap = new HashMap<>();

        //1、判断参数
        if (order_id == null || order_id.equals("")) {
            resultMap.put("result", false);
            resultMap.put("msg", "前端数据有误！");
            return resultMap;
        }

        if (sku_id == null || sku_id.equals("")) {
            resultMap.put("result", false);
            resultMap.put("msg", "前端数据有误！");
            return resultMap;
        }

        try {
            amqpTemplate.convertAndSend("order_status_queue", order_id);
            amqpTemplate.convertAndSend("storage_queue", sku_id);
        } catch (Exception e) {
            resultMap.put("result", false);
            resultMap.put("msg", "队列没写成功！");
            return resultMap;
        }

        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }

    @Override
    public Map<String, Object> updateOrderStatus(String order_id) {
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = orderDao.updateOrderStatus(order_id);
        if (!result) {
            resultMap.put("result", false);
            resultMap.put("msg", "更新订单状态失败！");
            return resultMap;
        }

        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }
}
