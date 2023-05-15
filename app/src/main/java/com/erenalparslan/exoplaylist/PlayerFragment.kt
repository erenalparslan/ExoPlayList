package com.erenalparslan.exoplaylist

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.*
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerFragment : Fragment(), Player.Listener {


    lateinit var player: ExoPlayer
    lateinit var playerNext: ExoPlayer

    //lateinit var mediaItem :MediaItem
    public lateinit var mediaItems: List<MediaItem>
    private var nextIndex = 0
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0


    inner class PlayerEventListener : Player.Listener {

        override fun onPlaybackStateChanged(state: Int) {
            super.onPlaybackStateChanged(state)

     /*       // Playback state değiştiğinde burada işlemler yapabilirsiniz
            when (state) {
                Player.STATE_READY -> {
                    // Medya hazır olduğunda, bir sonraki medyanın var olup olmadığını kontrol et
                    val nextMediaItem = mediaItems.getOrNull(player.currentMediaItemIndex + 1)
                    if (nextMediaItem != null) {
                        // Bir sonraki medya varsa, ön yükleme yap
                        player.setMediaItem(nextMediaItem)
                    }
                }
                // Diğer durumlar...
            }*/


        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            println("READY--->" + error)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            println("isPlaying--->" + isPlaying)
        }

        override fun onSeekForwardIncrementChanged(seekForwardIncrementMs: Long) {
            super.onSeekForwardIncrementChanged(seekForwardIncrementMs)
            println("SEEK--->" + seekForwardIncrementMs.toInt())
        }

        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
            println("PLAYER--->" + player)
            println("EVENTS--->" + events)
        }

        override fun onTracksChanged(tracks: Tracks) {
            super.onTracksChanged(tracks)
            println("TRACKS--->" + tracks.toString())
            // TODO: this nextTrack increment
            if (player.currentMediaItemIndex + 1 < mediaItems.size) {
                playerSetterNext(playerNext, mediaItems.get(player.currentMediaItemIndex + 1))
            }else if(player.currentMediaItemIndex + 1 == mediaItems.size){
                playerSetterNext(playerNext, mediaItems.get(0))
            }

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mediaItem = MediaItem.fromUri(Uri.parse(URL))
        player = ExoPlayer.Builder(requireContext()).build()
        playerNext = ExoPlayer.Builder(requireContext()).build()
        val playerEventListener = PlayerEventListener()
        player.addListener(playerEventListener)

        println("create Fragment")

        mediaItems = listOf(
            MediaItem.fromUri(Uri.parse("file:///storage/emulated/0/Download/car.mp4")),
            MediaItem.fromUri(Uri.parse("file:///storage/emulated/0/Download/cartoon.mp4")),
            MediaItem.fromUri(Uri.parse("file:///storage/emulated/0/Download/vlog.mp4"))
        )

        // Player state'i kaydet
        if (savedInstanceState != null) {
            currentWindow = savedInstanceState.getInt("current_window")
            playbackPosition = savedInstanceState.getLong("playback_position")
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        println("create View Fragment")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("View create  Fragment")

        exoPlay.player = player
        exoPlayNext?.player = playerNext
        playerNext.volume=0f
        nextIndex = player.currentMediaItemIndex + 1
        playerSetter(player, mediaItems)
        playerSetterNext(playerNext, mediaItems.get(nextIndex))


    }

    override fun onPause() {
        super.onPause()
        println(" Fragment pause")
    }

    override fun onResume() {
        super.onResume()
        println("Fragment resume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Fragment destroy")
        player.release()
    }

    override fun onStart() {
        super.onStart()
        println("Fragment start")
    }

    override fun onStop() {
        super.onStop()
        println("Fragment stop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("Fragment detach view")
    }

    override fun onDetach() {
        super.onDetach()
        println("Fragment detach")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("Fragment attach")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        /*    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                exoPlay.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            } else {
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                exoPlay.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            }*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Player state'ini kaydet
        outState.putInt("current_window", player.currentWindowIndex)
        outState.putLong("playback_position", player.currentPosition)
    }




    fun playerSetter(player: ExoPlayer, mediaItem: List<MediaItem>) {
        player.setMediaItems(mediaItem, currentWindow, playbackPosition)
        player.prepare()
        player.play()
    }

    fun playerSetterNext(player: ExoPlayer, mediaItem: MediaItem) {
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }


}

