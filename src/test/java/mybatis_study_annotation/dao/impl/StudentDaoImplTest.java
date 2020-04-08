package mybatis_study_annotation.dao.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study_annotation.AbstractTest;
import mybatis_study_annotation.dto.Gender;
import mybatis_study_annotation.dto.PhoneNumber;
import mybatis_study_annotation.dto.Student;
import mybatis_study_annotation.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void test01SelectStudentByNo() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Student student = new Student();
        student.setStudId(1);
        Student selectStudent = dao.selectStudentByNo(student);
        log.debug(selectStudent.toString());
        Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
	}

	@Test
	public void test02SelectStudentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Student> lists = dao.selectStudentByAll();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
	}

    @Test
    public void test03InsertStudent() {
    	log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);
        Student student = new Student();
        student.setStudId(3);
        student.setName("홍길동3");
        student.setEmail("lee@test.co.kr");
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setDob(newDate.getTime());
        int res = dao.insertStudent(student);
        Assert.assertEquals(1, res);
    }
    
/*  @Test
    public void test04InsertStudentAutoInc() {
    	log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);
        Student student = new Student();
        student.setName("홍길동4");
        student.setEmail("lee@test.co.kr");
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setDob(newDate.getTime());
        int res = dao.insertStudentAutoInc(student);
        Assert.assertEquals(1, res);
    }
*/    

    @Test
    public void test05UpdateStudent() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(2000, 4, 28);
        Student student = new Student();
        student.setStudId(3);
        student.setName("홍길동3");
        student.setEmail("hong@test.co.kr");
        student.setPhone(new PhoneNumber("010-1111-2222"));
        student.setDob(newDate.getTime());
        int res = dao.updateStudent(student);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test06DeleteStudent() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        int res = dao.deleteStudent(3);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test07SelectStudentByAllForResults() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Student> lists = dao.selectStudentByAllForResults();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
    } 

    @Test
    public void test08SelectStudentByAllForResultsMap() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Map<String, Object>> maps = dao.selectStudentByAllForResultsMap();
        Assert.assertNotNull(maps);
        for(Map<String, Object> m : maps) {
            for(Entry<String, Object> e : m.entrySet()) {
                log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
            }
        }
    }

    @Test
    public void test09SelectStudentByAllForMapper() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Student> lists = dao.selectStudentByAllForMapper();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
    } 

    @Test
    public void test10SelectStudentByAllForResultMapExt() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Student> lists = dao.selectStudentByAllForResultMapExt();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
    }

    @Test
    public void test11SelectStudentByAllForResultMapExtXML() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        List<Student> lists = dao.selectStudentByAllForResultMapExtXML();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
    }
    
    @Test
    public void test12SelectStudentOneToOne(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Student student = dao.selectStudentOneToOne(1);
        Assert.assertNotNull(student);
        log.trace(student.toString());
    }

    @Test
    public void test13InsertEnumStudent() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);
        Student student = new Student();
        student.setStudId(3);
        student.setName("test");
        student.setEmail("test@test.co.kr");
        student.setDob(newDate.getTime());
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setGender(Gender.FEMALE);
        int res = dao.insertEnumStudent(student);
        Assert.assertEquals(1, res);
        
        student.setStudId(4);
        student.setName("test4");
        student.setEmail("test4@test.co.kr");
        student.setDob(newDate.getTime());
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setGender(Gender.MALE);
        int res1 = dao.insertEnumStudent(student);
        Assert.assertEquals(1, res1);
        
        dao.deleteStudent(3);
        dao.deleteStudent(4);
    }

}
