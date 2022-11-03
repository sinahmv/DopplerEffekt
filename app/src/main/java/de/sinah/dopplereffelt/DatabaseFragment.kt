package de.sinah.dopplereffelt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import de.sinah.dopplereffelt.databinding.FragmentDatabaseBinding

class DatabaseFragment : Fragment() {

    private lateinit var binding: FragmentDatabaseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_database, container, false)

        return binding.root
    }
}
