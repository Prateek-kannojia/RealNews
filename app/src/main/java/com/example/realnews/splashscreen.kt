package com.example.realnews

import com.example.realnews.model
import androidx.recyclerview.widget.RecyclerView
import com.example.realnews.itemadapter.viewholder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.realnews.R
import com.squareup.picasso.Picasso
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import retrofit2.http.GET
import retrofit2.http.Url
import com.example.realnews.Newsmodel
import androidx.appcompat.app.AppCompatActivity
import com.example.realnews.categoriesadapter
import com.example.realnews.itemadapter
import com.example.realnews.modelcategory
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.realnews.retrofitAPI
import android.widget.Toast
import android.content.Intent
import android.os.Handler
import com.example.realnews.covid_tracker
import com.example.realnews.MainActivity

class splashscreen constructor() : AppCompatActivity() {
    /* access modifiers changed from: protected */
    // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar!!.hide()
        Handler().postDelayed(object : Runnable {
            /* class com.prateek.realnews.splashscreen.AnonymousClass1 */
            public override fun run() {
                this@splashscreen.startActivity(Intent(this@splashscreen, MainActivity::class.java))
                finish()
            }
        }, 1000)
    }
}