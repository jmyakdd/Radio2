package crte.com.radio.entry

class User {
    var id: Int = 0
    var name: String = ""
    var age: Int = 0
    var sex: String = "MALE"
        set(value) {
            if (value == null || value.equals("")) {
                field = "MALE"
            } else {
                field = value
            }
        }
}