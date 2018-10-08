package crte.com.radio.util;

import java.io.IOException;

public class PhoneStatusUtil {
    public static int shutdown() {
        int r = 0;
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"su" , "-c" ,"reboot -p"});
            r = process.waitFor();
            java.lang.System.out.println("r:" + r );
        } catch (IOException e) {
            e.printStackTrace();
            r = -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            r = -1;
        }
        return r;
    }

    public static int reboot() {
        int r = 0;
        try {
            Process process = Runtime.getRuntime().exec("su -c reboot");
            r = process.waitFor();
            java.lang.System.out.println("r:" + r );
        } catch (IOException e) {
            e.printStackTrace();
            r = -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            r = -1;
        }
        return r;
    }

}
