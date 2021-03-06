package com.example.androidcomponentsexam.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView

class CustomEditText(
    context: Context, attrs: AttributeSet?
): AppCompatEditText(context, attrs) {
    private val TAG = "LOG>> [EditText]"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i(TAG, "* onMeasure ($widthMeasureSpec, $heightMeasureSpec) *")
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