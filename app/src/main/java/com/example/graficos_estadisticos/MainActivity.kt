package com.example.graficos_estadisticos

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.example.graficos_estadisticos.classes.Draw
import com.example.graficos_estadisticos.databinding.ActivityMainBinding
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var puntos: ArrayList<Bar>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val draw = Draw()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var puntos = ArrayList<Bar>?=null
        puntos = ArrayList()

        binding.btnAdd.setOnClickListener(){
            puntos = draw.graficarBarras(puntos!!, binding)
            if(puntos!=null){
                var text1 = draw.generarColorHexAleatorio()
                Log.d("ColorGenerado", "El color hexadecimal aleatorio es: $text1")

                val grafica = findViewById<View>(R.id.graphBar) as BarGraph
                grafica.bars = puntos!!
            }else{
                binding.editCuantity.error = "Por favor, introduce un número válido para la cantidad (ej. 10.5)"
                Log.e("MainActivity", "Error de entrada: Cantidad no válida.")
            }
        }
    }

}