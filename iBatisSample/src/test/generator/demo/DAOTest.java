package test.generator.demo;

import java.sql.SQLException;
import java.util.List;

import test.generator.dao.person.AddressDAOImpl;
import test.generator.model.person.Address;
import test.generator.model.person.AddressExample;
import test.generator.model.person.AddressExample.Criteria;

public class DAOTest {
	public static void main(String[] args){
		AddressDAOImpl dao = new AddressDAOImpl();
		dao.setSqlMapClient(SessionManager.getSqlMapClient());
		
//		Address addr;
		try {
//			addr = dao.selectByPrimaryKey(2);
//			System.out.println(addr.getAddressline1());
			
			AddressExample example = new AddressExample();
			Criteria criteria = example.createCriteria();
			
//			criteria.andAddressidEqualTo(3);
			
			criteria.andCityLike("%the%");
			
			List<Address> resultList = dao.selectByExample(example);
			
			for(Address addr : resultList){
				System.out.println(addr.getCity());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
		}
		
	}
}
