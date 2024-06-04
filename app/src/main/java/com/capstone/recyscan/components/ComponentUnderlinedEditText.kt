package com.capstone.recyscan.components

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.CustomViewUnderlinedEditTextBinding

@SuppressLint("CustomViewStyleable")
class ComponentUnderlinedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: CustomViewUnderlinedEditTextBinding =
        CustomViewUnderlinedEditTextBinding.inflate(
            LayoutInflater.from(context), this
        )
    private var edtLabel: String
        get() = binding.tvHeadlineEdt.text.toString()
        set(value) {
            binding.tvHeadlineEdt.text = value
        }
    private var setTextValue: String
        get() = binding.edtField.text.toString()
        set(value) {
            binding.edtField.setText(value)
        }
    private var isPasswordInputType: Boolean = false
        set(value) {
            field = value
//            setTypeInput()
            setupInputType()
        }

    init {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomViewUnderlinedEditText)
        edtLabel = attributes.getString(R.styleable.CustomViewUnderlinedEditText_edtLabel) ?: ""
        isPasswordInputType =
            attributes.getBoolean(R.styleable.CustomViewUnderlinedEditText_isPasswordType, false)
        setTextValue = attributes.getString(R.styleable.CustomViewUnderlinedEditText_setTextValue) ?: ""
        attributes.recycle()
        setupInputType()
    }

    private fun setupInputType() {
        binding.edtField.apply {
            inputType = if (isPasswordInputType) {
                TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
            }else {
                TYPE_CLASS_TEXT
            }
        }
    }

}