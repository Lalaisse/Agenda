package mx.edu.tecmm.moviles.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Modificar : AppCompatActivity() {
    lateinit var txtNombre: EditText
    lateinit var txtApellido: EditText
    lateinit var txtEdad: EditText
    lateinit var txtId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)
        txtNombre= findViewById(R.id.txtNombreA);
        txtApellido= findViewById(R.id.txtApellido);
        txtEdad= findViewById(R.id.txtEdad);
        txtId=findViewById(R.id.txtIdM)

        txtNombre.setText(intent.getStringExtra("nombre"))
        txtApellido.setText(intent.getStringExtra("apellido"))
        txtEdad.setText(intent.getStringExtra("edad"))
        txtId.setText(intent.getStringExtra("id"))
    }

    fun toModificar(v: View) {
        val persona= Persona(txtNombre.text.toString(),
            txtApellido.text.toString(),
            txtEdad.text.toString(),txtId.text.toString().toInt());
        Log.e("Datos", "Se modificò");

        Log.e("Datos", "ID:  ${persona.id}");
        Log.e("Datos", "Nombre:  ${persona.nombre}");
        Log.e("Datos", "Apellido:  ${persona.apellido}");
        Log.e("Datos", "Edad:  ${persona.edad}");
        retrofitModificar(persona)
    }

    fun retrofitModificar(persona: Persona){
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitService.modificar(persona);

        peticion.enqueue(object: Callback<Resultado> {
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val resultado= response.body();
                Log.e("Editar", "Resultado: ${resultado}");
                terminaRetrofit(resultado!!);
            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("Editar", "El error es: ${t.message}");
                t.printStackTrace();
            }
        })
    }
    fun terminaRetrofit(resultado: Resultado){
        if (resultado.estado.equals("OK")) {
            Toast.makeText(this, "Editado correctamente", Toast.LENGTH_LONG).show();
            finish()

        }else{
            Toast.makeText(this, "¡Oh no! ocurrió un error", Toast.LENGTH_LONG).show();
        }
    }
}