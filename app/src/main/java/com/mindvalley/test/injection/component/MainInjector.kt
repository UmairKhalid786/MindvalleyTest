package com.mindvalley.test.injection.component

import com.mindvalley.test.base.BaseRepository
import com.mindvalley.test.injection.module.NetworkModule
import dagger.Component

import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface MainInjector {

//    /**
//     * Injects required dependencies into the specified HomeViewModel.
//     * @param categoriesViewModel HomeViewModel in which to inject the dependencies
//     */
//
////    fun inject(categoriesViewModel: CategoriesListViewModel)
//
//    /**
//     * Injects required dependencies into the specified CategoriesRepository.
//     * @param CategoriesRepository CategoriesRepository in which to inject the dependencies
//     */
//    fun inject(cat: CategoriesRepository)

    /**
     * Injects required dependencies into the specified BaseRepository.
     * @param BaseRepository BaseRepository in which to inject the dependencies
     */
    fun inject(baseRepository: BaseRepository)


    @Component.Builder
    interface Builder {
        fun build(): MainInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}