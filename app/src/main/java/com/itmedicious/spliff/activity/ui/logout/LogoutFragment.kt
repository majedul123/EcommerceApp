package com.itmedicious.spliff.activity.ui.logout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.itmedicious.spliff.activity.LoginActivity
import com.itmedicious.spliff.activity.MainActivity
import com.itmedicious.spliff.api.UtilsApi
import com.itmedicious.spliff.databinding.FragmentLogoutBinding
import com.itmedicious.spliff.model.LogIn
import com.itmedicious.spliff.model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null

    private val binding get() = _binding!!
    val PREFS_NAME = "LoginPrefs"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val settings = context?.getSharedPreferences(PREFS_NAME, 0)
        var accessToken: String? = "Bearer " + settings?.getString("token", "")

        logout(accessToken)
        val editor = settings?.edit()
        editor?.remove("logged")
        editor?.remove("access_token")
        editor?.apply()

        startActivity(Intent(context, LoginActivity::class.java))


        return binding.root
    }

    private fun logout(accessToken: String?) {

        UtilsApi.apiService.logOut(accessToken)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {

                }
            })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}