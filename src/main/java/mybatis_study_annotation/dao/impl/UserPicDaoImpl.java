package mybatis_study_annotation.dao.impl;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_annotation.dao.UserPicDao;
import mybatis_study_annotation.dto.UserPic;

public class UserPicDaoImpl implements UserPicDao {
	private String namespace = "mybatis_study_annotation.dao.UserPicDao";
	private static final UserPicDaoImpl instance = new UserPicDaoImpl();
	private SqlSession sqlSession;

	public static UserPicDaoImpl getInstance() {
		return instance;
	}

	private UserPicDaoImpl() {
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int insertUserPic(UserPic userPic) {
		return sqlSession.insert(namespace + ".insertUserPic", userPic);
	}

	@Override
	public UserPic getUserPic(int id) {
		return sqlSession.selectOne(namespace + ".getUserPic", id);
	}

	@Override
	public int deleteUserPic(int id) {
		return sqlSession.delete(namespace + ".deleteUserPic", id);
	}

}
