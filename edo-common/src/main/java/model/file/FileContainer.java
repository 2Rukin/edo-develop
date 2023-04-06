package model.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface FileContainer extends AutoCloseable {
    String getName();

    String getOriginalFileName();

    UUID getStorageFileId();

    boolean isEmpty() throws IOException;

    long getSize() throws IOException;

    InputStream getInputStream() throws IOException;

    byte[] getBytes();

    void setDate(ZonedDateTime date);
}
