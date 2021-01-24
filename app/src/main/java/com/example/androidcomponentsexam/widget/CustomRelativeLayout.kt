package com.example.androidcomponentsexam.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout

class CustomRelativeLayout(
    context: Context?, attrs: AttributeSet?
): RelativeLayout(context, attrs) {
    private val TAG = "LOG>> [CustomRelativeLayout][${hashCode()}]"

    override fun measureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.measureChildren(widthMeasureSpec, heightMeasureSpec)
        Log.i(TAG, "* measureChildren *")
    }

    override fun measureChild(
        child: View?,
        parentWidthMeasureSpec: Int,
        parentHeightMeasureSpec: Int
    ) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec)
        Log.i(TAG, "* measureChild *")
    }

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