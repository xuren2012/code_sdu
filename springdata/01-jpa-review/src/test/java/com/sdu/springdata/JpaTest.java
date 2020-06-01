package com.sdu.springdata;

import com.sdu.springdata.domain.Article;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaTest {
    @Test
    public void testSave(){
        //创建对象
        Article article = new Article();
        article.setTitle("测试文章标题");
        article.setAuthor("李思");
        article.setCreateTime(new Date());

        //保存
        //1、创建持久化管理器工厂
        //2、创建持久化管理器
        //3、得到事务，开启事务
        //4、运算
        //5、提交事务
        //6、释放资源
        String persistenceUnitName = "jpa01";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);

        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(article);

        transaction.commit();
        entityManager.close();
    }

    //查询
    @Test
    public void testFindByAid(){

        /**
         * 1、创建持久化管理器工厂
         * 2、创建持久化管理器
         * 3、获取事务并开启
         * 4、操作
         * 5、事务提交
         * 6、关闭资源
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Article article = entityManager.find(Article.class, 1);

        System.out.println(article);

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testUpdate(){
        /**
         * 1、创建持久化管理器工厂
         * 2、创建持久化管理器
         * 3、获取事务并开启
         * 4、操作
         * 5、提交事务
         * 6、释放资源
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Article article = entityManager.find(Article.class, 1);
        //修改
        article.setAuthor("李四");
        //entityManager.merge(article);
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testDelete(){
        /**
         * 1、创建持久化管理器工厂
         * 2、创建持久化管事器
         * 3、获取事务并开启
         * 4、操作
         * 5、提交事务
         * 6、释放资源
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa01");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Article article = entityManager.find(Article.class, 3);
        entityManager.remove(article);

        transaction.commit();
        entityManager.close();
    }
}
