package com.example.dicegames;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class ExampleInstrumentedTestWallet {
    @Rule
    public ActivityScenarioRule<WalletActivity> WalletActivityTestRule = new ActivityScenarioRule<>(WalletActivity.class);

    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable();
    }
    @Test
    public void rolldice(){
        onView(withId(R.id.btn_die)).perform(click());
    }

    @Test
    public void twoOrMore(){
        onView(withId(R.id.btn_launch_twoormore)).perform(click());
    }

    @Test
    public void wagerEdtTxtAcc(){
        onView(withId(R.id.txt_balance)).check(matches(withText("0")));
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.dicegames", appContext.getPackageName());
    }
}
