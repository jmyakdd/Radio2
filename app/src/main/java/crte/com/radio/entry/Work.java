package crte.com.radio.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Work {
    @Id(autoincrement = true)
    private Long id;
    private Long num;
    private String content;
    private long time;

    @Generated(hash = 1322149890)
    public Work(Long id, Long num, String content, long time) {
        this.id = id;
        this.num = num;
        this.content = content;
        this.time = time;
    }

    public Work(Long num, String content, long time) {
        this.num = num;
        this.content = content;
        this.time = time;
    }

    @Generated(hash = 572069219)
    public Work() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", num=" + num +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
