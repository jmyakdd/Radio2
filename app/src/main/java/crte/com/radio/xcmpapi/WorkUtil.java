package crte.com.radio.xcmpapi;

import crte.com.radio.util.DataConvert;

public class WorkUtil {
    public static void receiveWorkMsg(byte[] data) {
        int groupId = DataConvert.byteToInt(data, 5, 3);
        int length = DataConvert.byteToInt(data, 11, 2);
        byte[] content = new byte[length];
        System.arraycopy(data, 13, content, 0, length);

        int head = DataConvert.byteToInt(content, 0, 3);

    }
}
