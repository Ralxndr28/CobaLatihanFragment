package coba.c14220310.cobalatihanfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    var finalScore: Int = 0
    var maxRandomNumber: Int = 5 // Default batas angka acak di Fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default fragment to Fragment1
        if (savedInstanceState == null) {
            setCurrentFragment(Fragment1())
        }

        // Set up top menu navigation for fragment switching
        setupTopMenu()
    }


    private fun setupTopMenu() {
        // Get reference to the top menu
        val topNavigationView = findViewById<Toolbar>(R.id.top_navigation)
        topNavigationView.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.fragment_container -> {
                    setCurrentFragment(Fragment1())
                    true
                }
                R.id.fragment_container -> {
                    setCurrentFragment(Fragment2())
                    true
                }
                R.id.fragment_container -> {
                    setCurrentFragment(Frag3())
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}