package de.sinah.dopplereffelt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bestaetigung).setOnClickListener {
            berechnen(it)
        }
    }

    private fun berechnen(view: View) {
        val geschw = findViewById<EditText>(R.id.eingabeWert1)
        val hertz = findViewById<EditText>(R.id.eingabeWert2)
        //val schall = 343
        val textView = findViewById<TextView>(R.id.textViewTest1)
        val textView2 = findViewById<TextView>(R.id.textViewTest2)
        textView.text = geschw.toString()
        textView2.text = hertz.toString()
    }

}