package crte.com.radio.api

class BaseResponseResult <T>{
    var code:Int = 0
    var msg:String = ""
    var data: T? = null
}