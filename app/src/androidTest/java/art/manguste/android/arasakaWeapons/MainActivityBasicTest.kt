package art.manguste.android.arasakaWeapons

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityBasicTest {
    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun clickCartButton_GoToOrderActivity() {
        Intents.init()
        // click on cart button
        Espresso.onView(ViewMatchers.withId(R.id.cartImage)).perform(ViewActions.click())

        // check activity opens
        Intents.intended(IntentMatchers.hasComponent(OrderActivity::class.java.name))
        Intents.release()
    }
}