package viniciusapp.com.br.hurbtest.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import viniciusapp.com.br.hurbtest.R

class SplashScreenActivity: BaseActivity() {

    private val SPLASH_TIME_OUT : Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}