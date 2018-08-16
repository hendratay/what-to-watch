package com.example.hendratay.whatowatch.data.repository.datasource

import com.example.hendratay.whatowatch.data.entity.PopularTvEntity
import com.example.hendratay.whatowatch.data.remote.TMDBServiceFactory
import io.reactivex.Observable

class TvCloudDataStore: TvDataStore {

    override fun getPopularTv(page: Int): Observable<PopularTvEntity> {
        return TMDBServiceFactory.makeService().popularTv(page)
    }

}