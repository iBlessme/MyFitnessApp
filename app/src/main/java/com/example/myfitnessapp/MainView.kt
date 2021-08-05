package com.example.myfitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main_view.*

class MainView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)

        val BMI = BMIFragment()
        val steps = StepsFragment()
        val food = FoodFragment()

        makeCurrentFragment(BMI)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.BMIFr -> makeCurrentFragment(BMI)
                R.id.StepFr -> makeCurrentFragment(steps)
                R.id.FoodFr -> makeCurrentFragment(food)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
    }
}
