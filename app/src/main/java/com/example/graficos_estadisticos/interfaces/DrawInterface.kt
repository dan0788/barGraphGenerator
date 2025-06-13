package com.example.graficos_estadisticos.interfaces

import com.echo.holographlibrary.Bar
import com.example.graficos_estadisticos.databinding.ActivityMainBinding

interface DrawInterface {
    fun graficarBarras(puntos: ArrayList<Bar>, attribute: String, quantity: String) : Boolean
    fun generarColorHexAleatorio() : String
}