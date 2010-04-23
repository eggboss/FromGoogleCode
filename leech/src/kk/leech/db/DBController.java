package kk.leech.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kk.leech.util.EntitiesUtil;

import org.apache.log4j.Logger;

public class DBController {
	static private Logger logger = Logger.getLogger(DBController.class);
	
	static public final int SELECT = 0;
	static public final int INSERT = 1;
	static public final int UPDATE = 2;
	static public final int DELETE = 3;
	
	public Connection getConnection(){
		Connection cnn = null;
		return cnn;
	}
	
	/**
	 * 搜尋
	 * @param sql
	 * @param conditions
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public List doSearch(String sql, Object[] conditions, Class cls){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try{
			conn = JNDIConnection.getConnection();
			stmt = conn.prepareStatement(sql);
			setObject(stmt, conditions);
			rset = stmt.executeQuery();
			return EntitiesUtil.convert(rset, cls);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 分頁搜尋
	 * @param sql
	 * @param conditions
	 * @param cls
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List doPageSearch(String sql,Object[] conditions, Class cls, int start, int end){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try{
			conn = JNDIConnection.getConnection();
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select * from ( select rownum row_num , row_.* from ( ")
			         .append(sql)
			         .append(") row_ ) WHERE row_num between " + start + " AND " + end);
			stmt = conn.prepareStatement(sqlBuffer.toString());
			setObject(stmt, conditions);
			rset = stmt.executeQuery();
			return EntitiesUtil.convert(rset, cls);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 搜尋
	 * @param entity
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public List doSearch(DBEntities entity, Class cls){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try{
			conn = JNDIConnection.getConnection();
			Map map = entity.convert();
			String sql = genSelectSql(entity.getTablename(), map);
			stmt = conn.prepareStatement(sql);
			setObject(stmt, map);
			rset = stmt.executeQuery();
			return EntitiesUtil.convert(rset, cls);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 分頁搜尋
	 * @param entity
	 * @param cls
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List doPageSearch(DBEntities entity, Class cls, int start, int end){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try{
			conn = JNDIConnection.getConnection();
			Map map = entity.convert();
			String sql = genPageSelectSql(entity.getTablename(), map, start, end);
			stmt = conn.prepareStatement(sql);
			setObject(stmt, map);
			rset = stmt.executeQuery();
			return EntitiesUtil.convert(rset, cls);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private String genSelectSql(String tableName, Map map){
		Set set = map.keySet();
		int keysize = set.size();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(tableName);
		if(keysize>0){
			sql.append(" WHERE ");
			Iterator itr = set.iterator();
			for(;itr.hasNext();){
				String key = (String)itr.next();
				sql.append(key + " = ? ");
				if(itr.hasNext()){
					sql.append("AND ");
				}
			}
		}
		logger.info("sql=[ " + sql.toString() + " ]");
		return sql.toString();
	}
	
	private String genInsertSql(String tableName, Map map){
		Set set = map.keySet();
		int keysize = set.size();
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(tableName);
		if(keysize>0){
			sql.append(" ( ");
			Iterator itr = set.iterator();
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			for(;itr.hasNext();){
				String key = (String)itr.next();
				sb1.append(key);
				if(itr.hasNext()){
					sb1.append(",");
				}
				
				sb2.append(" ?");
				if(itr.hasNext()){
					sb2.append(",");
				}
			}
			sql.append(sb1.toString()).append(" ) ");
			sql.append("VALUES (").append(sb2).append(") ");
		}
		logger.info("sql=[ " + sql.toString() + " ]");
		return sql.toString();
	}
	
	private String genDeleteSql(DBEntities entity, Map map){
		Set set = map.keySet();
		String tableName = entity.getTablename();
		int keysize = set.size();
		String[] pks = entity.getKeys();
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ").append(tableName);
		if(keysize>0){
//			sql.append(" WHERE ");
			for(int i=0; i<pks.length; i++){
				sql.append(" WHERE ").append(pks[i].toLowerCase()).append("= ? ");//.append(map.get(pks[i].toLowerCase()));
				if(i<(pks.length-1)){
					sql.append(" AND ");
				}
			}
		}
		logger.info("sql=[ " + sql.toString() + " ]");
		return sql.toString();
	}
	
	private String genUpdateSql(DBEntities entity, Map map){
		Set set = map.keySet();
		String tableName = entity.getTablename();
		int keysize = set.size();
		String[] pks = entity.getKeys();
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ").append(tableName);
		if(keysize>0){
			sql.append(" SET ");
			Iterator itr = set.iterator();
			boolean isFirstValue = true;
			for(;itr.hasNext();){
				String key = (String)itr.next();
				boolean isPK = false;
				for(int i=0; i<pks.length; i++){
					if(key.equalsIgnoreCase(pks[i])){
						isPK = true;
						break;
					}
				}
				if(!isPK){
					if(!isFirstValue){
						sql.append(", ");
					}
					sql.append(key + " = ? ");
					isFirstValue = false;
				}
			}
			for(int i=0; i<pks.length; i++){
				sql.append(" WHERE ").append(pks[i].toLowerCase()).append(" = ? ");//.append(map.get(pks[i].toLowerCase()));
				if(i<(pks.length-1)){
					sql.append(" AND ");
				}
			}
		}
		logger.info("sql=[ " + sql.toString() + " ]");
		return sql.toString();
	}
	
	private String genPageSelectSql(String tableName, Map map, int start, int end){
		Set set = map.keySet();
		int keysize = set.size();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM( ");
		sql.append("SELECT * FROM ").append(tableName);
		if(keysize>0){
			sql.append(" WHERE ");
			Iterator itr = set.iterator();
			for(;itr.hasNext();){
				String key = (String)itr.next();
				sql.append(key + " = ? ");
				if(itr.hasNext()){
					sql.append("AND ");
				}
			}
		}
		sql.append(") WHERE rownum between " + start + " AND " + end);
		logger.info("sql=[ " + sql.toString() + " ]");
		return sql.toString();
	}
	
	private void setObject(PreparedStatement stmt, Map map) throws Exception{
		if(stmt!=null && !map.isEmpty()){
			Iterator itr = map.values().iterator();
			int index = 1;
			StringBuffer params = new StringBuffer("params=[ ");
			for(;itr.hasNext();){
				Object obj = itr.next();
				params.append(obj.toString());
				stmt.setObject(index, obj);
				index++;
				if(itr.hasNext()){
					params.append(",");
				}else{
					params.append(" ]");
				}
			}
			logger.info(params);
		}
	}
	
	private void setObjectByKey(PreparedStatement stmt, Map map, String[] pks) throws Exception{
		if(stmt!=null && !map.isEmpty() && pks!=null){
			int pksLength = pks.length;
			StringBuffer params = new StringBuffer("params=[ ");
			for(int i=0; i<pksLength; i++){
				params.append(map.get(pks[i].toLowerCase()));
				stmt.setObject(i+1, map.get(pks[i].toLowerCase()));
				if((i+1)<pksLength){
					params.append(",");
				}else{
					params.append(" ]");
				}
			}
			logger.info(params);
		}
	}
	
	private void setObjectForUpdate(PreparedStatement stmt, Map map, String[] pks) throws Exception{
		if(stmt!=null && !map.isEmpty() && pks!=null){
			int pksLength = pks.length;
			StringBuffer params = new StringBuffer("params=[ ");
			
			Iterator keyItr = map.keySet().iterator();
			Iterator valueItr = map.values().iterator();
			int index = 1;
			for(;keyItr.hasNext();){
				String key = (String)keyItr.next();
				Object value = valueItr.next();
				boolean isPK = false;
				for(int i=0; i<pksLength; i++){
					if(pks[i].toLowerCase().equals(key)){
						isPK = true;
						break;
					}
				}
				if(!isPK){
					params.append(value);
					params.append(",");
					stmt.setObject(index, value);
					index++;
				}
			}
			
			for(int i=0; i<pksLength; i++){
				params.append(map.get(pks[i].toLowerCase()));
				stmt.setObject(index+i, map.get(pks[i].toLowerCase()));
				if((i+1)<pksLength){
					params.append(",");
				}else{
					params.append(" ]");
				}
			}
			logger.info(params);
		}
	}
	
	private void setObject(PreparedStatement stmt, Object[] conditions) throws Exception{
		if(stmt!=null && conditions!=null){
			int conditionsCount = conditions.length;
			StringBuffer params = new StringBuffer("params=[ ");
			for(int i=0; i<conditionsCount; i++){
				params.append(conditions[i].toString());
				stmt.setObject((i + 1), conditions[i]);
				if(i!=(conditionsCount-1)){
					params.append(",");
				}else{
					params.append(" ]");
				}
			}
			logger.info(params);
		}
	}
	
	/**
	 * 使用PrepareStatement的方式insert、update、delete
	 * 
	 * @author Kirk Hsu
	 * @since Oct 20, 2008
	 *
	 * @param pSql PrepareStatement的sql
	 * @param params 參數陣列
	 * @return
	 */
	public int doSaveOrUpdate(String pSql, Object[] params){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JNDIConnection.getConnection();
			stmt = conn.prepareStatement(pSql);
			int paramsLength = params.length;
			for(int i=0; i<paramsLength; i++){
				stmt.setObject(i+1, params[i]);
			}
			return stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 使物件方式執行inert
	 * 
	 * @author Kirk Hsu
	 * @since Oct 20, 2008
	 *
	 * @param entity 要新增的物件
	 * @return
	 */
	public int doInsert(DBEntities entity){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JNDIConnection.getConnection();
			Map map = entity.convert();
			String sql = genInsertSql(entity.getTablename(), map);
			stmt = conn.prepareStatement(sql);
			setObject(stmt, map);
			return stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 使物件方式執行update
	 * 
	 * @author Kirk Hsu
	 * @since Oct 20, 2008
	 *
	 * @param entity
	 * @return
	 */
	public int doUpdate(DBEntities entity){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JNDIConnection.getConnection();
			Map map = entity.convert();
			String sql = genUpdateSql(entity, map);
			stmt = conn.prepareStatement(sql);
			setObjectForUpdate(stmt, map, entity.getKeys());
			return stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 使物件方式執行delete
	 * 
	 * @author Kirk Hsu
	 * @since Oct 20, 2008
	 *
	 * @param entity
	 * @return
	 */
	public int doDelete(DBEntities entity){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JNDIConnection.getConnection();
			Map map = entity.convert();
			String[] pks = entity.getKeys();
			String sql = genDeleteSql(entity, map);
			stmt = conn.prepareStatement(sql);
			setObjectByKey(stmt, map, pks);
			return stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void main(String[] args) throws Exception{
//		TestBean tb = new TestBean();
//		DBEntities entity = tb;
//		System.out.println(entity.getTableName());
//		tb.setA("111");
//		tb.setC("333");
//		Map map = tb.convert();
//		Set set = map.keySet();
//		Iterator itr = set.iterator();
//		for(;itr.hasNext();){
//			String key = (String)itr.next();
//			System.out.print(key + " - ");
//			System.out.println(map.get(key));
//		}
		
//		RMS213 rms213 = new RMS213();
//		rms213.setBrand("VW");
//		rms213.setVehid(new Integer(37246));
		
//		Map map = rms213.convert();
//		Set set = map.keySet();
//		Iterator itr = set.iterator();
//		for(;itr.hasNext();){
//			String key = (String)itr.next();
//			System.out.print(key + " - ");
//			System.out.println(map.get(key));
//		}
		
		DBController dc = new DBController();
		
		// Search 1
		/*
		String sql = "SELECT * FROM RMS213 WHERE brand = ? ";
		Object[] conditions = {"VW"};
		List list = dc.doPageSearch(sql, conditions, RMS213.class, 1, 10);
		for(int i=0; i<list.size(); i++){
			RMS213 rms213vo = (RMS213)list.get(i);
			System.out.println(rms213vo.getVehid());
		}
		System.out.println("size = "+list.size());
		*/
		
		// Search 2
		/*
		DBOP op = new DBOP();
		op.setId(new Integer(5));
		List list = dc.doSearch(op, DBOP.class);
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
		*/
		
		// Insert
		/*
		DBOP op = new DBOP();
		op.setId(new Integer(5));
		op.setName("aaaaa");
		op.setAccount("aaaa");
		op.setAge(new Integer(3));
		System.out.println(dc.doInsert(op));
		*/
		
		// Update
		/*
		DBOP op = new DBOP();
		op.setId(new Integer(5));
		op.setName("ccc");
		op.setAccount("bcccbb");
		op.setAge(new Integer(3));
		System.out.println(dc.doUpdate(op));
		*/

		// Delete
		/*
		DBOP op4 = new DBOP();
		op4.setId(new Integer(6));
		dc.doDelete(op4);
		*/
		
		// doSaveOrUpdate
		// test update
//		String sql = "update DBOP set name= ? , age= ? where id = ?";
//		Object[] params = {"kirk", new Integer(30), new Integer(5)};
//		System.out.println(dc.doSaveOrUpdate(sql, params));
	}
}
