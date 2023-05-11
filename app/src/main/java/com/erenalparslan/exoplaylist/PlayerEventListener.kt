package com.erenalparslan.exoplaylist

import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player

class PlayerEventListener : Player.Listener {
    override fun onPlaybackStateChanged(state: Int) {
        // Playback state değiştiğinde burada işlemler yapabilirsiniz

        println(state)

    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        println(isPlaying)
    }

}