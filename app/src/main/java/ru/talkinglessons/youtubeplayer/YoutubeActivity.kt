package ru.talkinglessons.youtubeplayer

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "LpizdiL5kvk"
const val YOUTUBE_PLAYLIST = "PlXtTjtWmQhg1SsviTmKkWO5n0a_0T0bnD"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 180)
//        button1.text = getString(R.string.buttonAdded)
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            youtubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youtubeInitilizstionResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (youtubeInitilizstionResult?.isUserRecoverableError == true) {
            youtubeInitilizstionResult.getErrorDialog(this, REQUEST_CODE)?.show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer ($youtubeInitilizstionResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}