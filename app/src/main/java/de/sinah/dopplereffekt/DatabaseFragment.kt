package de.sinah.dopplereffekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.sinah.dopplereffekt.database.DopplerDatabase
import de.sinah.dopplereffekt.database.DopplerRepository
import de.sinah.dopplereffekt.databinding.FragmentDatabaseBinding
import de.sinah.dopplereffekt.model.MainActivityViewModel
import de.sinah.dopplereffekt.model.MainActivityViewModelFactory

class DatabaseFragment : Fragment() {

    private lateinit var binding: FragmentDatabaseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_database, container, false)

        val context = requireActivity().applicationContext

        val database = DopplerDatabase.getInstance(context)
        val dopplerRepository = DopplerRepository(database.dopplerDao)

        val viewModelFactory = MainActivityViewModelFactory(dopplerRepository)
        val mainActivityViewModel = ViewModelProvider(this,viewModelFactory).get(
            MainActivityViewModel::class.java)

        val adapter = DopplerAdapter(listener = {mainActivityViewModel.deleteDoppler(it)})

        binding.lifecycleOwner = this

        binding.dopplerList.adapter = adapter

        binding.dopplerList.layoutManager = LinearLayoutManager(context)

        mainActivityViewModel.doppler.observe(viewLifecycleOwner, Observer{adapter.submitList(it)})

        binding.deleteAll.setOnClickListener { mainActivityViewModel.deleteAll() }

        return binding.root
    }
}
