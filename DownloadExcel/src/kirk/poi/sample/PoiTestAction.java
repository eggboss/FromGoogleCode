package kirk.poi.sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PoiTestAction extends Action {
	protected static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        InputStream stream = new PoiTest().getAllUserAsExcel2();

        String fileName = "downloadExcelSample.xls";
        try {
            response.setContentType("application/octet-stream; charset=UTF-8");
        	response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
            copy(stream, response.getOutputStream());
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // Tell Struts that we are done with the response.
        return null;
	}
	
	public int copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[getBufferSize()];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
	
	protected int getBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }
	
}
