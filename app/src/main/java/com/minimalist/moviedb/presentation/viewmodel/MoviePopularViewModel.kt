package com.minimalist.moviedb.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.minimalist.moviedb.domain.interactor.DefaultObserver
import com.minimalist.moviedb.domain.interactor.GetMoviePopular
import com.minimalist.moviedb.domain.model.MoviePopular
import com.minimalist.moviedb.presentation.data.Resource
import com.minimalist.moviedb.presentation.data.ResourceState
import com.minimalist.moviedb.presentation.model.MoviePopularView
import com.minimalist.moviedb.presentation.model.mapper.MoviePopularViewMapper
import javax.inject.Inject

class MoviePopularViewModel @Inject constructor(private val getMoviePopular: GetMoviePopular,
                                                private val moviePopularViewMapper: MoviePopularViewMapper):
        ViewModel() {

    private val moviePopularLiveData: MutableLiveData<Resource<MoviePopularView>> = MutableLiveData()

    init {
        fetchMoviePopular()
    }

    override fun onCleared() {
        getMoviePopular.dispose()
        super.onCleared()
    }

    fun getMoviePopular() = moviePopularLiveData

    private fun fetchMoviePopular() {
        moviePopularLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getMoviePopular.execute(MoviePopularObserver(), GetMoviePopular.Params.forPage(1))
    }

    inner class MoviePopularObserver: DefaultObserver<MoviePopular>() {
        override fun onComplete() {
        }

        override fun onNext(t: MoviePopular) {
            moviePopularLiveData.postValue(Resource(ResourceState.SUCCESS, moviePopularViewMapper.mapToView(t), null))
            Log.d("Popular Movie Success", t.toString())
        }

        override fun onError(e: Throwable) {
            moviePopularLiveData.postValue(Resource(ResourceState.ERROR, null, e.message))
            Log.d("Popular Movie Error", e.toString())
        }
    }

}

open class MoviePopularViewModelFactory(
        private val getMoviePopular: GetMoviePopular,
        private val moviePopularViewMapper: MoviePopularViewMapper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviePopularViewModel::class.java)) {
            return MoviePopularViewModel(getMoviePopular, moviePopularViewMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}