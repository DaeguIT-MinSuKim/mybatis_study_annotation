package mybatis_study_annotation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mybatis_study_annotation.dto.Student;

public interface StudentDao {
    @Select("select stud_id, name, email, phone, dob from students where stud_id=#{studId}")
    Student selectStudentByNo(Student student);

    @Select("select stud_id, name, email, phone, dob from students")
    List<Student> selectStudentByAll();
    
    @Insert("insert into students (stud_id, name, email, phone, dob) values (#{studId}, #{name}, #{email}, #{phone}, #{dob})")
    int insertStudent(Student student);

    @Insert("INSERT INTO STUDENTS(STUD_ID, NAME, EMAIL, PHONE, DOB) VALUES(#{studId}, #{name}, #{email}, #{phone}, #{dob})")
    @Options(useGeneratedKeys=true, keyProperty="studId")
    int insertStudentAutoInc(Student student);

    @Update("update students set name=#{name}, email=#{email}, phone=#{phone} where stud_id=#{studId}")
    int updateStudent(Student student);

    @Delete("delete from students where stud_id=#{studId}")
    int deleteStudent(int studId);

	@Results(id = "studentResult", value = { 
			@Result(id = true, column = "stud_id", property = "studId"),
			@Result(column = "name", property = "name"), 
			@Result(column = "email", property = "email"),
			@Result(column = "phone", property = "phone"), 
			@Result(column = "dob", property = "dob") })

	@Select("SELECT * FROM STUDENTS")
	List<Student> selectStudentByAllForResults();

	@Results({
	    @Result (id=true, column="stud_id", property="studId"),
	    @Result (column="name", property="name"),
	    @Result (column="email", property="email"),
	    @Result (column="phone", property="phone"),
	    @Result (column="dob", property="dob")        
	})
	@Select("SELECT * FROM STUDENTS")
	List<Map<String,Object>> selectStudentByAllForResultsMap();

    @Select("SELECT stud_id, name, email, phone, dob FROM STUDENTS")
    @ResultMap("mappers.StudentMapper.StudentResult")
    List<Student> selectStudentByAllForMapper();

	@Results(id = "StudentWithAddressResult", value = { 
			@Result(id = true, column = "stud_id", property = "studId"),
			@Result(column = "name", property = "name"), 
			@Result(column = "email", property = "email"),
			@Result(column = "phone", property = "phone"), 
			@Result(column = "dob", property = "dob"),
			@Result(column = "addr_id", property = "address.addrId"),
			@Result(column = "street", property = "address.street"),
			@Result(column = "city", property = "address.city"), 
			@Result(column = "state", property = "address.state"),
			@Result(column = "zip", property = "address.zip"),
			@Result(column = "country", property = "address.country") })
	@Select("select stud_id, name, email, phone, a.addr_id, street, city, state, zip, country from students s join addresses a on s.addr_id=a.addr_id")
	List<Student> selectStudentByAllForResultMapExt();

	@Select("select stud_id, name, email, phone, a.addr_id, street, city, state, zip, country             from students s join addresses a on s.addr_id=a.addr_id")
	@ResultMap("mappers.StudentMapper.StudentWithAddressResult")
	List<Student> selectStudentByAllForResultMapExtXML();

	/* 내포된 결과 매핑을 사용한 일대일 매핑 */
	@Select("select * from students where stud_id=#{studId}")
	@Results({
	    @Result(id=true, column="stud_id", property="studId"),
	    @Result(column="name", property="name"),
	    @Result(column="email", property="email"),
	    @Result(column="addr_id", property="address", 
	               one=@One(select="mybatis_study_annotation.dao.AddressDao.selectAddressById"))
	})
	Student selectStudentOneToOne(int studId);

	/* 매퍼 애너테이션 - enum 타입 */
	@Insert("insert into students(stud_id, name, email, phone, dob, gender) " 
            + "values(#{studId}, #{name}, #{email}, #{phone}, #{dob}, #{gender})")
	@Options(useGeneratedKeys = true, keyProperty = "studId")
	int insertEnumStudent(Student student);

	/* 여러 개의 입력 파라미터 */
	@Select("select stud_id, name, email, phone, dob, gender from students where name=#{param1} and email=#{param2}")
	@Results(id="multiParamResult", value={
	    @Result (id=true, column="stud_id", property="studId"),
	    @Result (column="name", property="name"),
	    @Result (column="email", property="email"),
	    @Result (column="phone", property="phone"),
	    @Result (column="dob", property="dob"),   
	    @Result (column="gender", property="gender")
	})
	Student selectAllStudentByParam(String name, String email);

	@Select("select stud_id, name, email, phone, dob, gender from students where name=#{name} and email=#{email}")
	@ResultMap("mappers.StudentMapper.StudentResult")
	Student selectAllStudentByStudent(Student student);

	@Select("select stud_id, name, email, phone, dob, gender from students where name=#{name} and email=#{email}")
	@ResultMap("mappers.StudentMapper.StudentResult")
	Student selectAllStudentByMap(Map<String, String> map);

	/* 매퍼 애너테이션 - ResultSet 처리방식의 재정의 */
	@Select("select stud_id, name, email, phone, dob from students where stud_id=#{studId}")
    @ResultMap("mappers.StudentMapper.StudentResult")
	Map<Integer, String> selectStudentForMap(int studId);

}
