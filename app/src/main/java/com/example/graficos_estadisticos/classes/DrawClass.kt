package com.example.graficos_estadisticos.classes

import com.echo.holographlibrary.Bar
import com.example.graficos_estadisticos.interfaces.DrawInterface
import android.graphics.Color
import java.util.Random
import android.util.Log

class DrawClass : DrawInterface {

    override fun graficarBarras(puntos: ArrayList<Bar>, attribute: String, quantity: String) : Boolean{
        try{
            val barra = Bar()
            var color = generarColorHexAleatorio()
            barra.color = Color.parseColor(color)
            barra.name = attribute
            barra.value = quantity.toFloat()
            puntos.add(barra)

            return true
        }catch(e: NumberFormatException){
            Log.e("Draw", "Error al convertir la cantidad '$quantity' a Float: ${e.message}", e)
            return false
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