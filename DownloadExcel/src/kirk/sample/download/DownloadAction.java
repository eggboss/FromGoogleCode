package kirk.sample.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction.StreamInfo;
/**
 * see
 * http://www.blogjava.net/blueoxygen/archive/2005/12/07/22894.html
 * @author kirk
 *
 */
public abstract class DownloadAction extends Action {
    /**
     * If the <code>getBufferSize()</code> method is not overridden, this is
     * the buffer size that will be used to transfer the data to the servlet
     * output stream.
     */
    protected static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * Returns the information on the file, or other stream, to be downloaded
     * by this action. This method must be implemented by an extending class.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request (if any).
     * @param request  The HTTP request we are processing.
     * @param response The HTTP response we are creating.
     *
     * @return The information for the file to be downloaded.
     *
     * @throws Exception if an exception occurs.
     */
    protected abstract StreamInfo getStreamInfo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception;

    /**
     * Returns the size of the buffer to be used in transferring the data to
     * the servlet output stream. This method may be overridden by an extending
     * class in order to customize the buffer size.
     *
     * @return The size of the transfer buffer, in bytes.
     */
    protected int getBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request (if any).
     * @param request  The HTTP request we are processing.
     * @param response The HTTP response we are creating.
     *
     * @throws Exception if an exception occurs.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        StreamInfo info = getStreamInfo(mapping, form, request, response);
        String contentType = info.getContentType();
        InputStream stream = info.getInputStream();

        try {
            response.setContentType(contentType);
            copy(stream, response.getOutputStream());
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // Tell Struts that we are done with the response.
        return null;
    }

    /**
     * Copy bytes from an <code>InputStream</code> to an
     * <code>OutputStream</code>.
     *
     * @param input  The <code>InputStream</code> to read from.
     * @param output The <code>OutputStream</code> to write to.
     *
     * @return the number of bytes copied
     *
     * @throws IOException In case of an I/O problem
     */
    public int copy(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[getBufferSize()];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
