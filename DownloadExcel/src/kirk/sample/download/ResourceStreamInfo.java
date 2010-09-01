package kirk.sample.download;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

public class ResourceStreamInfo implements StreamInfo {

    /**
     * The content type for this stream.
     */
    private String contentType;

    /**
     * The servlet context for the resource to be downloaded.
     */
    private ServletContext context;

    /**
     * The path to the resource to be downloaded.
     */
    private String path;

    /**
     * Constructs an instance of this class, based on the supplied
     * parameters.
     *
     * @param contentType The content type of the file.
     * @param context     The servlet context for the resource.
     * @param path        The path to the resource to be downloaded.
     */
    public ResourceStreamInfo(String contentType, ServletContext context,
            String path) {
        this.contentType = contentType;
        this.context = context;
        this.path = path;
    }

    /**
     * Returns the content type of the stream to be downloaded.
     *
     * @return The content type of the stream.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Returns an input stream on the resource to be downloaded. This stream
     * will be closed by the <code>DownloadAction</code>.
     *
     * @return The input stream for the resource to be downloaded.
     */
    public InputStream getInputStream() throws IOException {
        return context.getResourceAsStream(path);
    }

}
