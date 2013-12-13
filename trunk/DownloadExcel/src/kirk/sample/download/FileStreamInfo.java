package kirk.sample.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileStreamInfo implements StreamInfo {

    /**
     * The content type for this stream.
     */
    private String contentType;

    /**
     * The file to be downloaded.
     */
    private File file;

    /**
     * Constructs an instance of this class, based on the supplied
     * parameters.
     *
     * @param contentType The content type of the file.
     * @param file        The file to be downloaded.
     */
    public FileStreamInfo(String contentType, File file) {
        this.contentType = contentType;
        this.file = file;
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
     * Returns an input stream on the file to be downloaded. This stream
     * will be closed by the <code>DownloadAction</code>.
     *
     * @return The input stream for the file to be downloaded.
     */
    public InputStream getInputStream() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        return bis;
    }

}
