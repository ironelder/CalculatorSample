package com.ironelder.calculatorsample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {
    private val presenter = CalculatorPresenter()

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
            presenter.clickCalculator(tv_equal.text.toString())
        }
    }

}