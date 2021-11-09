package com.adobe.finalProject.stories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adobe.finalProject.R
import com.adobe.finalProject.utils.Constants.API_KEY
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoriesFragment : Fragment() {

    private val storiesViewModel: StoriesViewModel by viewModel()
    private val storiesAdapter = StoriesAdapter(arrayListOf())
    private val manager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers(view)

        getStories("not-found")
    }

    private fun getStories(section: String = "home") {

        storiesViewModel.getStories(section, API_KEY)
    }

    private fun setupObservers(view: View) {

        storiesViewModel.loadingLiveData.observe(viewLifecycleOwner, {


            view.findViewById<CircularProgressIndicator>(R.id.storiesLoader).isVisible = it
        })

        storiesViewModel.failLiveData.observe(viewLifecycleOwner, {

            Snackbar.make(view,"Something went wrong, pleas try again", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { getStories() }
                .show()
        })

        storiesViewModel.storiesListLiveData.observe(viewLifecycleOwner, {

            storiesAdapter.setItmes(it)

            view.findViewById<RecyclerView>(R.id.rvStories)?.apply {
                adapter = storiesAdapter
                layoutManager = manager
            }
        })
    }
}