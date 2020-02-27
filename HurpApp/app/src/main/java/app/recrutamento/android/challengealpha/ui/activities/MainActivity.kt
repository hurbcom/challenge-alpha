package app.recrutamento.android.challengealpha.ui.activities

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import app.recrutamento.android.challengealpha.R
import app.recrutamento.android.challengealpha.model.AmenityIcon
import app.recrutamento.android.challengealpha.ui.hotellist.HotelListFragment
import app.recrutamento.android.challengealpha.util.navigateToFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    lateinit var toolbar: Toolbar
    var isGridMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        txtSubtitle.setOnClickListener { withItems() }
        img_grid_on_off.setOnClickListener{ changeToListOrGrid() }
        navigateToFragment(R.id.content_frame, HotelListFragment.newInstance(), "MainActivity")
    }

    fun changeToListOrGrid() {
        var bundle = Bundle()
        if(!isGridMode){
            img_grid_on_off.setImageDrawable(getDrawable(R.drawable.ic_grid_on))
            isGridMode = true
        }else {
            img_grid_on_off.setImageDrawable(getDrawable(R.drawable.ic_grid_off))
            isGridMode = false
        }
        bundle.putBoolean("isGridMode",isGridMode)
        navigateToFragment(R.id.content_frame, HotelListFragment.newInstance(), "MainActivity",bundle)
    }


    fun withItems() {

        val items = arrayOf(AmenityIcon.toString())
        val builder = AlertDialog.Builder(this)
        builder.setTitle("List of Items")

            .setItems(items, null)

        builder.setNegativeButton("CANCEL", null)
        builder.setIcon(resources.getDrawable(R.drawable.ic_subtitle_icon, theme))

        val alertDialog = builder.create()

        alertDialog.show()

        val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        button.setBackgroundColor(Color.BLACK)
        button.setPadding(0, 0, 20, 0)
        button.setTextColor(Color.WHITE)
    }

}
