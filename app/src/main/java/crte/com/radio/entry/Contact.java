package crte.com.radio.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

@Entity(generateConstructors = false)
public class Contact {
    @Id
    private Long id;
    private String signalNum;
    private String signalName;

    public Contact(String signalName) {
        this.signalName = signalName;
    }

    public Contact(String signalNum, String signalName) {
        this.signalNum = signalNum;
        this.signalName = signalName;
    }

    public Contact(Long id, String signalName) {
        this.id = id;
        this.signalName = signalName;
    }

    public Contact(Long id, String signalName, String signalNum) {
        this.id = id;
        this.signalName = signalName;
        this.signalNum = signalNum;
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignalNum() {
        return signalNum;
    }

    public void setSignalNum(String signalNum) {
        this.signalNum = signalNum;
    }

    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }
}
