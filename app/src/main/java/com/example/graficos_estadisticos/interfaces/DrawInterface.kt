package com.example.graficos_estadisticos.interfaces

import com.echo.holographlibrary.Bar
import com.example.graficos_estadisticos.databinding.ActivityMainBinding

interface DrawInterface {
    fun graficarBarras(puntos: ArrayList<Bar>, binding: ActivityMainBinding) : ArrayList<Bar>?
    fun generarColorHexAleatorio() : String
}