package com.example.myfitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        goReg.setOnClickListener {
            goRegistration()
        }
        avtoBtn.setOnClickListener {
            avtoComplete()
        }
    }

    private fun avtoComplete() {
        if(loginText.text != null && passText.text != null){

            mAuth.signInWithEmailAndPassword(loginText.text.toString(), passText.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){

                        val user = mAuth.currentUser
                        if (user != null) {
                            updateUser(user)
                        }

                    }else{
                        Toast.makeText(this, "Вы ввели некоректные данные!", Toast.LENGTH_LONG).show()
                        updateUser(null)
                    }
                }



        }else{
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUser(currentUser: FirebaseUser?) {
        if(currentUser!=null)
        {  if(currentUser.isEmailVerified) {
            startActivity(Intent(this, MainView::class.java))
            finish()
        }
        else
        {
            Toast.makeText(this,"Подтвердите вашу почту",Toast.LENGTH_LONG).show();
        }
        }
        else
        {
            Toast.makeText(baseContext,"Произошла ошибка",Toast.LENGTH_SHORT).show()
        }
    }

    private fun goRegistration() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}