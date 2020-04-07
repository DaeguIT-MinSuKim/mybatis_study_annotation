package mybatis_study_annotation.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_annotation.dao.StudentDao;
import mybatis_study_annotation.dto.Student;

public class StudentDaoImpl implements StudentDao {
	private String namespace = "mybatis_study_annotation.dao.StudentDao";
	private static final StudentDaoImpl instance = new StudentDaoImpl();
	private SqlSession sqlSession;

	public static StudentDaoImpl getInstance() {
		return instance;
	}

	private StudentDaoImpl() {
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Student selectStudentByNo(Student student) {
        return sqlSession.selectOne(namespace + ".selectStudentByNo", student);
	}

	@Override
	public List<Student> selectStudentByAll() {
		return sqlSession.selectList(namespace + ".selectStudentByAll");
	}

	@Override
	public int insertStudent(Student student) {
		return sqlSession.insert(namespace + ".insertStudent", student);
	}

	@Override
	public int insertStudentAutoInc(Student student) {
		return sqlSession.insert(namespace + ".insertStudentAutoInc", student);
	}

	@Override
	public int updateStudent(Student student) {
		return sqlSession.update(namespace + ".updateStudent", student);
	}

}
