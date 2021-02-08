package com.example.quickcash;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.quickcash.View.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
        assertNotNull(RegistrationViewModel.errorType.valueOf("invalidUserName"));
    }

    //US-5: AT 1 - Tests if email is inValid
    @Test
    public void checkIfSignUpEmailIsInvalid() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("Hello.Live@live.com.ca"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloMan"));
        onView(withId(R.id.passwordText)).perform(typeText("hello123"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
        assertNotNull(RegistrationViewModel.errorType.valueOf("invalidEmail"));
    }

    //US-5: AT 1 - Tests if password is less than six chars/invalid
    @Test
    public void checkIfPassIsLessSixChar() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("hello@live.com"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloMan"));
        onView(withId(R.id.passwordText)).perform(typeText("sdf"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
        assertNotNull(RegistrationViewModel.errorType.valueOf("invalidPassword"));
    }

    //US-5: AT 1 - Tests Creating a new client user
    @Test
    public void creatingNewClientUser() {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailText)).perform(typeText("hello@live.com"));
        onView(withId(R.id.usernameText)).perform(typeText("HelloMan"));
        onView(withId(R.id.passwordText)).perform(typeText("sdf234"), closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());
        DatabaseReference DBCred = FirebaseDatabase.getInstance().getReference().child("HelloMan");
        DBCred.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                assertEquals("CLIENT", value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
    }
}