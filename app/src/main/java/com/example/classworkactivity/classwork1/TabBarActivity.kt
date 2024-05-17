package com.example.classworkactivity.classwork1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.classworkactivity.Fragment.addCartFragment
import com.example.classworkactivity.Fragment.homeFragment
import com.example.classworkactivity.Fragment.profileFragment
import com.example.classworkactivity.R
import com.example.classworkactivity.databinding.ActivityTabBarBinding

class TabBarActivity : AppCompatActivity() {
    lateinit var tabBarBinding: ActivityTabBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        tabBarBinding = ActivityTabBarBinding.inflate(layoutInflater)

        setContentView(tabBarBinding.root)
        tabBarBinding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.profile ->replaceFragment(profileFragment())
                R.id.addHome ->replaceFragment(homeFragment())
                R.id.addcart ->replaceFragment(addCartFragment())
                else ->{}
            }
            true

        }

    }
    private fun replaceFragment (fragment: Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }
}

