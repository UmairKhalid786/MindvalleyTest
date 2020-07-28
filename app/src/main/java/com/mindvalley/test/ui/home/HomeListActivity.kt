package com.mindvalley.test.ui.home

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mindvalley.test.R
import com.mindvalley.test.base.BaseListViewModel
import com.mindvalley.test.injection.ViewModelFactory
import com.mindvalley.test.ui.home.category.CategoriesListViewModel
import com.mindvalley.test.ui.home.category.CategoryListAdapter
import com.mindvalley.test.ui.home.channel.ChannelsListAdapter
import com.mindvalley.test.ui.home.channel.ChannelsListViewModel
import com.mindvalley.test.ui.home.episode.EpisodesListViewModel
import com.mindvalley.test.utils.MainDividerItemDecoration
import com.mindvalley.test.utils.extension.setHorizontalLayoutManager
import com.mindvalley.test.utils.extension.setVerticalLayoutManager
import com.mindvalley.test.utils.extension.visibility
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

        episodesViewModel       = loadViewModel(EpisodesListViewModel::class.java)
        channelsViewModel       = loadViewModel(ChannelsListViewModel::class.java)
        categoriesViewModel     = loadViewModel(CategoriesListViewModel::class.java)

        setViewModelListener(categoriesViewModel)
        setViewModelListener(episodesViewModel)
        setViewModelListener(channelsViewModel)

        categoriesViewModel.catObserver.observe(this, Observer { cats ->
            categoryListAdapter.update(cats)
            categoriesTv.visibility(cats.size > 0)
        })
        channelsViewModel.chanObserver.observe(this, Observer { chans ->
            channelsAdapter.updateMedia(chans)
            channelsDivd.visibility(chans.size > 0)
        })
        episodesViewModel.episodesObserver.observe(this, Observer { chans ->
            episodesAdapter.updateMedia(chans)
            episodesTv.visibility(chans.size > 0)
            epiDvd.visibility(chans.size > 0)
        })
    }

    private fun setViewModelListener(viewModel: BaseListViewModel) {
        viewModel.loadingVisibility.observe(this, onLoadingObserver)
        viewModel.errorMessage.observe(this, onErrorObserver)
    }

    val onLoadingObserver: Observer<Int> = object : Observer<Int> {
        override fun onChanged(t: Int?) {
            pullToRefresh.isRefreshing = false
            progress.visibility = t!!
        }
    }

    val onErrorObserver: Observer<Int> = object : Observer<Int> {
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
        channelsList.addItemDecoration(MainDividerItemDecoration(this))
    }

    private fun refresh() {
        categoriesViewModel.refreshStuff()
        channelsViewModel.refreshStuff()
        episodesViewModel.refreshStuff()
    }

    private fun setAdapters() {
        //This WICKED solution will display 3 items for tablet and 2 for mobiles ;)
        val itemsCount = resources.getInteger(R.integer.categories_item)
        categoriesList.layoutManager = GridLayoutManager(this, itemsCount)
        channelsList.setVerticalLayoutManager()
        episodesList.setHorizontalLayoutManager()

        categoriesList.adapter = categoryListAdapter
        channelsList.adapter = channelsAdapter
        episodesList.adapter = episodesAdapter
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(window.decorView.rootView, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, { refresh() })
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    fun <T : ViewModel?> loadViewModel(modelClass: Class<T>): T {
        return ViewModelProviders.of(this, ViewModelFactory(this))
            .get(modelClass)
    }
}