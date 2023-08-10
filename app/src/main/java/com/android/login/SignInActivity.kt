package com.android.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class SignInActivity : AppCompatActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        var memberList = Member.retrieveMembers()
        var i: Int

        var text1 = findViewById<EditText>(R.id.edit2)
        var text2 = findViewById<EditText>(R.id.edit4)
        val btn1 = findViewById<Button>(R.id.login)
        val btn2 = findViewById<Button>(R.id.membership)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it. resultCode == RESULT_OK) {
                val id = it.data?.getStringExtra("id") ?: ""
                val password = it.data?. getStringExtra("password") ?: ""
                text1.setText(id)
                text2.setText(password)
            }
        }

        btn1.setOnClickListener {
            if (text1.text.isNullOrEmpty() && text2.text.isNullOrEmpty()){
                toast("아이디와 비번을 입력 하여 주세요")
            }
            else{
                i = 0
                for ((index, member2) in memberList.withIndex()){
                    if (member2.id == text1.text.toString()){
                        i = index
                        break
                    }
                }

                if (memberList[i].password == text2.text.toString()){
                    toast("로그인 완료")
                    val loginIntent = Intent(this, HomeActivity::class.java)
                    loginIntent.putExtra("index", i)
                    startActivity(loginIntent)

                }
                else {
                    toast("아이디 또는 비밀번호를 잘 못 입력하셨습니다.")
                }
            }


        }
        btn2.setOnClickListener {
            val memberShipIntent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(memberShipIntent)
        }
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}