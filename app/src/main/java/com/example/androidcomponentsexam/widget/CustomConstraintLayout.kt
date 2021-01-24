package com.example.androidcomponentsexam.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout

class CustomConstraintLayout(
    context: Context, attrs: AttributeSet?
): ConstraintLayout(context, attrs) {
    private val TAG = "LOG>> [CustomConstraintLayout][${hashCode()}]"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i(TAG, "* onMeasure *")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.i(TAG, "** onLayout **")
        super.onLayout(changed, l, t, r, b)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.i(TAG, "*** onDraw ***")
        super.onDraw(canvas)
    }

    override fun onAttachedToWindow() {
        Log.i(TAG, "========= onAttachedToWindow =========")
        super.onAttachedToWindow()

    }

    override fun onDetachedFromWindow() {
        Log.i(TAG, "========= onDetachedFromWindow =========")
        super.onDetachedFromWindow()
    }
}