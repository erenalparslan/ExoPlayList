package com.erenalparslan.exoplaylist

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100 // Herhangi bir sayı olabilir

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("create Activity")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
                requestPermissions(permissions, REQUEST_CODE)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // İzin verildi
                // Burada videolarınızı yükleyebilirsiniz
            } else {
                // İzin verilmedi
                // Uygulama dosyalarına erişimi engelleyen bir açıklama gösterin veya
                // yeniden izin isteyin.
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        super.onResume()
        println("resume Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("destroy Activity")
    }

    override fun onPause() {
        super.onPause()
        println("pause Activity")
    }

    override fun onStop() {
        super.onStop()
        println("stop Activity")
    }

    override fun onRestart() {
        super.onRestart()
        println("restart Activity")
    }

    override fun onStart() {
        super.onStart()
        println("start Activity")
    }

}