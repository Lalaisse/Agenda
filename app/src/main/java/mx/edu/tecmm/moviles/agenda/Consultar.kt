package mx.edu.tecmm.moviles.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Consultar : AppCompatActivity(), IClickListener{
    lateinit var recycler: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        recycler=findViewById(R.id.contactos_rv);
        obtenerDatos();
    }

    override fun onResume() {
        super.onResume()
        obtenerDatos()
    }

    fun obtenerDatos() {
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<List<Persona>> = retrofitService.getListaPersona();

        peticion.enqueue(object : Callback<List<Persona>> {
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                val listaPersonas= response.body();
                acomodarRecycler(listaPersonas!!);
            }

            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                Log.e("Error", "Pasó algo");
                t.printStackTrace();
            }

        });
    }
    fun acomodarRecycler(listaPersona: List<Persona>){
        //Crear mi adaptador y mandar llamar los datos
        val adaptador= AdaptadorPersona(this, listaPersona, this);
        recycler.adapter= adaptador;
    }

    override fun onCellClick(persona: Persona) {
        Log.e("Click", "${persona}")
        val intent= Intent(this, Modificar::class.java);
        intent.putExtra("nombre", persona.nombre)
        intent.putExtra("apellido", persona.apellido);
        intent.putExtra("edad", persona.edad);
        intent.putExtra("id", persona.id.toString());
        startActivity(intent);
    }

}