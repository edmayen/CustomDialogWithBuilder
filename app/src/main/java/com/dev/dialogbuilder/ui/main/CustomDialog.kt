package com.dev.dialogbuilder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dev.dialogbuilder.R
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialog private constructor(
    private val titleText: String?,
    private val descriptionText: String?,
    private val positiveButtonText: String?,
    private val negativeButtonText: String?,
    private val isNegativeButtonVisible: Boolean?,
    private var dialogClickListener: DialogButtonClickListener? = null
): DialogFragment() {

    interface DialogButtonClickListener {
        fun onPositiveButtonClick()
        fun onNegativeButtonClick()
    }

    data class Builder(
        private var titleText: String? = null,
        private var descriptionText: String? = null,
        private var positiveButtonText: String? = null,
        private var negativeButtonText: String? = null,
        private var isNegativeButtonVisible: Boolean = true
    ){

        fun setTitleText(titleText: String) = apply { this.titleText = titleText }

        fun setDescriptionTitle(descriptionText: String) = apply { this.descriptionText = descriptionText }

        fun setPositiveButtonText(positiveButtonText: String) = apply { this.positiveButtonText = positiveButtonText }

        fun setNegativeButtonText(negativeButtonText: String) = apply { this.negativeButtonText = negativeButtonText }

        fun isNegativeButtonEnabled(isNegativeButtonVisible: Boolean) = apply { this.isNegativeButtonVisible = isNegativeButtonVisible }

        fun build() = CustomDialog(titleText, descriptionText, positiveButtonText, negativeButtonText, isNegativeButtonVisible)
    }

    fun setDialogButtonClickListener(listener: DialogButtonClickListener) = apply { dialogClickListener = listener }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_title.text = titleText
        dialog_description.text = descriptionText
        btnDialogAgree.text = positiveButtonText
        btnDialogAgree.setOnClickListener { dialogClickListener!!.onPositiveButtonClick() }
        btnDialogCancel.text = negativeButtonText
        btnDialogCancel.setOnClickListener { dialogClickListener!!.onNegativeButtonClick() }
        if(isNegativeButtonVisible!!) btnDialogCancel.visibility = View.GONE
    }

}