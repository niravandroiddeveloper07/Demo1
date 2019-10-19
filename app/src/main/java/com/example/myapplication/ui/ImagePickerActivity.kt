package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_image_picker.*

import java.util.*
import android.app.DatePickerDialog
import com.example.myapplication.util.DateUtil

import java.text.SimpleDateFormat


class ImagePickerActivity : AppCompatActivity(), View.OnClickListener {

    private val myCalendar = Calendar.getInstance()
    lateinit var date:DatePickerDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        txtAdd.setOnClickListener(this)

         date = DatePickerDialog(
            this@ImagePickerActivity,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                date.dismiss()
                updateLabel()

            },
            myCalendar
                .get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        etDate.setOnTouchListener { view, _ ->
            date.show()
            return@setOnTouchListener false
        }

    }

    private fun updateLabel() {

        etDate.setText(DateUtil.formatDate(myCalendar.time))
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.txtAdd -> {
                RxPaparazzo.single(this)
                    .usingGallery()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {

                        if (it.resultCode() == RESULT_OK) {
                            Glide.with(applicationContext)
                                .load(it.data().file)
                                .into(imgChooseImage);
                        }
                    }
            }
        }
    }

}
