package com.capstone.recyscan.components

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.CustomViewEditTextBinding
import com.google.android.material.textfield.TextInputLayout

class CustomViewEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomViewEditTextBinding =
        CustomViewEditTextBinding.inflate(LayoutInflater.from(context), this)

    var hintText: String
        get() = binding.textInputEditText.hint.toString()
        set(value) {
            binding.textInputLayout.hint = value
        }


    var inputText: String
        get() = binding.textInputEditText.text.toString()
        set(value) {
            binding.textInputEditText.setText(value)
        }


    private var isPasswordInputType: Boolean = false
        set(value) {
            field = value
            setupInputType()
        }

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomViewEditText)
        hintText = attributes.getString(R.styleable.CustomViewEditText_hintText) ?: ""
        isPasswordInputType =
            attributes.getBoolean(R.styleable.CustomViewEditText_isPassword, false)
        attributes.recycle()
        setupInputType()
    }

    private fun setupInputType() {
        if (isPasswordInputType) {
            binding.textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            binding.textInputEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            binding.textInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            binding.textInputEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
    }
}
