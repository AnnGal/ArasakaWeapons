package art.manguste.android.arasakaWeapons

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import art.manguste.android.arasakaWeapons.CardDetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardDetailActivityBasicTest {
    /*!!
    for error - "Error performing 'single click <..>' on view 'Animations or transitions are enabled on the target device"
    use:
    adb shell settings put global window_animation_scale 0cd
    adb shell settings put global transition_animation_scale 0
    adb shell settings put global animator_duration_scale 0
    */
    @Rule
    var mActivityTestRule = ActivityTestRule(CardDetailActivity::class.java)
    @Test
    fun clickDecrementButton_NothingChanges() {
        // check init number
        Espresso.onView(ViewMatchers.withId(R.id.count)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
        // click decrementing
        Espresso.onView(ViewMatchers.withId(R.id.actionDecreaseCount)).perform(ViewActions.click())
        // verify value not change
        Espresso.onView(ViewMatchers.withId(R.id.count)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    fun clickIncrementButton_ChangesQuantity() {
        // check init number
        Espresso.onView(ViewMatchers.withId(R.id.count)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
        // click incrementing
        Espresso.onView(ViewMatchers.withId(R.id.actionIncreaseCount)).perform(ViewActions.click())
        // verify value changes
        Espresso.onView(ViewMatchers.withId(R.id.count)).check(ViewAssertions.matches(ViewMatchers.withText("2")))
        Espresso.onView(ViewMatchers.withId(R.id.price)).check(ViewAssertions.matches(ViewMatchers.withText("200")))
    }
}