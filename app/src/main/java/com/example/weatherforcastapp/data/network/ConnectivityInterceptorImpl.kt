package com.example.weatherforcastapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherforcastapp.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
    context: Context  // will give syaem service which will tell whthernet is off or on
) : ConnectivityInterceptor {

    private val appContext=context.applicationContext // we only need application context
    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
       if(!isOnline())
       {
           throw NoConnectivityException()
       }
        return chain.proceed(chain.request())

    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isOnline(): Boolean {
        val connectivityManager= appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.isDefaultNetworkActive
        return networkInfo

    }
}