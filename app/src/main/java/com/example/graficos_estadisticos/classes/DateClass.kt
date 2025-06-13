package com.example.graficos_estadisticos.classes

import com.example.graficos_estadisticos.interfaces.DateInterface
import java.util.Calendar

class DateClass : DateInterface{
    override fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }
}