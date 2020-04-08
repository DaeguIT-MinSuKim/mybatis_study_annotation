package mybatis_study_annotation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import mybatis_study_annotation.dto.Course;

public interface CourseDao {
	@Select("select * from courses where tutor_id=#{tutorId}")
    @Results({
        @Result(id=true, column="course_id", property="courseId"),
        @Result(column="name", property="name"),
        @Result(column="description", property="description"),
        @Result(column="start_date", property="startDate"),
        @Result(column="end_date", property="endDate")
    })
    List<Course> selectCoursesByTutorId(int tutorId);
	
	/* Transaction */
	@Insert("insert into courses (course_id, name, description, start_date, end_date, tutor_id) values (#{courseId}, #{name}, #{description}, #{startDate}, #{endDate}, #{tutorId})")
	int insertCourse(Course course);

	@Delete("delete from courses where course_id = #{courseId}")
	int deleteCourse(Course course);

}
