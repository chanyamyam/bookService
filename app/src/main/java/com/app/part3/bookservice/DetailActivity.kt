package com.app.part3.bookservice

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.app.part3.bookservice.databinding.ActivityDetailBinding
import com.app.part3.bookservice.model.Book
import com.app.part3.bookservice.model.Review
import com.bumptech.glide.Glide

class DetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "historyDB"
        ).build()

        val bookModel = intent.getParcelableExtra<Book>("bookModel")

        binding.titleTextView.text = bookModel?.title.orEmpty()

        Glide
            .with(binding.coverImageView.context)
            .load(bookModel?.coverLargeUrl.orEmpty())
            .into(binding.coverImageView)

        binding.descriptionTextView.text = bookModel?.description.orEmpty()

        Thread {
            val review = db.reviewDao().getOneReview(bookModel?.id?.toInt() ?: 0)
            runOnUiThread {
                binding.reviewEditText.setText(review?.review.orEmpty())
            }
        }.start()

        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(bookModel?.id?.toInt() ?:0,
                        binding.reviewEditText.text.toString())
                )
            }.start()
        }

    }
}