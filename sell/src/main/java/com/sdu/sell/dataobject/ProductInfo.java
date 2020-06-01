package com.sdu.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdu.sell.enums.ProductStatusEnum;
import com.sdu.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 *              商品详情
 * @Author: YTF
 * @Date: 2020/4/22
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    //商品名称
    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品描述
    private String productDescription;

    //商品小图
    private String productIcon;

    //商品状态：0正常，1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    //商品类目编号
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
