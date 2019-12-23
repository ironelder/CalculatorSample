package com.ironelder.calculatorsample

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_calculator.*


class CalculatorFragment : Fragment(R.layout.fragment_calculator) {
    private val presenter = CalculatorPresenter()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var calculatorData = StringBuilder()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayoutAttribute()
        loadPreferenceData()
    }

    private fun initLayoutAttribute() {
        val viewArray = arrayOf(
            tv_num_zero,
            tv_num_one,
            tv_num_two,
            tv_clear,
            tv_divide,
            tv_equal,
            tv_minus,
            tv_multiple,
            tv_plus,
            tv_remainder
        )

        viewArray.forEach { view ->
            presenter.getClickEventObservable(view)?.subscribe {
                presenter.clickButton(
                    calculatorData, (it as TextView).text.toString(),
                    if (it.text.toString().toIntOrNull() == null) BUTTONTYPE.OPERATOR else BUTTONTYPE.NUMBER
                )
            }?.addDisposable()
        }
    }

    private fun loadPreferenceData() {

        presenter.loadDisplayData(
            activity?.getSharedPreferences("pref", MODE_PRIVATE),
            "result", calculatorData
        )
        presenter.loadDisplayData(
            activity?.getSharedPreferences("pref", MODE_PRIVATE),
            "history", calculatorData
        )
    }


    private fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }
}