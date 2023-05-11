package com.erenalparslan.exoplaylist

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerFragment : Fragment() {

    lateinit var player: ExoPlayer
    //lateinit var mediaItem :MediaItem
    lateinit var mediaItems: List<MediaItem>

    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mediaItem = MediaItem.fromUri(Uri.parse(URL))
        player = ExoPlayer.Builder(requireContext()).build()
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

        println(mediaItems[0].mediaMetadata)
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

        playerSetter(player, mediaItems)


    }

    override fun onPause() {
        super.onPause()
        println("pause")
    }

    override fun onResume() {
        super.onResume()
        println("resume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("destroy")
        player.release()
    }

    override fun onStart() {
        super.onStart()
        println("start")
    }

    override fun onStop() {
        super.onStop()
        println("stop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("detach view")
    }

    override fun onDetach() {
        super.onDetach()
        println("detach")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("attach")
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

    companion object {
        const val URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    }


}