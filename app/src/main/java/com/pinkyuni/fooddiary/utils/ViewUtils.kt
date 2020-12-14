package com.pinkyuni.fooddiary.utils

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

object ViewUtils {

    fun setErrorIsNotValid(vararg textInputLayouts: TextInputLayout) {
        for (layout in textInputLayouts) {
            layout.error = "Value is not valid"
        }
    }

    fun setErrorIfNotEqual(vararg textInputLayouts: TextInputLayout): Boolean {
        val text = textInputLayouts[0].editText!!.text
        var res = true
        for (inputLayout in textInputLayouts) {
            val editText = inputLayout.editText
            if (!TextUtils.equals(text, editText!!.text)) {
                for (layout in textInputLayouts) {
                    layout.error = "Values must be equal"
                    res = false
                }
            }
        }
        return res
    }

    fun resetTextInputErrorsOnTextChanged(vararg textInputLayouts: TextInputLayout) {
        for (inputLayout in textInputLayouts) {
            val editText = inputLayout.editText
            editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    if (inputLayout.error != null) {
                        inputLayout.error = null
                    }
                }
            })
        }
    }

    fun setErrorsIfEmpty(vararg textInputLayouts: TextInputLayout): Boolean {
        var isNotEmpty = true
        for (textInputLayout in textInputLayouts) {
            if (textInputLayout.editText?.text!!.isEmpty()) {
                textInputLayout.error = "The field is required"
                isNotEmpty = false
            }
        }
        return isNotEmpty
    }

    fun setErrorsIfInvalidEmail(vararg textInputLayouts: TextInputLayout): Boolean {
        var isValid = true
        for (textInputLayout in textInputLayouts) {
            val editText = textInputLayout.editText
            if (!TextUtils.isEmpty(editText!!.text) &&
                !Patterns.EMAIL_ADDRESS.matcher(editText.text).matches()
            ) {
                isValid = false
                textInputLayout.error = "${textInputLayout.editText?.text} is invalid email"
            }
        }
        return isValid
    }

}