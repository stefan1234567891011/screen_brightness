package com.example.screenbrightness

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import  android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.attributes.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
        val brightness = brightness
        seekBar.progress = brightness

        allowWritePermission()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override  fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean){
                setBrightness(seekBar.progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}

fun Context.allowWritePermission(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val intent = Intent(
            Settings.ACTION_MANAGE_WRITE_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
    }
}

val Context.brightness:Int
    get() {
        return Settings.System.getInt(
            this.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            0
        )
    }

fun Context.setBrightness(value:Int):Unit{
    Settings.System.putInt(
        this.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS,
        value
    )
}