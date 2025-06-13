package com.example.graficos_estadisticos.classes

import com.echo.holographlibrary.Bar
import com.example.graficos_estadisticos.interfaces.DrawInterface
import android.graphics.Color
import android.widget.EditText
import com.example.graficos_estadisticos.databinding.ActivityMainBinding
import java.util.Random

class Draw : DrawInterface {

    override fun graficarBarras(puntos: ArrayList<Bar>, binding: ActivityMainBinding) : ArrayList<Bar>?{
        try{
            val barra = Bar()
            var color = generarColorHexAleatorio()
            barra.color = Color.parseColor(color)
            barra.name = binding.editAttribute.text.toString()
            barra.value = binding.editCuantity.text.toString().toFloat()
            puntos.add(barra)

            return puntos
        }catch(e: NumberFormatException){
            return null
        }
    }

    override fun generarColorHexAleatorio() : String {
        val random = Random()

        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)

        return String.format("#%02X%02X%02X", red, green, blue)
    }
}