package de.sinah.dopplereffelt

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import de.sinah.dopplereffelt.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        val speed = args.speed.toString()
        val frequency = args.frequency.toString()
        val result = args.result.toString()

        binding.speedField.text = args.speed.toString()
        binding.frequencyField.text = args.frequency.toString()
        binding.resultField.text = args.result.toString()

        setHasOptionsMenu(true)

        binding.newDoppler.setOnClickListener{
            it.findNavController().navigate(ResultFragmentDirections.backToWelcome())
        }

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