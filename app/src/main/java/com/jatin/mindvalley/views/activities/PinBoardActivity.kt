package com.jatin.mindvalley.views.activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jatin.mindvalley.R
import com.jatin.mindvalley.databinding.ActivityPinBoardBinding
import com.jatin.mindvalley.views.fragments.PinboardViewFragment
import kotlinx.android.synthetic.main.activity_pin_board.*

class PinBoardActivity : AppCompatActivity() {

    private lateinit var pinBoardBinding: ActivityPinBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pinBoardBinding = DataBindingUtil.setContentView(this, R.layout.activity_pin_board)

        setSupportActionBar(toolbar)

        // Init Open
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.mainFrameLayout,
            PinboardViewFragment()
        ).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pin_board, menu)
        return true
    }
}
