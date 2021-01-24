package com.example.androidcomponentsexam.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout

class CustomLinearLayout(
    context: Context?, attrs: AttributeSet?
): LinearLayout(context, attrs) {
    private val TAG = "LOG>> [CustomLinearLayout][${hashCode()}]"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.e(TAG, "* onMeasure BEFORE super($widthMeasureSpec, $heightMeasureSpec) *")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e(TAG, "* onMeasure AFTER super($widthMeasureSpec, $heightMeasureSpec) *")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.i(TAG, "** onLayout **")
        super.onLayout(changed, l, t, r, b)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.i(TAG, "*** onDraw ***")
        super.onDraw(canvas)
    }
}