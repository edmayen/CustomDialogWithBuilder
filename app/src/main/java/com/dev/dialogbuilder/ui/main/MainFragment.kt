package com.dev.dialogbuilder.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dev.dialogbuilder.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCustomDialog1.setOnClickListener {
            val customDialog = CustomDialog.Builder()
                .setTitleText("Do you want to delete it?")
                .setDescriptionTitle("It will delete all your data. ")
                .setPositiveButtonText("Sure")
                .setNegativeButtonText("Cancel")
                .isNegativeButtonEnabled(false)
                .build()

            customDialog.show(fragmentManager!!, "customdialog1")
            customDialog.isCancelable = false
            customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                override fun onPositiveButtonClick() {
                    Toast.makeText(requireContext(), "Agree", Toast.LENGTH_LONG).show()
                }

                override fun onNegativeButtonClick() {
                    customDialog.dismiss()
                }
            })
        }

        btnCustomDialog2.setOnClickListener {
            val customDialog2 = CustomDialog.Builder()
                .setTitleText("Do you want to save the information?")
                .setDescriptionTitle("It will save it on your phone. ")
                .setPositiveButtonText("Ok")
                .setNegativeButtonText("No")
                .isNegativeButtonEnabled(false)
                .build()

            customDialog2.show(fragmentManager!!, "customdialog2")
            customDialog2.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                override fun onPositiveButtonClick() {
                    Toast.makeText(requireContext(), "Ok", Toast.LENGTH_LONG).show()
                }

                override fun onNegativeButtonClick() {
                    customDialog2.dismiss()
                }
            } )
        }
    }

}