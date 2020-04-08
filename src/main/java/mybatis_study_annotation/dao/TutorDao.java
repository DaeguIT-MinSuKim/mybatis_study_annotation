package mybatis_study_annotation.dao;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import mybatis_study_annotation.dto.Tutor;

public interface TutorDao {
	@Select("select tutor_id, name as tutor_name, email, addr_id from tutors where tutor_id=#{tutorId}")
	@Results(id = "TutorResult", value = { 
			@Result(id = true, column = "tutor_id", property = "tutorId"),
			@Result(column = "tutor_name", property = "name"), 
			@Result(column = "email", property = "email"),
			@Result(property = "address", column = "addr_id", one = @One(select = "mybatis_study_annotation.dao.AddressDao.selectAddressById")),
			@Result(property = "courses", column = "tutor_id", many = @Many(select = "mybatis_study_annotation.dao.CourseDao.selectCoursesByTutorId")) })
	Tutor selectTutorById(int tutorId);

	@Select("select t.tutor_id, t.name as tutor_name, email, a.addr_id, street, city, state, zip, country, course_id, "
			+ "c.name, description, start_date, end_date " 
			+ "from tutors t left join addresses a on t.addr_id=a.addr_id left join courses c on t.tutor_id=c.tutor_id " 
			+ "where t.tutor_id=#{tutorId}")
	@ResultMap("mappers.TutorMapper.TutorResult")
	Tutor selectTutorByTutorId(Tutor tutor);
}
