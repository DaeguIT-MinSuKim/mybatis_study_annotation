package mybatis_study_annotation.transaction;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_annotation.dto.Course;
import mybatis_study_annotation.dto.Tutor;
import mybatis_study_annotation.jdbc.MyBatisSqlSessionFactory;

public class CourseTutorTransaction {
	private static final CourseTutorTransaction instance = new CourseTutorTransaction();

    public static CourseTutorTransaction getInstance() {
        return instance;
    }

    private CourseTutorTransaction() {}

    private String namespace_tutor = "mybatis_study_annotation.dao.TutorDao";
    private String namespace_course = "mybatis_study_annotation.dao.CourseDao";

    public void joinNewTutorAndCourse(Tutor tutor, Course course) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(false);
        int res = 0;
        try {
            res += sqlSession.insert(namespace_tutor + ".insertTutor", tutor);
            res += sqlSession.insert(namespace_course + ".insertCourse", course);
            if (res == 2)
                sqlSession.commit();
            else
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

    public void removeTutorAndCourse(Tutor tutor, Course course) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(false);
        int res = 0;
        try {
            res += sqlSession.delete(namespace_course + ".deleteCourse", course);
            res += sqlSession.delete(namespace_tutor + ".deleteTutor", tutor);
            if (res == 2)
                sqlSession.commit();
            else
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

}
