package de.sinah.dopplereffelt

import android.os.Bundle
import android.view.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome,container,false)

        val database = DopplerDatabase.getInstance(requireActivity().applicationContext)
        val dopplerRepository = DopplerRepository(database.dopplerDao)

        val viewModelFactory = MainActivityViewModelFactory(dopplerRepository)
        val mainActivityViewModel = ViewModelProvider(this,viewModelFactory).get(
            MainActivityViewModel::class.java)

        binding.bestaetigung.setOnClickListener {
            var frequency = binding.eingabeWert1.text.toString().toDouble()
            var speed = binding.eingabeWert2.text.toString().toDouble()
            var result = 0.0
            var error = ""

            //Hier Logik einfügen

            if (frequency.toString() == ".") {
                error = "Ungültige Eingabe Frequenz"
                frequency = 0.0
                speed = 0.0
            }
            if (speed.toString() == ".") {
                error = "Ungültige Eingabe Geschwindigkeit"
                frequency = 0.0
                speed = 0.0
            }
            if (frequency.toString() == "." && speed.toString() == ".") {
                error = "Ungültige Eingabe Frequenz und Geschwindigkeit"
                frequency = 0.0
                speed = 0.0
            }

            result = roundto5digits(frequency / (1 - (speed / 343)))

            mainActivityViewModel.frequency = frequency
            mainActivityViewModel.speed = speed
            mainActivityViewModel.result = result
            mainActivityViewModel.error = error
            mainActivityViewModel.insert()
            it.findNavController().navigate(WelcomeFragmentDirections.toResultFragment(error.toString(), frequency.toFloat(), speed.toFloat(), result.toFloat()))
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun roundto5digits(insertvalue : Double):Double{
        val insertvalue = insertvalue

        var rounded :Double = (insertvalue * 10000).roundToInt().toDouble()
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


}
