package mybatis_study_annotation.providers;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

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

}
