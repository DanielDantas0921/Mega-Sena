package com.danx0921.megasena

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_number: EditText = findViewById(R.id.et_number)
        val bt_generator: Button = findViewById(R.id.bt_generator)
        val tv_result: TextView = findViewById(R.id.tv_result)

        sp = getSharedPreferences("db", MODE_PRIVATE)
        val result = sp.getString("result", null)
        if(result != null){
            tv_result.text = "Última aposta apostada: $result"
        }


        //tv_result.text = "Ola mundo" text é para escrever o texto ( é como setText no java)

        //bt_generator.setOnClickListener(View.OnClickListener { tv_result.text = "Acionei o botão" })

        bt_generator.setOnClickListener {

            val text = et_number.text.toString()
            numberGenerator(text, tv_result)
        }

    }


    private fun numberGenerator(text: String, textResult: TextView) {
        if (text.isEmpty()) {
            Toast.makeText(this, "Por favor digite um número", Toast.LENGTH_LONG).show()
            return
        }
        val qtd = text.toInt()
        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Por favor digite um número entre 6 e 15", Toast.LENGTH_LONG)
                .show()
            return

        }
        //sucesso

        val numbers = mutableSetOf<Int>()
        val random = java.util.Random()
        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)

            if (numbers.size == qtd) {
                break
            }


        }

        textResult.text = numbers.joinToString(" - ")

        val editor = sp.edit()
        editor.putString("result", textResult.text.toString())
        editor.apply()




    }



}

//    fun click_do_gerar(view: View) {
//        val tv_result: TextView = findViewById(R.id.tv_result)
//        tv_result.text = "Acionei o botão"
//
//    }
