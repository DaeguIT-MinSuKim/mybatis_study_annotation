package mybatis_study_annotation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

}
