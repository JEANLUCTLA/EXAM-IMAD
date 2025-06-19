package com.example.assig_2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//

// Declare parallel arrays for song details
        val songs = mutableListOf("Fela in Versace", "Jerusalema", "Sponono", "Loliwe")
        val artists = mutableListOf("Aka", "Master kg", "Kabza de small", "Zahara")
        val ratings = mutableListOf(4, 5, 3, 2)
        val comments = mutableListOf("Rap", "Best Song", "Dance", "Memories")
// Initialize buttons
        val addButton = findViewById<Button>(R.id.btnAdd)
        val NextButton = findViewById<Button>(R.id.btnNavigate)
        val exitbtn = findViewById<Button>(R.id.btnExit)
// Initialize plaintexts
        val song_title = findViewById<EditText>(R.id.txtTitle)
        val artist_name = findViewById<EditText>(R.id.txtArtist)
        val rating_field = findViewById<EditText>(R.id.txtRating)
        val comment_text = findViewById<EditText>(R.id.txtComment)
        val error_txt = findViewById<TextView>(R.id.txtError)

        addButton.setOnClickListener {
// Get input values
            val song = song_title.text.toString()
            val artist = artist_name.text.toString()
            val rating = rating_field.text.toString().toIntOrNull() ?: 0
            val comment = comment_text.text.toString()

// Validation checks
            if (song.isEmpty() || artist.isEmpty()) {
                error_txt.text = "field empty"
                return@setOnClickListener
            }

            if (rating !in 1..5) {
                error_txt.text = "error ! in your rating field"
                return@setOnClickListener
            }

// Check if song already exists
            if (songs.contains(song)) {
                error_txt.text = "this song exist already"
            } else {
// Add to arrays if not exists
                songs.add(song)
                artists.add(artist)
                ratings.add(rating)
                comments.add(comment)

// Clear inputs
                song_title.text.clear()
                artist_name.text.clear()
                rating_field.text.clear()
                comment_text.text.clear()

                error_txt.text = "SUCCESS YOU CAN REVIEW "
            }


        }

        NextButton.setOnClickListener {
            var also = (Intent(this , MainActivity::class.java).apply {
                putExtra("SONGS" , songs.toTypedArray())
                putExtra("ARTISTS" , artists.toTypedArray())
                putExtra("RATINGS" , ratings.toIntArray())
                putExtra("COMMENTS" , comments.toTypedArray())
            }).also {
                startActivity(it)
            }
        }

        exitbtn.setOnClickListener {
            finishAffinity() // Closes all activities and exits app
        }

    }
}