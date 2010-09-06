package kirk.sample.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class TokenTestAction extends DispatchAction {

	public ActionForward goInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("goInsert !");
		
		saveToken(request);
		System.out.println("saveToken(request);");
		
		return mapping.findForward("goInsert");
	}
	
	public ActionForward doInsert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("doInsert !");
		
		if(!isTokenValid(request)){
			System.out.println("Page refresh !");
		}else{
			System.out.println("Token check ok and do something for insert data !");
			
			resetToken(request);
			System.out.println("resetToken(request);");
		}
		
		return mapping.findForward("showResult");
	}
	
}
