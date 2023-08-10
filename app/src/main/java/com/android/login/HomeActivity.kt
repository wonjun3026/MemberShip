package com.android.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var memberList = Member.retrieveMembers()

        val textViewName = findViewById<TextView>(R.id.textView11)
        val textViewId = findViewById<TextView>(R.id.textView13)
        val textViewPassword = findViewById<TextView>(R.id.textView15)
        val textViewMbti = findViewById<TextView>(R.id.textView16)
        val textViewAge = findViewById<TextView>(R.id.textView17)

        val btn = findViewById<ConstraintLayout>(R.id.close2)
        val btn2 = findViewById<Button>(R.id.btn22)
        val btn3 = findViewById<Button>(R.id.btn23)

        val index = intent.getIntExtra("index", 0)
        textViewName.text = memberList[index].name
        textViewId.text = memberList[index].id
        textViewPassword.text = memberList[index].password
        textViewMbti.text = memberList[index].mbti
        textViewAge.text = memberList[index].age

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it. resultCode == RESULT_OK) {
                val name = it.data?.getStringExtra("name") ?: ""
                val id = it.data?.getStringExtra("id") ?: ""
                val password = it.data?. getStringExtra("password") ?: ""
                val age = it.data?. getStringExtra("age") ?: ""
                val mbti = it.data?.getStringExtra("mbti") ?: ""
                textViewName.text = name
                textViewId.text = id
                textViewPassword.text = password
                textViewAge.text = age
                textViewMbti.text = mbti
            }
        }

        btn.setOnClickListener {
            finish()
        }

        btn2.setOnClickListener {
            val memberModifyIntent = Intent(this, HomeActivityModify::class.java)
            memberModifyIntent.putExtra("index", index)
            activityResultLauncher.launch(memberModifyIntent)
        }

        btn3.setOnClickListener {
            Member.deleteMember(index)
            toast("회원 탈퇴가 완료 되었습니다.")
            finish()
        }

        val img = findViewById<ImageView>(R.id.imageView)

        val id = when ((1 until  4).random()) {
            1 -> R.drawable.cat
            2 -> R.drawable.dog
            3 -> R.drawable.dog2
            else -> {R.drawable.pat}
        }

        img.setImageDrawable(ResourcesCompat.getDrawable(resources, id, null))
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}