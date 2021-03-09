package com.example.chat.authentication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chat.R
import com.example.chat.authentication.login.ui.LoginFragment
import com.example.chat.authentication.singup.ui.SignUpFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {

    private val collectionPagerAdapter = CollectionPagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        viewPager.adapter = collectionPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                1 -> tab.text = "Вход"
                else -> tab.text = "Регистрация"
            }
        }.attach()

    }

    inner class CollectionPagerAdapter(fragment: AppCompatActivity) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> LoginFragment()
                else -> SignUpFragment()
            }
        }
    }
}