package de.sinah.dopplereffelt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.dhHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        findViewById<Button>(R.id.bestaetigung).setOnClickListener {
            showNumbsResult(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.dhHostFragment)
        return navController.navigateUp()
    }

    private fun showNumbsResult(view: View) {
        //Get elements
        val editText = findViewById<EditText>(R.id.eingabeWert1)
        val editText2 = findViewById<EditText>(R.id.eingabeWert2)
        val setText = findViewById<TextView>(R.id.textView6)
        val setText2 = findViewById<TextView>(R.id.textView7)
        //Set text
        setText.text = editText.text
        setText2.text = editText2.text
    }

}

