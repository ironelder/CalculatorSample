package com.ironelder.calculatorsample

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_display.*

class DisplayFragment : Fragment(R.layout.fragment_display) {

    private val disposeBag = CompositeDisposable()
    private val presenter = DisplayPresenter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val event = EventBus.toObservable()?.subscribe({
            val eventString = (it as String)
            if ('c'.toString() == eventString) {
                tv_history.text = ""
                tv_result.text = ""
            } else {
                tv_history.text = (tv_history.text.toString() + (it as String))
            }
        }, {})

        val result = EventBus.toObservableResult()?.subscribe({
            tv_result.text = (it as String)
        }, {})

        disposeBag.add(event!!)
        disposeBag.add(result!!)
        tv_result.text = presenter.loadDisplayData(
            activity?.getSharedPreferences("pref", MODE_PRIVATE),
            "result"
        )
        tv_history.text = presenter.loadDisplayData(
            activity?.getSharedPreferences("pref", MODE_PRIVATE),
            "history"
        )

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
}