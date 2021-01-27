package com.example.permissions

import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

//Basically this video tells about the permissions required from the UI to interact such as if
// the network connection is made it shows that the network connection is made
// URL: https://developer.android.com/training/permissions/requesting
//the permissions are actually given in manifest file
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt1.setOnClickListener{
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected:Boolean= activeNetwork?.isConnectedOrConnecting == true
            if(isConnected){
                tv1.setText("Connected")
            }
            else{
                tv1.setText("Disconnected")
            }
        }

        //DANGEROUS_PERMISSIONS

        bt2.setOnClickListener{
            val mobile=et1.text.toString()
            val d= Intent()
            //in java activitycompat.checkselfpermission replaced by contextcompat.
            val perm=ActivityCompat.checkSelfPermission(this, CALL_PHONE)
            if (perm==PackageManager.PERMISSION_GRANTED){
                callphone()
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE),121)
            }
            d.action= Intent.ACTION_CALL
            d.data= Uri.parse("tel:$mobile")
            d.putExtra(Intent.EXTRA_SUBJECT,"Implicit Intents")
            startActivity(d)
        }
    }

    private fun callphone() {
        val mobile=et1.text.toString()
        val d= Intent()
        d.action= Intent.ACTION_CALL
        d.data= Uri.parse("tel:$mobile")
        d.putExtra(Intent.EXTRA_SUBJECT,"Implicit Intents")
        startActivity(d)
    }
}
