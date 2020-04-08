package mybatis_study_annotation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import mybatis_study_annotation.dao.CourseDao;
import mybatis_study_annotation.dto.Course;
import mybatis_study_annotation.dto.CourseStat;

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

	@Override
	public CourseStat getCourseCountByTutor(int param) {
		return sqlSession.selectOne(namespace + ".getCourseCountByTutor", param);
	}

	@Override
	public CourseStat getCourseCountByTutor2(Map<String, Object> param) {
		return sqlSession.selectOne(namespace + ".getCourseCountByTutor2", param);
	}

	@Override
	public Map<String, Object> getCourseCountByTutor3(Map<String, Object> param) {
		return sqlSession.selectMap(namespace + ".getCourseCountByTutor3", param, "tutor");
	}

	@Override
	public Map<String, Object> getCourseCountByTutor4(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<>();
	    ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {
	        @Override
	        public void handleResult(ResultContext<? extends CourseStat> resultContext) {
	            CourseStat state = resultContext.getResultObject();
	            map.put(state.getTutor(), state.getTotal());
	        }
	    };
        sqlSession.select(namespace + ".getCourseCountByTutor4", param, resultHandler);
        return map;
	}

}
