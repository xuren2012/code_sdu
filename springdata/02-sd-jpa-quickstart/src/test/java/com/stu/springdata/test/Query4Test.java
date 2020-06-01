package com.stu.springdata.test;

import com.sdu.springdata.dao.ArticleDao;
import com.sdu.springdata.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class Query4Test {

    @Autowired
    private ArticleDao articleDao;

    //需求：按标题、作者进行查询，以不为空的属性作为查询条件
    @Test
    public void testFindAll(){
        //模拟前台传入数据
        String title = "";
        String author = "";
        List<Article> articles = articleDao.findAll(new Specification<Article>() {

            /**
             *
             * @param root 实体对象，可以通过其获取属性值
             * @param cq   用于生成SQL语句
             * @param cb   用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(title)){
                    //拼接查询条件
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }

                if(!StringUtils.isEmpty(author)){
                    //拼接查询条件
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }
                return cb.and(list.toArray(new Predicate[]{}));
            }
        });

        for (Article article : articles) {
            System.out.println(article);
        }

    }

    @Test
    public void testFindAllWithPage(){

        String title = "";
        String author = "";

        //分页
        Pageable pageable = PageRequest.of(0,3);
        Page<Article> page = articleDao.findAll(new Specification<Article>() {
            /**
             *
             * @param root
             * @param cq
             * @param cb
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                ArrayList<Object> list = new ArrayList<>();
                if(!StringUtils.isEmpty(title)){
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }
                if(!StringUtils.isEmpty(author)){
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }

                return cb.and(list.toArray(new Predicate[]{}));
            }
        }, pageable);
        for (Article article : page.getContent()) {
            System.out.println(article);
        }

    }

    @Test
    public void testFindAllWithPageAndSort(){

        String title = "";
        String author = "";

        Pageable pageable = PageRequest.of(0,3,Sort.by(Sort.Order.desc("aid")));

        Page<Article> page = articleDao.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(title)){
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }

                if(!StringUtils.isEmpty(author)){
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }

                return cb.and(list.toArray(new Predicate[]{}));
            }
        }, pageable);
        for (Article article : page.getContent()) {
            System.out.println(article);
        }
    }


}
