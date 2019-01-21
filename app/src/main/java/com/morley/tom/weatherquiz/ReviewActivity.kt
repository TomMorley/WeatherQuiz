package com.morley.tom.weatherquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.morley.tom.weatherquiz.base.BaseActivity
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : BaseActivity() {

    private var score : Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_review
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        score = intent.getIntExtra("score", 0)
        scoreTv.text = "You scored $score!"
    }

    override fun initViews() {
        super.initViews()

        homeBtn.setOnClickListener {
            startActivity(Intent(this, HomepageActivity::class.java))
        }
    }
}
