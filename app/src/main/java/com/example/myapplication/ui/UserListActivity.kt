package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.database.User
import com.example.myapplication.ui.adapter.UserListAdapter
import com.example.myapplication.util.MyApp
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setData()
    }

    private fun setData() {

        recycler_view.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false);

        MyApp.userDao.getUserList().observe(this, Observer
        {
            val userList = it
            recycler_view.adapter =
                UserListAdapter(it as ArrayList<User>, applicationContext, myCallback =
                {
                    CoroutineScope(Dispatchers.IO).launch {
                        MyApp.userDao.deleteById(userList.get(it).id)
                    }
                })

        })


    }

}
