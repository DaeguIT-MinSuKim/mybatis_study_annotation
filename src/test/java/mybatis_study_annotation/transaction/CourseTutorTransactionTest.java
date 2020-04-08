package mybatis_study_annotation.transaction;

import java.util.Date;

import org.junit.Test;

import mybatis_study_annotation.AbstractTest;
import mybatis_study_annotation.dto.Course;
import mybatis_study_annotation.dto.Tutor;

public class CourseTutorTransactionTest extends AbstractTest {
	private CourseTutorTransaction service = CourseTutorTransaction.getInstance();

    @Test(expected = RuntimeException.class)
    public void test1JoinNewTutorAndCourseFailTutor() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(4);
        tutor.setName("kim");
        tutor.setEmail("test@test.co.kr");
        Course course = getCourse(8, "Python", "Programming", new Date(), new Date(), 4);
        service.joinNewTutorAndCourse(tutor, course);
    }

    @Test(expected = RuntimeException.class)
    public void test2JoinNewTutorAndCourseFailCourse() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(6);
        tutor.setName("kim");
        tutor.setEmail("test@test.co.kr");
        Course course = getCourse(2, "Python", "Programming", new Date(), new Date(), 4);
        service.joinNewTutorAndCourse(tutor, course);
    }

    @Test
    public void test3JoinNewTutorAndCourseSuccess() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(6);
        tutor.setName("kim");
        tutor.setEmail("test@test.co.kr");

        Course course = getCourse(8, "Python", "Programming", new Date(), new Date(), 6);

        service.joinNewTutorAndCourse(tutor, course);
    }

    @Test(expected=RuntimeException.class)
    public void test4RemoveTutorAndCourseFailTutor() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(10);
        Course course = new Course();
        course.setCourseId(8);
        service.removeTutorAndCourse(tutor, course);
    }

    @Test(expected=RuntimeException.class)
    public void test5RemoveTutorAndCourseFailCourse() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(6);
        Course course = new Course();
        course.setCourseId(10);
        service.removeTutorAndCourse(tutor, course);
    }

    @Test
    public void test6RemoveTutorAndCourseSuccess() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(6);
        Course course = new Course();
        course.setCourseId(8);
        service.removeTutorAndCourse(tutor, course);
    }

	private Course getCourse(int courseId, String name, String description, Date startDate, Date endDate, int tutorId) {
		Course course =  new Course();
		course.setCourseId(courseId);
		course.setName(name);
		course.setDescription(description);
		course.setStartDate(startDate);
		course.setEndDate(endDate);
		course.setTutorId(tutorId);
		return course;
	}
}
