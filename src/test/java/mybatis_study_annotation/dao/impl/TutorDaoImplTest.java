package mybatis_study_annotation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study_annotation.AbstractTest;
import mybatis_study_annotation.dto.Tutor;
import mybatis_study_annotation.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorDaoImplTest extends AbstractTest {
	private static TutorDaoImpl dao;
    private static SqlSession sqlSession;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao = TutorDaoImpl.getInstance();
        sqlSession = MyBatisSqlSessionFactory.openSession(true);
        dao.setSqlSession(sqlSession);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dao = null;
        sqlSession.close();
    }
    
    @Test
    public void test01selectTutorById() {
       log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");

       Tutor selTutor1 = dao.selectTutorById(1);
       log.trace(selTutor1.toString());
       Assert.assertNotNull(selTutor1);
       
       Tutor selTutor2 = dao.selectTutorById(10);
       Assert.assertNull(selTutor2);
    }

    @Test
    public void test02SelectTutorByTutorId() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(1);
        Tutor selTutor = dao.selectTutorByTutorId(tutor);

        Assert.assertNotNull(selTutor);
        log.trace(selTutor.toString());
    }

    @Test
    public void test03SelectAllTutorsProv() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Tutor> lists = dao.selectAllTutorsProv();
        Assert.assertNotNull(lists);
        for(Tutor t : lists) log.trace(t.toString());
    }

    @Test
    public void test04SelectTutorProv() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Map<String, Object> map = null;
        List<Tutor> lists = dao.selectTutorProv(map);
        for(Tutor t : lists) log.trace(t.toString());

        map = new HashMap<>();
        map.put("tutorId", 1);

        lists = dao.selectTutorProv(map);
        Assert.assertNotNull(lists);
        for(Tutor t : lists) log.trace(t.toString());
    }

    @Test
    public void test05SelectTutorByJoinProv() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Map<String, Object> map = null;
        List<Tutor> lists = dao.selectTutorByJoinProv(map);
        for(Tutor t : lists) log.trace(t.toString());

        map = new HashMap<>();
        map.put("tutorId", 1);
        lists = dao.selectTutorByJoinProv(map);
        for(Tutor t : lists) log.trace(t.toString());

        map.put("addrId", 1);
        lists = dao.selectTutorByJoinProv(map);
        Assert.assertNotNull(lists);
        for(Tutor t : lists) log.trace(t.toString());
    } 

    @Test
    public void test06InsertTutor(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        Tutor tutor = new Tutor();
        tutor.setTutorId(5);
        tutor.setName("홍");
        tutor.setEmail("hong@test.co.kr");
           
        int res = dao.insertTutor(tutor);
        Assert.assertEquals(1, res);
    }    

    @Test
    public void test07UpdateTutor(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        Tutor tutor = new Tutor();
        tutor.setTutorId(5);
        tutor.setName("홍5");
        tutor.setEmail("hong5@test.co.kr");
            
        int res = dao.updateTutor(tutor);
        Assert.assertEquals(1, res);
    }

}
