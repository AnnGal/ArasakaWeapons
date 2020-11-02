package art.manguste.android.ArasakaWeapons;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CardDetailActivityBasicTest {

    /*!!
    for error - "Error performing 'single click <..>' on view 'Animations or transitions are enabled on the target device"
    use:
    adb shell settings put global window_animation_scale 0cd
    adb shell settings put global transition_animation_scale 0
    adb shell settings put global animator_duration_scale 0
    */

    @Rule
    public ActivityTestRule<CardDetailActivity> mActivityTestRule =
            new ActivityTestRule<>(CardDetailActivity.class);

    @Test
    public void clickDecrementButton_NothingChanges() {
        // check init number
        onView((withId(R.id.count))).check(matches(withText("1")));
        // click decrementing
        onView((withId(R.id.actionDecreaseCount))).perform(click());
        // verify value not change
        onView(withId(R.id.count)).check(matches(withText("1")));
    }

    @Test
    public void clickIncrementButton_ChangesQuantity() {
        // check init number
        onView((withId(R.id.count))).check(matches(withText("1")));
        // click incrementing
        onView((withId(R.id.actionIncreaseCount))).perform(click());
        // verify value changes
        onView(withId(R.id.count)).check(matches(withText("2")));
        onView(withId(R.id.price)).check(matches(withText("200")));
    }
}
