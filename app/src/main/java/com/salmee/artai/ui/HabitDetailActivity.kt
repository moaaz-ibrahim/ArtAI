package com.salmee.artai.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.salmee.artai.ModelActivity
import com.salmee.artai.R
import com.salmee.artai.databinding.ActivityHabitDetailBinding

class HabitDetailActivity : AppCompatActivity() {
    private var isFavorite = false
    private lateinit var binding: ActivityHabitDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding= ActivityHabitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val habitImageView: ImageView = findViewById(R.id.habitImageView)
        val habitTitle: TextView = findViewById(R.id.habitTitle)
        val habitDescription: TextView = findViewById(R.id.habitDescription)
        val saveButton: Button = findViewById(R.id.save_btn)
        val shareButton: Button = findViewById(R.id.share_btn)
        val fav : ImageView = findViewById(R.id.favorite_btn)

        // Get the data from intent
        val name = intent.getStringExtra("habit_name") ?: "Unknown"
        val description = intent.getStringExtra("habit_description") ?: "No description"
        val imageResId = intent.getIntExtra("habit_image", 0)

        // Set the data in UI
        habitTitle.text = name
        habitDescription.text = description
        habitImageView.setImageResource(imageResId)

        fav.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_elevation))
            isFavorite = !isFavorite  // Toggle the state

            if (isFavorite) {
                fav.setImageResource(R.drawable.favourite_fill) // Change to full red heart
            } else {
                fav.setImageResource(R.drawable.favourite) // Change back to outlined heart
            }
        }

        saveButton.setOnClickListener {
            Toast.makeText(this, "Saved $name", Toast.LENGTH_SHORT).show()
        }

        shareButton.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this habit: $name\n$description")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        binding.navigationBar.profileButton.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_elevation))
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
        binding.navigationBar.homeButton.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_elevation))
            val i = Intent(this, CategoryActivity::class.java)
            startActivity(i)
        }
        binding.navigationBar.centerIcon.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_elevation))
            val i = Intent(this, ModelActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
