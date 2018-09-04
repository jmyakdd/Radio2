package crte.com.radio.api

class BaseListResponseResult <T>{
    var code:Int = 0
    var msg:String = ""
    var data: List<T>? = null
    override fun toString(): String {
        return "BaseListResponseResult(code=$code, msg='$msg', data=$data)"
    }
}