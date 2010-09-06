package test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionManager {
	static SqlSessionFactory sqlMapper = null;
	static private String resource = "Configuration.xml";
	
	static SqlSessionFactory getSqlMapper(){
		if(sqlMapper==null){
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sqlMapper;
	}
	
}
