package com.adobe.finalProject.stories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R
import com.adobe.finalProject.utils.Constants.API_KEY
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoriesFragment : Fragment() {

    private val storiesViewModel: StoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        getStories()
    }

    private fun getStories(section: String = "home") {

        storiesViewModel.getStories(section, API_KEY)
    }

    private fun setupObservers() {

        storiesViewModel.loadingLiveData.observe(viewLifecycleOwner, {


            view?.findViewById<CircularProgressIndicator>(R.id.storiesLoader)?.isVisible = it
        })

        storiesViewModel.failLiveData.observe(viewLifecycleOwner, {


        })

        storiesViewModel.storiesListLiveData.observe(viewLifecycleOwner, {

            view?.findViewById<RecyclerView>(R.id.rvStories)?.apply {
                adapter = StoriesAdapter(context, it)
                layoutManager = LinearLayoutManager(context)
            }
        })
    }
}