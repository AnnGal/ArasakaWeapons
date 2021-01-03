package art.manguste.android.arasakaWeapons

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test

class MainActivityRecyclerViewTest {
    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun clickRecyclerViewItem_OpensDetailActivity() {
        val weaponName = mActivityTestRule.activity.getString(R.string.weapon_gorilla_title)

        // click on displayed recycler view, on a first item
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.productName)).check(ViewAssertions.matches(ViewMatchers.withText(weaponName)))
    }
}