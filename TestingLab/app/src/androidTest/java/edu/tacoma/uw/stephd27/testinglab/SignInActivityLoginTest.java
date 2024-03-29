package edu.tacoma.uw.stephd27.testinglab;

import android.app.Fragment;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignInActivityLoginTest {
    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test.
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<SignInActivity> mActivityRule = new ActivityTestRule<>(
            SignInActivity.class);

    @Before
    public void testLaunchLoginFragment() {
        onView(withId(R.id.tv_login))
                .perform(click());
    }

    @Test
    public void testLoginFragmentInvalid() {

        onView(withId(R.id.login_email))
                .perform(typeText("mmuppa@uw.edu"));
        onView(withId(R.id.login_pwd))
                .perform(typeText(""));
        onView(withId(R.id.btn_submit))
                .perform(click());

        onView(withText("Unable to login: Invalid password"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @Test
    public void testLoginFragment() {

        onView(withId(R.id.login_email))
                .perform(typeText("stephd27@uw.edu"));

        onView(withId(R.id.login_pwd))
                .perform(typeText("beep@boop1"));

        onView(withId(R.id.btn_submit))
                .perform(click());

        onView(allOf(withId(R.id.hello)
                , withText("Hello")))
                .check(matches(isDisplayed()));
    }


}
