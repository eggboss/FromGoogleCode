package kirk.sample.download;

import java.io.IOException;
import java.io.InputStream;

public interface StreamInfo {
    /**
     * Returns the content type of the stream to be downloaded.
     *
     * @return The content type of the stream.
     */
    public abstract String getContentType();

    /**
     * Returns an input stream on the content to be downloaded. This stream
     * will be closed by the <code>DownloadAction</code>.
     *
     * @return The input stream for the content to be downloaded.
     */
    public abstract InputStream getInputStream() throws IOException;
}
