package crte.com.radio.util;

public class StringUtil {

    public static boolean isNull(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhone(String tel){
        if(isNull(tel)){
            return false;
        }
        if(tel.length()!=11){
            return false;
        }

        return false;
    }
}
