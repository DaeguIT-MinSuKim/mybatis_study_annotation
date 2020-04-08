package mybatis_study_annotation.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_annotation.dao.CourseDao;
import mybatis_study_annotation.dto.Course;

public class CourseDaoImpl implements CourseDao {
	private String namespace = "mybatis_study_annotation.dao.CourseDao";
	private static final CourseDaoImpl instance = new CourseDaoImpl();
	private SqlSession sqlSession;

	public static CourseDaoImpl getInstance() {
		return instance;
	}

	private CourseDaoImpl() {}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List<Course> selectCoursesByTutorId(int tutorId) {
        return sqlSession.selectList(namespace + ".selectCoursesByTutorId", tutorId);
	}

	@Override
	public int insertCourse(Course course) {
		return sqlSession.insert(namespace + ".insertCourse", course);
	}

	@Override
	public int deleteCourse(Course course) {
		return sqlSession.delete(namespace + ".deleteCourse", course);
	}

}
