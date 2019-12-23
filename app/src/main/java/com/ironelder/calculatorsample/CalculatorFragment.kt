package com.ironelder.calculatorsample

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
        tv_plus.setOnClickListener {
            presenter.clickCalculator(tv_plus.text.toString())
        }

        tv_minus.setOnClickListener {
            presenter.clickCalculator(tv_minus.text.toString())
        }

        tv_multiple.setOnClickListener {
            presenter.clickCalculator(tv_multiple.text.toString())
        }

        tv_divide.setOnClickListener {
            EventBus.send(tv_divide.text)
        }

        tv_remainder.setOnClickListener {
            EventBus.send(tv_remainder.text)
        }

        tv_clear.setOnClickListener {
            presenter.clickCalculator(tv_clear.text.toString())
        }

        tv_num_zero.setOnClickListener {
            presenter.clickNumber(tv_num_zero.text.toString())
        }

        tv_num_one.setOnClickListener {
            presenter.clickNumber(tv_num_one.text.toString())
        }

        tv_num_two.setOnClickListener {
            presenter.clickNumber(tv_num_two.text.toString())
        }

        tv_equal.setOnClickListener {
            //            presenter.clickCalculator(tv_equal.text.toString())

        }


        //TODO : Refactoring
        initLayoutAttribute()
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


    private fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }
}