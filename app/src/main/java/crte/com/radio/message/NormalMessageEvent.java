package crte.com.radio.message;

/**
 * 负责普通xcmp消息通知
 */
public class NormalMessageEvent {
    public static final int CALL_START = 1;
    public static final int CALL_END = 2;
    public static final int CHANGE_CHANNEL = 3;

    private int code = -1;
    private String message;
    private Object data;

    public NormalMessageEvent() {
    }

    public NormalMessageEvent(int code) {
        this.code = code;
    }

    public NormalMessageEvent(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public NormalMessageEvent(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
