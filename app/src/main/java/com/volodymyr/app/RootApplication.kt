package com.volodymyr.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RootApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
}