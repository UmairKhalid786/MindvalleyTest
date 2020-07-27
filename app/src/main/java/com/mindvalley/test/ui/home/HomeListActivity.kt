package com.mindvalley.test.ui.home

import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.test.R
import com.mindvalley.test.base.BaseListViewModel
import com.mindvalley.test.injection.ViewModelFactory
import com.mindvalley.test.ui.home.category.CategoriesListViewModel
import com.mindvalley.test.ui.home.category.CategoryListAdapter
import com.mindvalley.test.ui.home.channel.ChannelsListAdapter
import com.mindvalley.test.ui.home.channel.ChannelsListViewModel
import com.mindvalley.test.ui.home.episode.EpisodesListViewModel
import kotlinx.android.synthetic.main.activity_category_list.*

class HomeListActivity : AppCompatActivity() {

    private lateinit var categoriesViewModel: CategoriesListViewModel
    private lateinit var episodesViewModel: EpisodesListViewModel
    private lateinit var channelsViewModel: ChannelsListViewModel

    private var errorSnackbar: Snackbar? = null

    val categoryListAdapter: CategoryListAdapter = CategoryListAdapter()
    val channelsAdapter: ChannelsListAdapter = ChannelsListAdapter()
    val episodesAdapter: MediaListAdapter = MediaListAdapter(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        initViews()
        setupViewModels();
    }

    private fun setupViewModels() {
        categoriesViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CategoriesListViewModel::class.java)
        episodesViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(EpisodesListViewModel::class.java)
        channelsViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ChannelsListViewModel::class.java)

        setViewModelListener(categoriesViewModel)
        setViewModelListener(episodesViewModel)
        setViewModelListener(channelsViewModel)

        categoriesViewModel.catObserver.observe(this, Observer { cats ->
            categoryListAdapter.update(cats)
        })
        channelsViewModel.chanObserver.observe(this, Observer { chans ->
            channelsAdapter.updateMedia(chans)
        })

        episodesViewModel.episodesObserver.observe(this, Observer { chans ->
            episodesAdapter.updateMedia(chans)
        })
    }

    private fun setViewModelListener(viewModel: BaseListViewModel) {
        viewModel.loadingVisibility.observe(this, onLoadingObserver)
        viewModel.errorMessage.observe(this, onErrorObserver)
    }

    val onLoadingObserver : Observer<Int> = object : Observer<Int> {
        override fun onChanged(t: Int?) {
            pullToRefresh.isRefreshing = false
            progress.visibility = t!!
        }
    }

    val onErrorObserver : Observer<Int> = object : Observer<Int> {
        override fun onChanged(errorMessage: Int?) {
            if (errorMessage != null) showError(errorMessage) else hideError()
        }
    }


    private fun initViews() {
        setTitle("Channels")
        setAdapters()
        pullToRefresh.setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh() {
        categoriesViewModel.refreshStuff()
        channelsViewModel.refreshStuff()
        episodesViewModel.refreshStuff()
    }

    private fun setAdapters() {
        categoriesList.layoutManager = GridLayoutManager(this, 2)
        channelsList.layoutManager = LinearLayoutManager(this)
        episodesList.layoutManager = LinearLayoutManager(this , RecyclerView.HORIZONTAL , false)

        categoriesList.adapter = categoryListAdapter
        channelsList.adapter = channelsAdapter
        episodesList.adapter = episodesAdapter
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar =
            Snackbar.make(window.decorView.rootView, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, { refresh() })
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}