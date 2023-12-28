package androidsamples.java.dicegames;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.accessibility.AccessibilityChecks;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
  @Rule
  public ActivityScenarioRule<TwoOrMoreActivity> TwoOrMoreActivityTestRule = new ActivityScenarioRule<>(TwoOrMoreActivity.class);

  @BeforeClass
  public static void enableAccessibilityChecks() {
    AccessibilityChecks.enable();
  }

  @Test
  public void wagerCheck(){
    onView(withId(R.id.edit_wager)).check(matches(withHint("Wager")));
  }

  @Test
  public void infoButtonCheck(){
    onView(withId(R.id.btn_info)).perform(click());
  }

  @Test
  public void backButtonCheck(){
    onView(withId(R.id.btn_back)).perform(click());
  }

//  @Test
//  public void walletalike2(){
//    onView(withId(R.id.btn_2alike)).perform(click());
//  }
//
//  @Test
//  public void walletalike3(){
//    onView(withId(R.id.btn_3alike)).perform(click());
//  }
//  @Test
//  public void walletalike4(){
//    onView(withId(R.id.btn_4alike)).perform(click());
//  }

  @Test
  public void walletalikeButtonClicks() {
    int[] buttonIds = {R.id.btn_2alike, R.id.btn_3alike, R.id.btn_4alike};
    int numberOfClicks = 20; // Now, each button will be clicked 20 times

    for (int buttonId : buttonIds) {
      for (int i = 0; i < numberOfClicks; i++) {
        onView(withId(buttonId)).perform(click());

      }
    }

    // If you need to perform additional actions after clicking the buttons, you can do so here.
  }
  @Test
  public void goButtonCheck(){
    onView(withId(R.id.btn_go)).perform(click());
  }

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    assertEquals("androidsamples.java.dicegames", appContext.getPackageName());
  }
}