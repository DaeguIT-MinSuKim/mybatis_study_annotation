package mybatis_study_annotation.dao;

import org.apache.ibatis.annotations.Select;

import mybatis_study_annotation.dto.Address;

public interface AddressDao {
	@Select("SELECT ADDR_ID AS ADDRID, STREET, CITY, STATE, ZIP, COUNTRY FROM ADDRESSES WHERE ADDR_ID=#{id}")
	Address selectAddressById(int id);
}
