package kk.leech.sample;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kk.leech.db.DBController;

import kk.leech.db.vo.DISTRIBUTOR;

public class SampleServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		String pageNums = request.getParameter("pageNum");
		
		System.out.println("method=" + method);
		System.out.println("pageNums=" + pageNums);
		
		RequestDispatcher dispatcher = null;
		String message = null;
		
		int pageNum = 0;
		
		if(method==null){
			method = "search";
		}
		
		if(pageNums==null){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(pageNums);
		}
		
		if("update".equals(method)){
			String id = request.getParameter("custId");
			goUpdate(request, new Integer(id));
			dispatcher = request.getRequestDispatcher("update.jsp");
		}
		else if("updateAfter".equals(method)){
			if(doUpdate(request)){
				message = "修改成功";
			}else{
				message = "修改失敗";
			}
			pageNum = 1;
			doSearch(request, pageNum);
			message = "修改成功";
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		else if("insert".equals(method)){
			dispatcher = request.getRequestDispatcher("insert.jsp");
		}
		else if("insertAfter".equals(method)){
			if(doInsert(request)){
				message = "新增成功";
			}else{
				message = "新增失敗";
			}
			pageNum = 1;
			doSearch(request, pageNum);
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		else if("delete".equals(method)){
			String id = request.getParameter("custId");
			doDelete(request, new Integer(id));
			pageNum = 1;
			doSearch(request, pageNum);
			message = "刪除成功";
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		else if("search".equals(method)){
			doSearch(request, pageNum);
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("message", message);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void doSearch(HttpServletRequest request, int pageNum){
		DBController dc = new DBController();
		String sql = "SELECT * FROM DISTRIBUTOR ";
		int firstNum = (pageNum-1)*4+1;
		int lastNum = firstNum + 3;
		System.out.println("firstNum=" + firstNum);
		System.out.println("lastNum=" + lastNum);
		List list = dc.doPageSearch(sql, null, DISTRIBUTOR.class, firstNum, lastNum);
		List list2 = dc.doSearch(sql, null, DISTRIBUTOR.class);
		int totalCount = list2.size();
		int totalPage = (totalCount%4==0)?(totalCount/4):((totalCount/4)+1);
		
		request.setAttribute("totalPage", new Integer(totalPage));
		request.setAttribute("list", list);
		System.out.println("size = "+list.size());
	}
	
	private boolean doInsert(HttpServletRequest request){
		String cust_id = request.getParameter("cust_id");
		String password = request.getParameter("password");
		String cust_name = request.getParameter("cust_name");
		String phone = request.getParameter("phone");
		String address = "";
		try {
			address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		DISTRIBUTOR entity = new DISTRIBUTOR();
		entity.setCust_id(new Integer(cust_id));
		entity.setPassword(password);
		entity.setCust_name(cust_name);
		entity.setPhone(new Integer(phone));
		entity.setAddress(address);
		
		DBController dc = new DBController();
		int insertCount = dc.doInsert(entity);
		if(insertCount==1){
			return true;
		}else{
			return false;
		}
	}
	
	private void goUpdate(HttpServletRequest request, Integer id){
		DBController dc = new DBController();
		String sql = "SELECT * FROM DISTRIBUTOR where CUST_ID = ?";
		Object[] conditions = {id};
		List list = dc.doSearch(sql, conditions, DISTRIBUTOR.class);
		request.setAttribute("entity", list.get(0));
	}
	
	private boolean doUpdate(HttpServletRequest request){
		String cust_id = request.getParameter("cust_id");
		String password = request.getParameter("password");
		String cust_name = request.getParameter("cust_name");
		String phone = request.getParameter("phone");
		String address = "";
		try {
			address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		DISTRIBUTOR entity = new DISTRIBUTOR();
		entity.setCust_id(new Integer(cust_id));
		entity.setPassword(password);
		entity.setCust_name(cust_name);
		entity.setPhone(new Integer(phone));
		entity.setAddress(address);
		
		DBController dc = new DBController();
		int insertCount = dc.doUpdate(entity);
		if(insertCount==1){
			return true;
		}else{
			return false;
		}
	}
	
	private void doDelete(HttpServletRequest request, Integer id){
		DBController dc = new DBController();
		DISTRIBUTOR entity = new DISTRIBUTOR();
		entity.setCust_id(id);
		dc.doDelete(entity);
	}
}
