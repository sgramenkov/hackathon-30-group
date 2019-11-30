package com.example.putinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.putinder.ui.tinder.TinderFragment

class FragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
        val fragment = TinderFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment)

    }
}
