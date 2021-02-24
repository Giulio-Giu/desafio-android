package com.picpay.desafio.android.ui.activities

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.services.PicPayService
import com.picpay.desafio.android.R
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.services.RetrofitInitializer
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        val s = RetrofitInitializer(this).servicePicPay()
        val call = s.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                val message = getString(R.string.error)

                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE

                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                progressBar.visibility = View.GONE

                val listUser = response.body()

                if (response.isSuccessful && listUser != null) {
                    adapter.users = listUser
                } else {

                    val message = getString(R.string.error)
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
