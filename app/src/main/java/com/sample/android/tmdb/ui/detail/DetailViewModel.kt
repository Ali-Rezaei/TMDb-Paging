package com.sample.android.tmdb.ui.detail

import androidx.lifecycle.MutableLiveData
import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.ui.detail.DetailViewModel.DetailWrapper
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

abstract class DetailViewModel : BaseViewModel<DetailWrapper>() {

    override val requestObservable: Observable<DetailWrapper> by lazy {
        Observable.zip(trailers.map { it.videos }, credits,
                BiFunction<List<Video>, CreditWrapper, DetailWrapper> { trailers, creditWrapper ->
                    DetailWrapper(trailers, creditWrapper)
                })
    }

    override val mutableLiveData: MutableLiveData<DetailWrapper> by lazy {
        MutableLiveData<DetailWrapper>().also { sendRequest() }
    }

    protected abstract val trailers : Observable<VideoWrapper>

    protected abstract val credits: Observable<CreditWrapper>

    class DetailWrapper(
            val trailers: List<Video>,
            val creditWrapper: CreditWrapper
    )
}