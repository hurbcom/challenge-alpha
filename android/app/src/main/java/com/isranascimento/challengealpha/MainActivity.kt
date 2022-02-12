package com.isranascimento.challengealpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isranascimento.challengealpha.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.root_container, HomeFragment())
            .commit()
    }
}