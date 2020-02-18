package viniciusapp.com.br.hurbtest.ui

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class HurbApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}