package com.sdu.sell.repository;

import com.sdu.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/22
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    //使用命名规则查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
