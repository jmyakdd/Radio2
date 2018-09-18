package crte.com.radio.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Message {
    @Id(autoincrement = true)
    private Long id;
    private Long num;
    private String name;
    private String content;
    private long time;

    @Generated(hash = 2124073093)
    public Message(Long id, Long num, String name, String content, long time) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.content = content;
        this.time = time;
    }

    @Generated(hash = 637306882)
    public Message() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Message{" +
                "id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
