package crte.com.radio.message

import crte.com.radio.util.NetUtil

/**
 * 负责获取网络状态消息
 */
class NetStateMessageEvent {
    var code = NetUtil.NETWORK_NONE
}