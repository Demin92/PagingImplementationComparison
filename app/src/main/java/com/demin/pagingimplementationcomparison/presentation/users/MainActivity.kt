package com.demin.pagingimplementationcomparison.presentation.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demin.pagingimplementationcomparison.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, UsersFragment.newInstance()).commit()
    }
}
