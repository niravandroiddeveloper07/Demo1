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

import java.text.SimpleDateFormat


class ImagePickerActivity : AppCompatActivity(), View.OnClickListener {

    private val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        txtAdd.setOnClickListener(this)


        val date: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()

            }

        etDate.setOnTouchListener { view, _ ->
            DatePickerDialog(
                this@ImagePickerActivity, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            return@setOnTouchListener false
        }

    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etDate.setText(sdf.format(myCalendar.time))
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
                                .apply(RequestOptions.circleCropTransform())
                                .into(imgChooseImage);
                        }
                    }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart() {
        super.onStart()
    }


}
