package com.erenalparslan.exoplaylist

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test

class MainTest {

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun permissionTest(){
        Espresso.onView(withText("ALLOW")).check(ViewAssertions.matches(isDisplayed()))
    }

}