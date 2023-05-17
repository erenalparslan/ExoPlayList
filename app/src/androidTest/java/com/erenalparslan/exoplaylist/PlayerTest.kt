package com.erenalparslan.exoplaylist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerTest {

    private lateinit var playerFragment : PlayerFragment


    @get:Rule
    var instantTaskExecute = InstantTaskExecutorRule()


    @Before
    fun setup() {


        playerFragment= PlayerFragment()
        ActivityScenario.launch(MainActivity::class.java)
        val player = ExoPlayer.Builder( ApplicationProvider.getApplicationContext()).build()
        playerFragment.player = player
        //player = playerFragment.player
    }

    @Test
    fun notNullPlayer() {


        Truth.assertThat(playerFragment.player).isNotNull()

    }

    @Test
    fun notNullPlayList() {
        Truth.assertThat(playerFragment.mediaItems).isNotNull()
    }

    @Test
    fun sizePlayList() {
        Truth.assertThat(playerFragment.mediaItems?.size).isEqualTo(3)
    }

    @Test
    fun IndexnotNullPlayList() {
        Truth.assertThat(playerFragment.mediaItems?.get(0)).isNotNull()
    }


}