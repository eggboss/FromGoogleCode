package test.generator.demo;

import java.sql.SQLException;

import test.generator.model.person.Address;

import com.ibatis.sqlmap.client.SqlMapSession;

public class GeneratorTest {
	public static void main(String[] args){
		
		SqlMapSession sqlSession = SessionManager.getSqlMapSession();
		
		Address addr;
		try {
			Address param = new Address();
			
			param.setAddressid(2);
			addr = (Address)sqlSession.queryForObject("Person_Address.ibatorgenerated_selectByPrimaryKey", param);
			System.out.println(addr.getAddressline1());
			System.out.println(addr.getCity());
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sqlSession.close();
		}
	}
}
