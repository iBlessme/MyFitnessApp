package com.example.myfitnessapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_b_m_i.*

class BMIFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_m_i, container, false)
    }

    override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()

        bmitext.isVisible = false
        disclamer.isVisible = false

        Exit.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)

        }
        calculateBtn.setOnClickListener {
            if(edtHeight.text != null && edtWeight.text != null){
                cadChanger()
            }else{
                Toast.makeText(this.context, "Заполните данные", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun cadChanger() {
        bmitext.visibility = View.GONE
        disclamer.visibility = View.GONE
        res.visibility = View.VISIBLE
        feedback.visibility = View.VISIBLE
        yourBmi.visibility = View.VISIBLE
        val height: Float = edtHeight.text.toString().toFloat()
        val weight: Float = edtWeight.text.toString().toFloat()
        val ans: Float = (weight) / ((height / 100) * (height / 100))
        val BMI: Double = Math.round(ans * 10.0) / 10.0
        res.text = BMI.toString()
        if (BMI < 18.5) {
            feedback.text = "У вас недостаточный вес. Попробуйте набрать вес."
            feedback.setTextColor(Color.RED)
            res.setTextColor(Color.RED)
        } else if (BMI >= 25) {
            feedback.text = "У вас избыточный вес. Постарайтесь похудеть."
            feedback.setTextColor(Color.RED)
            res.setTextColor(Color.RED)
        } else {
            feedback.text = "У вас нормальная масса тела. Наслаждайтесь !"
            feedback.setTextColor(Color.GREEN)
            yourBmi.setTextColor(Color.GREEN)
            res.setTextColor(Color.GREEN)
        }
    }
}