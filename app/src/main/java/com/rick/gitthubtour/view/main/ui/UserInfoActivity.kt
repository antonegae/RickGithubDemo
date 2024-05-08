package com.rick.githubtour.view.main.ui


import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rick.githubtour.R
import com.rick.githubtour.databinding.ActivityUserInfoBinding
import com.rick.githubtour.view.main.viewModel.UserInfoViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserInfoActivity : AppCompatActivity() {

    private val viewModel: UserInfoViewModel by viewModels()
    private lateinit var binding : ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tvtext: TextView = binding.tvUsername//findViewById(R.id.tv_username)
        val tvbio: TextView = findViewById(R.id.tv_bio)
        val tvnickname: TextView = findViewById(R.id.tv_loginname)
        val tvlocation: TextView = findViewById(R.id.tv_location)
        val tvblog: TextView = findViewById(R.id.tv_blog)
        //tvblog.setTextColor(Color.parseColor("#66B3FF"))

        val txtName = intent.getStringExtra("txtname")
        val img = intent.getStringExtra("picture")

        Picasso.get().load(img)
            .placeholder(R.drawable.github)
            .into(binding.ivUserPic)
        viewModel.getUserInfo(txtName!!)
        viewModel.userInfo.observe(this) { response ->
            tvtext.text = response.name
            tvbio.text = response.bio
            tvnickname.text = response.login
            tvlocation.text =response.location
            tvblog.text = response.blog

        }
    }
}









