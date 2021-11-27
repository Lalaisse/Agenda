package mx.edu.tecmm.moviles.agenda

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClase {

    //Sirve para que sea estático y no tener que crear objeto para utilizarlo:
    companion object{
        fun getRetrofit(): Retrofit{
            val retrofit= Retrofit.Builder()
                .baseUrl("http://187.217.234.227/7mo/crud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            return retrofit;
        }
    }

}