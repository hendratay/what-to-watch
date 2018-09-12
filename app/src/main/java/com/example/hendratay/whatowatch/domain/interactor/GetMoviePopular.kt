package com.example.hendratay.whatowatch.domain.interactor

import com.example.hendratay.whatowatch.domain.model.MoviePopular
import com.example.hendratay.whatowatch.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularMovie @Inject constructor(private val movieRepository: MovieRepository): UseCase<MoviePopular, GetPopularMovie.Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<MoviePopular> {
        return movieRepository.getPopularMovie(params!!.page)
    }

    class Params(val page: Int) {
        companion object {
            fun forPage(page: Int): Params {
                return Params(page)
            }
        }
    }

}