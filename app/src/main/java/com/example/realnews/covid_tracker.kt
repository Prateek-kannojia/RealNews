package com.example.realnews

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.realnews.R
import kotlin.Throws
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest
import com.android.volley.VolleyError
import android.widget.Toast
import java.text.NumberFormat

class covid_tracker : AppCompatActivity() {
    var totalactive: TextView? = null
    var totalcase: TextView? = null
    var totaldeath: TextView? = null
    var totaldischarged: TextView? = null
    var url = "https://corona.lmao.ninja/v2/countries"

    /* access modifiers changed from: protected */
    // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_tracker)
        totalcase = findViewById(R.id.cases)
        totaldeath = findViewById(R.id.deaths)
        totaldischarged = findViewById(R.id.recovered)
        totalactive = findViewById(R.id.active)
        fetchdata()
    }

    /* access modifiers changed from: private */ /* access modifiers changed from: public */
    @Throws(JSONException::class)
    private fun getposition(jsonArray: JSONArray): JSONObject? {
        for (j in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(j)
            if (jsonObject.getString("country") == "India") {
                return jsonObject
            }
        }
        return null
    }

    private fun fetchdata() {
        // com.android.volley.Response.ErrorListener
        Volley.newRequestQueue(this).add(StringRequest(0, url, { response: String? ->
            try {
                val INCovid = getposition(JSONArray(response))
                val cases = INCovid!!.getString("cases").toInt()
                val deaths = INCovid.getString("deaths").toInt()
                val recovered = INCovid.getString("recovered").toInt()
                val active = INCovid.getString("active").toInt()
                totalcase!!.text = NumberFormat.getInstance().format(
                    cases.toLong()
                )
                totaldeath!!.text = NumberFormat.getInstance().format(
                    deaths.toLong()
                )
                totaldischarged!!.text = NumberFormat.getInstance().format(
                    recovered.toLong()
                )
                totalactive!!.text = NumberFormat.getInstance().format(
                    active.toLong()
                )
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }) { error: VolleyError? ->
            Toast.makeText(
                this@covid_tracker,
                "somthing went wrong",
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}