package mx.edu.tecmm.moviles.agenda

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IPersona {

    @GET("wsLeer.php")
    fun getListaPersona(): Call<List<Persona>>

    @POST("agrega.php")
    fun agregar(@Body persona: Persona): Call<Resultado>

    @POST("update.php")
    fun modificar(@Body persona: Persona): Call<Resultado>

}