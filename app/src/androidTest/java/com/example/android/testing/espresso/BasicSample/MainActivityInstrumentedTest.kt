package com.example.android.testing.espresso.BasicSample

import android.content.Intent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testChangeText_sameActivity() {
        onView(withId(R.id.editTextUserInput))
            .perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.textToBeChanged)).check(matches(withText("123")))
    }

    @Test
    fun testChangeText_newActivity() {
        onView(withId(R.id.editTextUserInput))
            .perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
       Thread.sleep(2000)
       onView(withId(R.id.show_text_view)).check(matches(withText("123")))
    }

    @Test
    fun testEmptyText_sameActivity() {
       
        onView(withId(R.id.changeTextBt)).perform(click())
        Thread.sleep(2000) 
        onView(withId(R.id.textToBeChanged)).check(matches(withText("")))
    }

    @Test
    fun testEmptyText_newActivity() {
      onView(withId(R.id.activityChangeTextBtn)).perform(click())
       Thread.sleep(2000) 
       onView(withId(R.id.show_text_view)).check(matches(withText("")))
    }
    @Test
    fun testChangeTextToAbcdef_sameActivity() {
    
        onView(withId(R.id.editTextUserInput))
            .perform(typeText("abcdef"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        Thread.sleep(2000) 
        onView(withId(R.id.textToBeChanged)).check(matches(withText("abcdef")))
    }

    @Test
    fun testChangeTextToAbcdef_newActivity() {
     
        onView(withId(R.id.editTextUserInput))
            .perform(typeText("abcdef"), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())

     
        Thread.sleep(2000) 

        onView(withId(R.id.show_text_view)).check(matches(withText("abcdef")))
    }
    @Test
    fun testValidateTextViewMainActivity() {
     
        onView(withId(R.id.textToBeChanged)).check(matches(withText("Hello Espresso!")))
    }

    @Test
    fun testValidateTextViewShowTextActivity() {
    
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val intent = ShowTextActivity.newStartIntent(context, "Test Text")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().startActivitySync(intent)

  
        Thread.sleep(2000) 

  
        onView(withId(R.id.show_text_view)).check(matches(withText("Test Text")))
    }


}