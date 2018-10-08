package crte.com.radio.xcmpapi;

import crte.com.radio.util.DataConvert;

public class BatteryLevelUtil {
    public static void receiveBatteryChangeData(byte[] data) {
        switch (data[2]) {
            case 0x00://电量正常
                break;
            case 0x01://电量低需要充电
                break;
        }
        if (data[3] == 0xff) {//充电显示 smart电池0~100 其他电池 0xff

        }
        if (DataConvert.byteToInt(data, 4, 2) == 0xffff) {//电压值

        }
    }
}