package com.adobe.finalProject.stories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adobe.finalProject.R
import com.adobe.finalProject.utils.Constants.API_KEY
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers(view)

        setSection(Section.DEFAULT)

        val filterBtn = view.findViewById<FloatingActionButton>(R.id.stories_filter)
        filterBtn.setOnClickListener { showFilterDialog() }

        view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer).apply {
            setOnRefreshListener { storiesViewModel.getStories(API_KEY) }
            setColorSchemeResources(
                R.color.primary,
                R.color.success,
                R.color.orange,
                R.color.red
            )
        }
    }

    private fun showFilterDialog() {

        val bottomSheetDialog = BottomSheetDialog(context as Context).apply {
            setContentView(R.layout.stories_filter_dialog)
        }

        val filterGroup = bottomSheetDialog.findViewById<RadioGroup>(R.id.storiesFilters)
        val checkedFilterId = storiesViewModel.section.value?.viewId

        if (checkedFilterId != null) {

            filterGroup?.check(checkedFilterId)
        } else {

            filterGroup?.check(Section.DEFAULT.viewId)
        }

        filterGroup?.setOnCheckedChangeListener { _, checkedId ->

            val section = when (checkedId) {
                R.id.filterHome -> Section.DEFAULT
                R.id.filterArts -> Section.ARTS
                R.id.filterAutomobiles -> Section.AUTOMOBILES
                R.id.filterBooks -> Section.BOOKS
                R.id.filterBusiness -> Section.BUSINESS
                else -> Section.DEFAULT
            }

            setSection(section)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun setSection(section: Section) {

        storiesViewModel.setSection(section)
    }

    private fun setupObservers(view: View) {

        storiesViewModel.loadingLiveData.observe(viewLifecycleOwner, {


            view.findViewById<CircularProgressIndicator>(R.id.storiesLoader).isVisible = it
            view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer).isRefreshing = it
        })

        storiesViewModel.failLiveData.observe(viewLifecycleOwner, {

            Snackbar.make(view, "Something went wrong, pleas try again", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { storiesViewModel.getStories(API_KEY) }
                .show()
        })

        storiesViewModel.storiesListLiveData.observe(viewLifecycleOwner, {

            storiesAdapter.setItems(it)

            view.findViewById<RecyclerView>(R.id.rvStories)?.apply {
                adapter = storiesAdapter
                layoutManager = manager
            }

            view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer).isRefreshing = false
        })

        storiesViewModel.section.observe(viewLifecycleOwner, {

            storiesViewModel.getStories(API_KEY)
        })
    }
}