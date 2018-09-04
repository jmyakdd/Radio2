package crte.com.radio.entry

class StatusMessageEvent(i: Int, s: String) {
    var code:Int = -1
    set(value) {
        if(value==null){
            field = -1
        }else{
            field = value
        }
    }
    var message: String? = null
    var obj: Any? = null

    init {
        code = i
        message = s
    }

    override fun toString(): String {
        return "MessageEvent(code=$code, message=$message, obj=$obj)"
    }


}