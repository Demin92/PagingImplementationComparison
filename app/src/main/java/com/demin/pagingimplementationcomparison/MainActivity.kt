package com.demin.pagingimplementationcomparison

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val reposRepository = ReposRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        search.setOnClickListener {
            reposRepository.getRepositories(search_text.text.toString())
                    .subscribe({ repos->
                        Log.d("", "")
                    }, {
                        Log.d("", "")
                    })
        }
    }
}
