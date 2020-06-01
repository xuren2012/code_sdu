package com.stu.springdata.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
public class Query2Test {

//    @Autowired
//    private ArticleDao articleDao;
//
//    @Test
//    public void testFindByTitle(){
//        List<Article> articles = articleDao.findByTitle("Spring boot6");
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByTitleLike(){
//        List<Article> articles = articleDao.findByTitleLike("%Spring%");
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByTitleAndAuthor(){
//        List<Article> articles = articleDao.findByTitleAndAuthor("Spring boot", "YTF");
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByAidBetween(){
//        List<Article> articles = articleDao.findByAidBetween(8, 16);
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByAidLessThan(){
//        List<Article> articles = articleDao.findByAidLessThan(10);
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByAidIn(){
//        List<Integer> list = new ArrayList();
//        list.add(8);
//        list.add(9);
//        list.add(10);
//        List<Article> articles = articleDao.findByAidIn(list);
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByCreateTimeAfter(){
//        List<Article> articles = articleDao.findByCreateTimeAfter(new Date());
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testFindByCreateTimeBefore(){
//        List<Article> articles = articleDao.findByCreateTimeBefore(new Date());
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }


}
