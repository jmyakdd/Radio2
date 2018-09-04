package crte.com.radio.api

import crte.com.radio.entry.Contact
import crte.com.radio.entry.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService{
    @POST("/accuont/login")
    fun login(@Query("name")name:String,@Query("password")password:String):Observable<BaseResponseResult<Contact>>

    @GET("/getUserList")
    fun getUserList():Observable<BaseListResponseResult<User>>
}