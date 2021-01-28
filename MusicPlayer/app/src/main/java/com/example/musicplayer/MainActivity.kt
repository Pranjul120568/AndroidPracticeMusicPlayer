package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)
        var media = MediaPlayer.create(this, R.raw.audio)
        binding!!.playbtn.setOnClickListener {
            media.start()
        }
        binding!!.pausebtn.setOnClickListener {
            media.pause()
        }
        binding!!.stopbtn.setOnClickListener {
            if (media.isPlaying) {
                media.stop()
                media.prepare()
            }
            }

        }
    }
