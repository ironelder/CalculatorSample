package com.ironelder.calculatorsample

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_display.*

class DisplayFragment : Fragment(R.layout.fragment_display) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val presenter = DisplayPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.toObservable()?.subscribe({
            tv_history.text = (it as String)
        }, {})?.addDisposable()

        EventBus.toObservableResult()?.subscribe({
            tv_result.text = (it as String)
        }, {})?.addDisposable()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.saveDisplayData(
            activity?.getSharedPreferences("pref", MODE_PRIVATE),
            "result",
            tv_result.text.toString()
        )
        presenter.saveDisplayData(
            activity?.getSharedPreferences("pref", MODE_PRIVATE),
            "history",
            tv_history.text.toString()
        )

    }

    private fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

}