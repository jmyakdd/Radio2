package crte.com.radio.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false)
public class Contact {
    @Id
    private Long id;
    private String name;
    private Long num;

    public Contact(String name, Integer age) {
        this.name = name;
    }

    public Contact(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
    }

    public Contact() {
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
