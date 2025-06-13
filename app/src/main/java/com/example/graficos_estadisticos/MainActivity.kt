package com.example.graficos_estadisticos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.example.graficos_estadisticos.classes.DrawClass
import com.example.graficos_estadisticos.classes.DateClass
import com.example.graficos_estadisticos.databinding.ActivityMainBinding
import android.util.Log
import android.app.AlertDialog
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val puntos: ArrayList<Bar> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val draw = DrawClass()
        val year = DateClass()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener(){
            val attributeText = binding.editAttribute.text.toString().trim()
            val quantityText = binding.editCuantity.text.toString().trim()

            if (attributeText.isEmpty()) {
                binding.editAttribute.error = "El atributo no puede estar vacío"
                return@setOnClickListener // Sale del click listener
            }

            val success = draw.graficarBarras(puntos, attributeText, quantityText)

            if(success){
                binding.graphTitle.text = "Gráfico de Muestra ${year.getCurrentYear()}"

                var text1 = draw.generarColorHexAleatorio()
                Log.d("ColorGenerado", "El color hexadecimal aleatorio es: $text1")

                val grafica = findViewById<View>(R.id.graphBar) as BarGraph
                grafica.bars = puntos
                grafica.postInvalidate() // Forzar redibujado del gráfico para asegurar la actualización

                // Limpiar campos para la siguiente entrada
                binding.editAttribute.text.clear()
                binding.editCuantity.text.clear()
                binding.editAttribute.requestFocus()
            }else{
                binding.editCuantity.error = "Por favor, introduce un número válido para la cantidad (ej. 10.5)"
                Log.e("MainActivity", "Error de entrada: Cantidad no válida.")
            }
        }

        binding.btnLimpiar.setOnClickListener(){
            binding.graphTitle.text = ""
            puntos.clear()
            val grafica = findViewById<BarGraph>(R.id.graphBar)
            grafica.bars = puntos // Asigna la lista vacía al gráfico
            grafica.postInvalidate() // Fuerza el redibujado
            Log.d("MainActivity", "Gráfico limpiado.")
        }

        binding.btnCambiarTitulo.setOnClickListener(){
            // Crea un EditText para que el usuario ingrese el nuevo título
            val inputEditText = EditText(this)
            inputEditText.hint = "Nuevo título del gráfico"
            // Opcional: Establece el texto actual como valor por defecto en el EditText
            inputEditText.setText(binding.graphTitle.text)

            // Crea un LinearLayout para envolver el EditText y añadirle padding
            val container = LinearLayout(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            // Añade un padding alrededor del EditText dentro del diálogo
            params.setMargins(50, 0, 50, 0) // izq, arriba, der, abajo
            container.layoutParams = params
            container.addView(inputEditText, params)


            AlertDialog.Builder(this).apply {
                setTitle("Cambiar Título del Gráfico")
                // Establece la vista personalizada (el EditText) para el diálogo
                setView(container) // O simplemente setView(inputEditText) si no quieres padding extra

                setPositiveButton("Aceptar") { dialog, _ ->
                    val newTitle = inputEditText.text.toString().trim()
                    if (newTitle.isNotEmpty()) {
                        binding.graphTitle.text = newTitle
                        Toast.makeText(this@MainActivity, "Título actualizado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "El título no puede estar vacío", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss() // Cierra el diálogo
                }

                setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel() // Cancela el diálogo
                }
                show() // Muestra el diálogo
            }
        }

        binding.btnEliminarUltimo.setOnClickListener(){
            if (puntos.isNotEmpty()) {
                puntos.removeAt(puntos.size - 1) // Elimina el último elemento
                val grafica = findViewById<BarGraph>(R.id.graphBar)
                grafica.bars = puntos
                grafica.postInvalidate()
                Log.d("MainActivity", "Último registro eliminado.")
                Toast.makeText(this, "Último registro eliminado", Toast.LENGTH_SHORT).show()
            } else {
                // Si la lista está vacía, informa al usuario
                Toast.makeText(this, "No hay registros para eliminar", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Intento de eliminar de gráfico vacío.")
            }

            if (puntos.isEmpty()){
                binding.graphTitle.text=""
            }
        }
    }

}