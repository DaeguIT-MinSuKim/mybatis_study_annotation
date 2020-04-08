package mybatis_study_annotation.providers;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import mybatis_study_annotation.dto.Tutor;

public class TutorProvider {
	public String selectAllTutorProv(){    
        return new SQL(){{
            SELECT("tutor_id, name, email, phone, dob");
            FROM("tutors");
        }}.toString();
    }

    public String selectTutorProv(Map<String, Object> map){
        return new SQL(){
            {
                SELECT("tutor_id, name, email, phone, dob");
                FROM("tutors");
                if (map != null){
                    WHERE("tutor_Id=#{tutorId}");
                }
            }
        }.toString();
    }
    
    public String selectTutorByJoinProv(Map<String, Object> map){
        return new SQL(){{
            SELECT("t.tutor_id, t.name as tutor_name, email");
            SELECT("a.addr_id, street, city, state, zip, country");
            SELECT("course_id, c.name as course_name, description, start_date, end_date");
            FROM("TUTORS t");
            LEFT_OUTER_JOIN("addresses a on t.addr_id=a.addr_id");
            LEFT_OUTER_JOIN("courses c on t.tutor_id=c.tutor_id");
            if (map != null){
                if (map.get("tutorId")!=null){
                    WHERE("t.tutor_id=#{tutorId}");
                }
                if (map.get("addrId")!=null){
                    WHERE("t.addr_id=#{addrId}");
                }
            }
        }}.toString();
    }

	public String insertTutor(Tutor tutor) {
	    return new SQL() {
	        {
	            INSERT_INTO("TUTORS");
	            if (tutor.getTutorId() != 0) {
	                VALUES("tutor_id", "#{tutorId}");
	            }
	            if (tutor.getName() != null) {
	                VALUES("NAME", "#{name}");
	            }
	            if (tutor.getEmail() != null) {
	                VALUES("EMAIL", "#{email}");
	            }
	        }
	    }.toString();
	}
	
	public String updateTutor(Tutor tutor) {
	    return new SQL() {
	       {
	            UPDATE("TUTORS");
	            if (tutor.getName() != null) {
	                SET("NAME=#{name}");
	            }
	            if (tutor.getEmail() != null) {
	                SET("EMAIL=#{email}");
	            }
	            WHERE("TUTOR_ID=#{tutorId}");
	        }
	    }.toString();
	}

	public String deleteTutor(Tutor tutor) {
	    return new SQL() {
	        {
	            DELETE_FROM("TUTORS");
	            WHERE("TUTOR_ID=#{tutorId}");
	        }
	    }.toString();
	}

}
