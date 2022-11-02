package de.sinah.dopplereffelt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import de.sinah.dopplereffelt.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        val args = ResultFragmentArgs.fromBundle(requireArguments())
        val speed = args.speed.toString()
        val frequency = args.frequency.toString()
        val result = args.result.toString()

        return binding.root
    }
}