package test.generator.demo;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.client.SqlMapSession;

public class SessionManager {
	static SqlMapClient sqlMapClient = null;
	static SqlMapSession sqlMapSession = null;
	static private String resource = "sql-map-config.xml";
	
	static SqlMapClient getSqlMapClient(){
		if(sqlMapClient==null){
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader(resource);
				sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sqlMapClient;
	}
	
	static SqlMapSession getSqlMapSession(){
		if(sqlMapSession==null){
			sqlMapSession = getSqlMapClient().openSession();
		}
		return sqlMapSession;
	}
	
}
