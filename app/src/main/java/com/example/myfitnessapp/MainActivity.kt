package com.example.myfitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        checkUser()

        goAvto.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        regBtn.setOnClickListener {
            regUser()
        }
    }
    fun regUser(){
        if(loginText.text.toString().isEmpty()){
            showMessage("Поле логина постое")
            return
        }
        if(passText.text.toString().isEmpty()){
            showMessage("Поле пароля пустое")
            return
        }
        mAuth.createUserWithEmailAndPassword(loginText.text.toString(), passText.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    showMessage("Вы успешно зарегистрировались")
                    showMessage("Поддтвердите почту")

                    val user : FirebaseUser? = mAuth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                }else{
                    showMessage("Произошла ошибочка")
                }

            }

    }

    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun checkUser(){
        val user = mAuth.currentUser
        if(user != null){
            val intent = Intent(this, MainView::class.java)
            startActivity(intent)
        }
    }

}