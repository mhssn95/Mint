package io.mhssn.mint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.mhssn.mint.Store.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val store = SharedPreferences(this)

        val appValues = Mint(store).create(AppValues::class)
    }
}