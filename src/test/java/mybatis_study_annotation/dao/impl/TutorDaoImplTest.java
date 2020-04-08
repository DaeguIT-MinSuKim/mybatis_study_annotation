package mybatis_study_annotation.dao.impl;

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

}
