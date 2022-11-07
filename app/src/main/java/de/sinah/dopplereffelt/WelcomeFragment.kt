package de.sinah.dopplereffelt

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import de.sinah.dopplereffelt.database.DopplerDatabase
import de.sinah.dopplereffelt.database.DopplerRepository
import de.sinah.dopplereffelt.databinding.FragmentWelcomeBinding
import de.sinah.dopplereffelt.model.MainActivityViewModel
import de.sinah.dopplereffelt.model.MainActivityViewModelFactory
import kotlin.math.roundToInt


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        val database = DopplerDatabase.getInstance(requireActivity().applicationContext)
        val dopplerRepository = DopplerRepository(database.dopplerDao)

        val viewModelFactory = MainActivityViewModelFactory(dopplerRepository)
        val mainActivityViewModel = ViewModelProvider(this, viewModelFactory).get(
            MainActivityViewModel::class.java
        )

        binding.bestaetigung.setOnClickListener {
            val speedField = binding.eingabeWert2
            var frequency = readFrequency()
            var speed = readSpeed()
            var result = 0.0

            //Hier Logik einfügen




            result = roundto5digits(frequency / (1 - (speed / 343)))

            mainActivityViewModel.frequency = frequency
            mainActivityViewModel.speed = speed
            mainActivityViewModel.result = result
            mainActivityViewModel.insert()
            it.findNavController().navigate(
                WelcomeFragmentDirections.toResultFragment(
                    frequency.toFloat(),
                    speed.toFloat(),
                    result.toFloat()
                )
            )
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun roundto5digits(insertvalue: Double): Double {
        val insertvalue = insertvalue

        var rounded: Double = (insertvalue * 10000).roundToInt().toDouble()
        rounded = rounded / 10000.0

        return rounded

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
    }

    fun readFrequency(): Double {
        val frequencyField = binding.eingabeWert1
        var frequency :Double

        if (frequencyField.text.toString() == "." || frequencyField.text.toString() == "") {
            frequency = 0.0
        }
        if (frequencyField.text.toString().toDouble() < 20.0) {
            Toast.makeText(
                context,
                "Die kleinste hörbare Frequenz ist 20 Hertz",
                Toast.LENGTH_LONG
            ).show()
            frequency = 20.0
        }
        if (frequencyField.text.toString().toDouble() > 20000.0) {
            Toast.makeText(
                context,
                "Die größte hörbare Frequenz ist 20.000 Hertz",
                Toast.LENGTH_LONG
            ).show()
            frequency = 20000.0
        }
        if(frequency != 0.0 && frequency != 20.0 && frequency != 20000.0 ){
            frequency = frequencyField.text.toString().toDouble()
        return frequency
    }
    fun readSpeed():Double{
        val speedField = binding.eingabeWert2

        if (speedField.text.toString() == "." || speedField.text.toString() == "") {
            return 0.0
        }
        if (speedField.text.toString().toDouble() > 1000.0) {
            Toast.makeText(
                context,
                "Die größtmögliche Geschwindigkeit ist 1000 m/s",
                Toast.LENGTH_LONG
            ).show()
            return 20000.0
        }
            return speedField.text.toString().toDouble()

    }
}



