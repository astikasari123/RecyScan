package com.capstone.recyscan.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.capstone.recyscan.R
import com.google.android.material.card.MaterialCardView

@SuppressLint("CustomViewStyleable")
class ComponentCustomCardButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var imageView: ImageView
    private var textView: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_view_card_button, this, true)
        imageView = view.findViewById(R.id.iv_to_set)
        textView = view.findViewById(R.id.title_to_set)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomViewCardButton, 0, 0)
            val text = typedArray.getString(R.styleable.CustomViewCardButton_cardText)
            val imageDrawable = typedArray.getDrawable(R.styleable.CustomViewCardButton_cardImage)

            textView.text = text
            imageView.setImageDrawable(imageDrawable)

            typedArray.recycle()
        }
    }

    fun setText(text: String) {
        textView.text = text
    }

    fun setImageDrawable(drawable: Drawable) {
        imageView.setImageDrawable(drawable)
    }
}