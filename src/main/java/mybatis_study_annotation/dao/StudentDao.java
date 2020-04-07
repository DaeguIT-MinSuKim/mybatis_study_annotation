package mybatis_study_annotation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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

}
