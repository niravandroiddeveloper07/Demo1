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
import kotlinx.android.synthetic.main.item_user_list.view.*

class ImagePickerActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        txtAdd.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when(p0!!.id)
        {
            R.id.txtAdd->
            {
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

}
