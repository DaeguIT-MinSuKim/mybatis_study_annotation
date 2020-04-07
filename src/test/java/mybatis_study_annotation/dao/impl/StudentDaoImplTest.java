package mybatis_study_annotation.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import mybatis_study_annotation.AbstractTest;
import mybatis_study_annotation.dto.Student;
import mybatis_study_annotation.jdbc.MyBatisSqlSessionFactory;

public class StudentDaoImplTest extends AbstractTest{
	private static StudentDaoImpl dao;
    private static SqlSession sqlSession;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao = StudentDaoImpl.getInstance();
        sqlSession = MyBatisSqlSessionFactory.openSession(true);
        dao.setSqlSession(sqlSession);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dao = null;
        sqlSession.close();
    }
    
	@Test
	public void testSelectStudentByNo() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Student student = new Student();
        student.setStudId(1);
        Student selectStudent = dao.selectStudentByNo(student);
        log.debug(selectStudent.toString());
        Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}

	@Test
	public void testSelectStudentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Student> lists = dao.selectStudentByAll();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
	}

}
