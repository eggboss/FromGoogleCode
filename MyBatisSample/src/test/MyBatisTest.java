package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.mapper.Address;



public class MyBatisTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		SqlSessionFactory sqlMapper = SessionManager.getSqlMapper();
		SqlSession sqlSession = sqlMapper.openSession();
		
//		Address addr = (Address)sqlSession.selectOne( "test.mapper.AddressMapper.selectAddress", 2); 
//		System.out.println(addr.getAddressLine1());
		
		
		Map paramMap = new HashMap();
		String[] citys = {"London","Bothell"};
		
		List cityList = new ArrayList();
		cityList.add("London");
		cityList.add("Bothell");
		
		paramMap.put("id", "2");
//		paramMap.put("city", "%thell%");
//		paramMap.put("city", cityList);
		paramMap.put("city", citys);
		
		List<Address> requestList = sqlSession.selectList("test.mapper.AddressMapper.queryAddress", paramMap);
		
		for(Address addr : requestList){
			System.out.println(addr.getAddressLine1());
		}

		
		
/*
		try{
			Address addr = new Address();
			addr.setAddressID("2");
			addr.setAddressLine2("small shit");
			
			sqlSession.update("test.mapper.AddressMapper.updateAddress", addr);
			
			addr.setAddressID(null);
			sqlSession.update("test.mapper.AddressMapper.updateAddress", addr);
			
			sqlSession.commit(); // 一定要commit才會寫入
		}catch(Exception ex){
			ex.printStackTrace();
			sqlSession.rollback();
			System.out.println("rollback !");
		}
*/
		
		sqlSession.close();
	}
	
}
