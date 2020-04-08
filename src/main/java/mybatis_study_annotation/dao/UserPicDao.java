package mybatis_study_annotation.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import mybatis_study_annotation.dto.UserPic;

public interface UserPicDao {
    @Insert("insert into user_pics(id, name, pic, bio) values (#{id}, #{name}, #{pic}, #{bio})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUserPic(UserPic userPic);

    @Results(id = "userPicResult", 
          value = { 
              @Result(id = true, column = "id", property = "id"),
              @Result(column = "name", property = "name"), 
              @Result(column = "pic", property = "pic"), 
              @Result(column = "bio", property = "bio") 
          }
    )
    @Select("select id, name, pic, bio from user_pics where ID=#{id}")
    UserPic getUserPic(int id);

    @Delete("delete from user_pics where ID=#{id}")
    int deleteUserPic(int id);
}
