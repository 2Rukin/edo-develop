package model.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.UUID;

public class ByteArrayFileContainer implements FileContainer {
    private final String name;

    private UUID storageFileId;
    private String originalFileName;
    private ByteArrayInputStream stream;
    private byte[] file;


    public ByteArrayFileContainer(String name) {
        this.name = name;
    }

    public ByteArrayFileContainer(byte[] file, String name) {
        this.name = name;
        this.stream = new ByteArrayInputStream(file);
        this.file = file;
    }

    public ByteArrayFileContainer(byte[] file, String name, UUID storageFileId) {
        this.name = name;
        this.stream = new ByteArrayInputStream(file);
        this.storageFileId = storageFileId;
        this.file = file;
    }

    public ByteArrayFileContainer(byte[] file, String name, String originalFileName) {
        this.stream = new ByteArrayInputStream(file);
        this.name = name;
        this.originalFileName = originalFileName;
        this.file = file;
    }

    /**
     * использовать только для вычислений, где расширение файла не важно
     */
    public ByteArrayFileContainer(UUID storageFileId) {
        this.name = "containerForCheck";
        this.storageFileId = storageFileId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0L;
    }

    @Override
    public long getSize() {
        return stream.available();
    }

    @Override
    public InputStream getInputStream() {
        return stream;
    }

    @Override
    public byte[] getBytes() {
        return file != null ? file : stream.readAllBytes();
    }

    @Override
    public void setDate(ZonedDateTime date) {
    }

    @Override
    public String getOriginalFileName() {
        return originalFileName;
    }

    @Override
    public UUID getStorageFileId() {
        return storageFileId;
    }

    public void setStorageFileId(UUID storageFileId) {
        this.storageFileId = storageFileId;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Override
    public void close() throws Exception {
        stream.close();
    }
}
