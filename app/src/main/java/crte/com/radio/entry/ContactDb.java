package crte.com.radio.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false)
public class ContactDb {
    @Id
    private Long id;
    private String name;
    private Integer age;

    public ContactDb(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public ContactDb(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public ContactDb() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
