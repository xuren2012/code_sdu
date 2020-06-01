package com.sdu.sell.controller;

import com.sdu.sell.VO.ProductInfoVO;
import com.sdu.sell.VO.ProductVO;
import com.sdu.sell.VO.ResultVO;
import com.sdu.sell.dataobject.ProductCategory;
import com.sdu.sell.dataobject.ProductInfo;
import com.sdu.sell.service.CategoryService;
import com.sdu.sell.service.ProductService;
import com.sdu.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 买家商品
 * @Author: YTF
 * @Date: 2020/4/23
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //测试输出数据格式--json格式
        //ResultVO resultVO = new ResultVO();
        //ProductVO productVO = new ProductVO();
        //ProductInfoVO productInfoVO = new ProductInfoVO();
        //productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        //resultVO.setData(Arrays.asList(productVO));
        //resultVO.setCode(0);
        //resultVO.setMsg("成功");
        //return resultVO;

        //1、查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2、查询类目
        //方式一：传统方法
        //List<Integer> categoryTypeList = new ArrayList<>();
        //        for (ProductInfo productInfo : productInfoList) {
        //            categoryTypeList.add(productInfo.getCategoryType());
        //        }

        //方式二：java8新特性，lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3、数据拼接--按输出格式完成
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
                productVO.setProductInfoVOList(productInfoVOList);
                productVOList.add(productVO);
            }
        }
        //方式一：基础代码实现
        //        ResultVO resultVO = new ResultVO();
        //        resultVO.setData(productVOList);
        //        resultVO.setCode(0);
        //        resultVO.setMsg("成功");
        //        return resultVO;
        //方式二：优化代码实现
        return ResultVOUtils.success(productVOList);
    }
}
