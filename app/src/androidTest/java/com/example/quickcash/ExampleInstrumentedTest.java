package com.example.quickcash;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.View.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quickcash", appContext.getPackageName());
    }

    //US-5: AT 1 - Tests if username is Invalid
    @Test
    public void checkIfSignUpUserNameIsInvalid() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("hello@dal.ca"));
        onView(withId(R.id.usernameText)).perform(typeText(""));
        onView(withId(R.id.passwordText)).perform(typeText("hello123"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
        assertNotNull(ErrorTypes.valueOf("invalidUserName"));
    }

    //US-5: AT 1 - Tests if email is inValid
    @Test
    public void checkIfSignUpEmailIsInvalid() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("Hello.Live@live.com.ca"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloMan"));
        onView(withId(R.id.passwordText)).perform(typeText("hello123"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
        assertNotNull(ErrorTypes.valueOf("invalidEmail"));
    }

    //US-5: AT 1 - Tests if password is less than six chars/invalid
    @Test
    public void checkIfPassIsLessSixChar() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("hello@live.com"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloMan"));
        onView(withId(R.id.passwordText)).perform(typeText("sdf"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
        assertNotNull(ErrorTypes.valueOf("invalidPassword"));
    }

    //US-5: AT 1 - Tests Creating a new client user
    //User must not exist in FB authentication & Realtime DB for test to pass
    @Test
    public void creatingNewClientUser() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("helloman@live.com"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloMan"));
        onView(withId(R.id.passwordText)).perform(typeText("sdf234"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
    }

    //US-5: AT 1 - Tests Creating a new helper user
    //User must not exist in FB authentication & Realtime DB for test to pass
    @Test
    public void creatingNewHelperUser() { //TODO: insert assert test
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("hellowoman@live.com"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloWoman"));
        onView(withId(R.id.passwordText)).perform(typeText("qwe567"), closeSoftKeyboard());
        onView(withId(R.id.roleSelector)).perform(click());
        onView(withId(R.id.signupButton)).perform(click());
    }

    //US-5: AT 1 - Tests Login with no correctly formatted email credential
    @Test
    public void badCredEmailUser() { //TODO: insert assert test
        onView(withId(R.id.loginEmailText)).perform(typeText("helloman@@@live.com"));
        onView(withId(R.id.loginPasswordText)).perform(typeText("sdf234"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
    }

    //US-5: AT 1 - Tests Login with not correctly formatted pass credential
    @Test
    public void badCredPassUser() { //TODO: insert assert test
        onView(withId(R.id.loginEmailText)).perform(typeText("helloman@live.com"));
        onView(withId(R.id.loginPasswordText)).perform(typeText("sd34"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
    }

    //US-5: AT 1 - Tests Login with invalid email credential
    @Test
    public void invalidEmailUser() { //TODO: insert assert test
        onView(withId(R.id.loginEmailText)).perform(typeText("helan@live.com"));
        onView(withId(R.id.loginPasswordText)).perform(typeText("sdf234"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
    }

    //US-5: AT 1 - Tests Login with invalid password credential
    @Test
    public void invalidPassUser() { //TODO: insert assert test
        onView(withId(R.id.loginEmailText)).perform(typeText("helloman@live.com"));
        onView(withId(R.id.loginPasswordText)).perform(typeText("sdf2dssd34"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
    }

    //US-5: AT 1 - Tests Login with valid email and password credentials
    //User must exist in FB authentication & Realtime DB for test to pass
    @Test
    public void validLoginUser() { //TODO: insert assert test
        onView(withId(R.id.loginEmailText)).perform(typeText("helloman@live.com"));
        onView(withId(R.id.loginPasswordText)).perform(typeText("sdf234"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
    }
}