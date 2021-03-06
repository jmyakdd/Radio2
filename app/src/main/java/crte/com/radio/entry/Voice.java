package crte.com.radio.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Voice {
    @Id
    private Long id;
    private Long num;
    private String fileName;
    private String filePath;
    private long time;
    @Keep
    public Voice(){

    }
    @Keep
    public Voice(Long id, Long num, String fileName, String filePath) {
        this.id = id;
        this.num = num;
        this.fileName = fileName;
        this.filePath = filePath;
    }
    @Generated(hash = 2020319746)
    public Voice(Long id, Long num, String fileName, String filePath, long time) {
        this.id = id;
        this.num = num;
        this.fileName = fileName;
        this.filePath = filePath;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
