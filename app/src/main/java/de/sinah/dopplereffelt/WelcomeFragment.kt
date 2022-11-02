package de.sinah.dopplereffelt

import android.os.Bundle
import android.view.*
import android.widget.EditText
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
            view: View -> view.findNavController().navigate(R.id.toResultFragment)
            mainActivityViewModel.frequency = binding.eingabeWert1.text.toString().toDouble()
            mainActivityViewModel.speed = binding.eingabeWert2.text.toString().toDouble()
            mainActivityViewModel.result = 0.0
            mainActivityViewModel.insert()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
    }


}