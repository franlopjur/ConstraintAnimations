package com.betabeers.constraintanimations

import android.content.Context
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater

class ExpandableCard @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    var applyInitAnimation = false
    private val constraintContainer: ConstraintLayout by bind(R.id.card__container__all)

    init {
        LayoutInflater.from(context).inflate(R.layout.card_collapsed, this, true)
        setUpAnimations()
    }

    private fun setUpAnimations() {
        setOnClickListener {
            val constraintsFinal = ConstraintSet()
            constraintsFinal.clone(context, R.layout.card_expanded)
            val constraintsInitial = ConstraintSet()
            constraintsInitial.clone(context, R.layout.card_collapsed)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(this)
                val constraint = if (applyInitAnimation) constraintsInitial else constraintsFinal
                constraint.applyTo(constraintContainer)
                applyInitAnimation = !applyInitAnimation
            }
        }
    }
}