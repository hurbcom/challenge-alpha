package br.com.hu.allyson.desafiohu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.hu.allyson.desafiohu.ui.hotels.HotelsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HotelsFragment.newInstance())
                .commitNow()
        }
    }

}
