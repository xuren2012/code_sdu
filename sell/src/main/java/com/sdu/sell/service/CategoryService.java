package com.sdu.sell.service;

import com.sdu.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @Description:
 *              类目
 * @Author: YTF
 * @Date: 2020/4/23
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save (ProductCategory productCategory);

}
