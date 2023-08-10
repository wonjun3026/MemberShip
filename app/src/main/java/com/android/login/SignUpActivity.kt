package com.android.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var name = findViewById<EditText>(R.id.editTextText)
        var id = findViewById<EditText>(R.id.editTextText2)
        var password = findViewById<EditText>(R.id.editTextText5)
        var age = findViewById<EditText>(R.id.editTextText3)
        var mbti = findViewById<EditText>(R.id.editTextText4)

        val btn = findViewById<Button>(R.id.memberShipCmp)
        btn.setOnClickListener {
            if (name.text.isNullOrEmpty() || id.text.isNullOrEmpty() || password.text.isNullOrEmpty() || age.text.isNullOrEmpty() || mbti.text.isEmpty()){
                toast("내용을 전부 입력해 주세요")
            }
            else{
                Member.createMember(name.text.toString(), id.text.toString(), password.text.toString(), mbti.text.toString(), age.text.toString())
                val homeIntent = Intent(this, SignInActivity::class.java)
                    .putExtra("id", id.text.toString())
                    .putExtra("password", password.text.toString())
                setResult(RESULT_OK, homeIntent)
                toast("회원 가입 완료")
                finish()
            }
        }
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}