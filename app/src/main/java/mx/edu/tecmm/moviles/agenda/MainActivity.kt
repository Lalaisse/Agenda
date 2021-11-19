package mx.edu.tecmm.moviles.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun agregar(v: View){
        Log.e("Boton", "Agregar");
        val intent= Intent(this, Agregar:: class.java);
        startActivity(intent);

    }

    fun consultar(v: View){
        Log.e("Boton", "Consultar");
        val intent= Intent(this, Consultar:: class.java);
        startActivity(intent);
    }
}