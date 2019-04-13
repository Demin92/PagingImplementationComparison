package com.demin.pagingimplementationcomparison

import android.app.Application
import com.demin.pagingimplementationcomparison.di.DI

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        DI.init()
    }
}