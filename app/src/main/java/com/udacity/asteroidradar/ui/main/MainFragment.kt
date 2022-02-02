package com.udacity.asteroidradar.ui.main

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

@RequiresApi(Build.VERSION_CODES.O)
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    val adapter = MainAsteroidAdapter(AsteroidListener { asteroidId ->
        viewModel.onAsteroidItemClick(asteroidId)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this





        binding.viewModel = viewModel
        binding.asteroidRecycler.adapter = adapter



        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner, { asteroid ->
            asteroid?.let {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.onDetailFragmentNavigated()

            }
        })
        viewModel.asteroidsWeek.observe(viewLifecycleOwner){
            it?.let {
                adapter.submitList(it)
            }
        }



        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_week_asteroids -> viewModel.asteroidsWeek.observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitList(it)
                }
                item.isChecked = true
            }
            R.id.show_today_asteroids -> viewModel.asteroidsToday.observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitList(it)
                }
                item.isChecked = true
            }

            else -> {
                viewModel.asteroidsWeek.observe(viewLifecycleOwner) {
                    it?.let {
                        adapter.submitList(it)
                    }
                }
                item.isChecked = true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}
