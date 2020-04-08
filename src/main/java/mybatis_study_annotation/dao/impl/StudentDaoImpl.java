package mybatis_study_annotation.dao.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public int deleteStudent(int studId) {
		return sqlSession.delete(namespace + ".deleteStudent", studId);
	}

	@Override
	public List<Student> selectStudentByAllForResults() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForResults");
	}

	@Override
	public List<Map<String, Object>> selectStudentByAllForResultsMap() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForResultsMap");
	}

	@Override
	public List<Student> selectStudentByAllForMapper() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForMapper");
	}

	@Override
	public List<Student> selectStudentByAllForResultMapExt() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForResultMapExt");
	}

	@Override
	public List<Student> selectStudentByAllForResultMapExtXML() {
		return sqlSession.selectList(namespace + ".selectStudentByAllForResultMapExtXML");
	}

	@Override
	public Student selectStudentOneToOne(int studId) {
		return sqlSession.selectOne(namespace + ".selectStudentOneToOne", studId);
	}

	@Override
	public int insertEnumStudent(Student student) {
		return sqlSession.insert(namespace + ".insertEnumStudent", student);
	}

	@Override
	public Student selectAllStudentByParam(String name, String email) {
		return sqlSession.getMapper(StudentDao.class).selectAllStudentByParam(name, email);
	}

	@Override
	public Student selectAllStudentByStudent(Student student) {
		return sqlSession.selectOne(namespace + ".selectAllStudentByStudent", student);
	}

	@Override
	public Student selectAllStudentByMap(Map<String, String> map) {
		return sqlSession.selectOne(namespace + ".selectAllStudentByMap", map);
	}

}
