package mybatis_study_annotation.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_annotation.dao.TutorDao;
import mybatis_study_annotation.dto.Tutor;

public class TutorDaoImpl implements TutorDao {
	private String namespace = "mybatis_study_annotation.dao.TutorDao";
	private static final TutorDaoImpl instance = new TutorDaoImpl();
	private SqlSession sqlSession;

	public static TutorDaoImpl getInstance() {
		return instance;
	}

	private TutorDaoImpl() {
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Tutor selectTutorById(int tutorId) {
		return sqlSession.selectOne(namespace + ".selectTutorById", tutorId);
	}

	@Override
	public Tutor selectTutorByTutorId(Tutor tutor) {
		return sqlSession.selectOne(namespace + ".selectTutorByTutorId", tutor);
	}

	@Override
	public List<Tutor> selectAllTutorsProv() {
		return sqlSession.selectList(namespace + ".selectAllTutorsProv");
	}

	@Override
	public List<Tutor> selectTutorProv(Map<String, Object> map) {
	    return sqlSession.selectList(namespace + ".selectTutorProv", map);
	}

	@Override
	public List<Tutor> selectTutorByJoinProv(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectTutorByJoinProv", map);
	}

	@Override
	public int insertTutor(Tutor tutor) {
		return sqlSession.insert(namespace + ".insertTutor", tutor);
	}

	@Override
	public int updateTutor(Tutor tutor) {
		return sqlSession.update(namespace + ".updateTutor", tutor);
	}

}
