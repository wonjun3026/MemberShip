package com.android.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class HomeActivityModify : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_modify)

        var name = findViewById<EditText>(R.id.editName)
        var id = findViewById<EditText>(R.id.editId)
        var password = findViewById<EditText>(R.id.editPassword)
        var age = findViewById<EditText>(R.id.editAge)
        var mbti = findViewById<EditText>(R.id.editMbti)
        var memberList = Member.retrieveMembers()

        val btn4 = findViewById<Button>(R.id.memberShipCmp2)

        val index = intent.getIntExtra("index", 0)
        name.setText(memberList[index].name)
        id.setText(memberList[index].id)
        password.setText(memberList[index].password)
        mbti.setText(memberList[index].mbti)
        age.setText(memberList[index].age)

        btn4.setOnClickListener {
            if (name.text.isNullOrEmpty() || id.text.isNullOrEmpty() || password.text.isNullOrEmpty() || age.text.isNullOrEmpty() || mbti.text.isEmpty()){
                toast("내용을 입력 하여 주세요")
            }
            else{
                Member.updateMember(name.text.toString(), id.text.toString(), password.text.toString(), mbti.text.toString(), age.text.toString(), index)
                val homeIntent = Intent(this, SignInActivity::class.java)
                    .putExtra("name", name.text.toString())
                    .putExtra("id", id.text.toString())
                    .putExtra("password", password.text.toString())
                    .putExtra("age", age.text.toString())
                    .putExtra("mbti", mbti.text.toString())
                setResult(RESULT_OK, homeIntent)
                toast("회원 수정 완료")
                finish()
            }
        }
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}