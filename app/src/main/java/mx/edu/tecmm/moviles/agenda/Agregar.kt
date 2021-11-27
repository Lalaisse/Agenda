package mx.edu.tecmm.moviles.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Agregar : AppCompatActivity() {
    lateinit var txtNombre: EditText
    lateinit var txtApellido: EditText
    lateinit var txtEdad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)
        txtNombre= findViewById(R.id.txtNombreA);
        txtApellido= findViewById(R.id.txtApellido);
        txtEdad= findViewById(R.id.txtEdad);
    }

    fun toAgregar(v: View){
        val persona= Persona(txtNombre.text.toString(),
                                txtApellido.text.toString(),
                                txtEdad.text.toString(), 0);
        Log.e("Boton", "Se presionó");

        Log.e("Datos", "Nombre:  ${persona.nombre}");
        Log.e("Datos", "Apellido:  ${persona.apellido}");
        Log.e("Datos", "Edad:  ${persona.edad}");
        retrofitAgregar(persona)

    }

    fun retrofitAgregar(persona: Persona){
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitService.agregar(persona);

        peticion.enqueue(object:Callback<Resultado>{
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val resultado= response.body();
                terminaRestrofit(resultado!!);
            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("Agregar", "El error es: ${t.message}");
                t.printStackTrace();
            }


        })


    }
    fun terminaRestrofit(resultado: Resultado){
        if (resultado.estado.equals("OK")){
            Toast.makeText(this, "Grabado correctamente", Toast.LENGTH_LONG).show();
            finish()
        }else{
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_LONG).show();
        }
    }
}