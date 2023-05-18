package com.erenalparslan.exoplaylist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.pm.ActivityInfo
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement

import com.google.android.exoplayer2.ExoPlayer
import com.google.common.truth.ExpectFailure.assertThat
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerTest {

    private lateinit var playerFragment: PlayerFragment
    var scenario: ActivityScenario<MainActivity>? = null
    var playerFragmentScenario: FragmentScenario<PlayerFragment>? = null
    var player: ExoPlayer? = null

    @get:Rule
    var instantTaskExecute = InstantTaskExecutorRule()


    @Before
    fun setup() {

        /*scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario?.onActivity { }
        playerFragment = PlayerFragment()
        player = ExoPlayer.Builder(ApplicationProvider.getApplicationContext()).build()
        playerFragment.player = player!!*/
        playerFragmentScenario =
            launchFragmentInContainer<PlayerFragment>(themeResId = R.style.Theme_ExoPlayList)
        playerFragmentScenario?.moveToState(Lifecycle.State.STARTED)
        playerFragment = PlayerFragment()
        playerFragmentScenario?.onFragment() {
            playerFragment = it
        }

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

    @Test
    fun IndexOnenotNullPlayList() {
        Truth.assertThat(playerFragment.mediaItems?.get(1)).isNotNull()
    }

    @Test
    fun IndexTwonotNullPlayList() {
        Truth.assertThat(playerFragment.mediaItems?.get(2)).isNotNull()
    }

    @Test
    fun displayExoPlayer() {
        Espresso.onView(withId(R.id.exoPlay))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun displayNextExoPlayer() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario?.onActivity() { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Espresso.onView(withId(R.id.exoPlayNext))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun displaySubTitle() {
        Espresso.onView(withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun playerNext() {
        playerFragmentScenario?.onFragment { fragment ->

            println(fragment.player.mediaItemCount)
            fragment.player.seekToNextMediaItem()

            //Truth.assertThat(fragment.player.currentPosition).isEqualTo(1)
        }

    }


}