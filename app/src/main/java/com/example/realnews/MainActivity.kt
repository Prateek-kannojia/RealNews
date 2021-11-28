package com.example.realnews

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),categoriesadapter.clickinterface,
    itemadapter.clickinterface {
    private lateinit var Itemrecyclerview: RecyclerView
    private lateinit var categoriesadapter: categoriesadapter
    private lateinit var categoryrecyclerview: RecyclerView
    private lateinit var covid: Button
    private lateinit var itemadapter: itemadapter
    private lateinit var modelList: ArrayList<model>
    private lateinit var modelcategoryList: ArrayList<modelcategory>
    /* access modifiers changed from: protected */
    @SuppressLint("NotifyDataSetChanged")  // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Itemrecyclerview = findViewById(R.id.itemrecyclerview)
        categoryrecyclerview = findViewById(R.id.categories)
        covid = findViewById(R.id.covid_tracker)
        modelcategoryList= ArrayList()
        modelList= ArrayList()
        getcatgories()
        fetchnews("All")
        itemadapter = itemadapter(this,this)
        categoriesadapter = categoriesadapter(modelcategoryList,this,this)
        Itemrecyclerview.layoutManager=LinearLayoutManager(this)
        Itemrecyclerview.adapter = itemadapter
        itemadapter.notifyDataSetChanged()
        categoryrecyclerview.adapter = categoriesadapter
        val recyclerView: RecyclerView = Itemrecyclerview
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, 1))
        covid.setOnClickListener { covidtracker() }
    }

    private fun getcatgories() {
        modelcategoryList.add(
            modelcategory(
                "All",
                "https://images.unsplash.com/photo-1584359983106-ef9366f27454?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fG5ld3N8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
        modelcategoryList.add(
            modelcategory(
                "technology",
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
        modelcategoryList.add(
            modelcategory(
                "science",
                "https://images.unsplash.com/photo-1567427018141-0584cfcbf1b8?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8c2NpZW5jZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
        modelcategoryList.add(
            modelcategory(
                "sports",
                "https://images.unsplash.com/photo-1503525523076-ca4aa2e47535?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDR8fHNwb3J0c3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
        modelcategoryList.add(
            modelcategory(
                "business",
                "https://images.unsplash.com/photo-1579532537598-459ecdaf39cc?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzB8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
        modelcategoryList.add(
            modelcategory(
                "entertainment",
                "https://images.unsplash.com/photo-1598899134739-24c46f58b8c0?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
        modelcategoryList.add(
            modelcategory(
                "health",
                "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aGVhbHRofGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"
            )
        )
    }

    private fun fetchnews(category: String?) {
        modelList.clear()
        val baseurl="https://gnews.io/"
        val token="353267b6eed4887c4af82c9cfb565898"
        val categoryurl= "https://gnews.io/api/v4/top-headlines?token=$token&lang=en&topic=$category"
        val url="https://gnews.io/api/v4/top-headlines?token=$token&lang=en&topic=world"
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitAPI: retrofitAPI = retrofit.create(retrofitAPI::class.java)
        val call: Call<Newsmodel>?
        if ((category == "All")) {
            call = retrofitAPI.getallNews(url)
        }
        else{
            call=retrofitAPI.getNewsbycategory(categoryurl)
        }
        call.enqueue(object : Callback<Newsmodel> {
            /* class com.prateek.realnews.MainActivity.AnonymousClass2 */
            // retrofit2.Callback
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Newsmodel>, response: Response<Newsmodel>) {
                val models = response.body().articles
                for (i in 0 until models.size) {
                    val news= model(
                        models[i].title,
                        models[i].url,
                        models[i].name,
                        models[i].image
                    )
                    modelList.add(news)
                }
                itemadapter.updateNews(modelList)
            }

            // retrofit2.Callback
            override fun onFailure(call: Call<Newsmodel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "somthing went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
    /* access modifiers changed from: private */ /* access modifiers changed from: public */
    private fun covidtracker() {
        startActivity(Intent(this, covid_tracker::class.java))
    }

    // com.prateek.realnews.categoriesadapter.clickinterface
    override fun oncategoryclick(position: Int) {
        fetchnews(modelcategoryList[position].category)
    }
    override fun onnewsclick(position: Int){
        val builder=CustomTabsIntent.Builder()
        val customtabsintent=builder.build()
        customtabsintent.launchUrl(this, Uri.parse(modelList[position].url))
    }
}