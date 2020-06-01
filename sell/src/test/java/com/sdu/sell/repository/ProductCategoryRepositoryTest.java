package com.sdu.sell.repository;

import com.sdu.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        //ProductCategory productCategory = repository.findOne(1);
        ProductCategory productCategory = repository.findById(1).orElse(null);
        System.out.println(productCategory.toString());
    }

    //添加数据
    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("体育服务器");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    //@Transactional   //测试方法中添加事务注解：会使操作完全回滚
    public void saveTest2(){
        ProductCategory productCategory = new ProductCategory("装饰用品", 6);
        ProductCategory result = repository.save(productCategory);
        //使用断言测试是否成功
        //Assert.assertNotNull(result);
        Assert.assertNotEquals(null,result);
    }

    //修改数据
    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("体育用品");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    //使用命名规则查询
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(5,6,7);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }


}