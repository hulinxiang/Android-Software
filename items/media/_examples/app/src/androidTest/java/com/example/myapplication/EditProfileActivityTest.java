package com.example.myapplication;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.myapplication.R;
import com.example.myapplication.activity.EditProfileActivity;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditProfileActivityTest {

    @Before
    public void setUp() {
        // Initialize a dummy user session before launching the activity
        User dummyUser = new User("dummy@test.com", "Dummy User", "1234", "123 Street", "1234567890");
        SessionManager.getInstance().setUser(dummyUser);
    }

    @Test
    public void testEditProfile_SuccessfulUpdate() {
        // Launch the EditProfileActivity
        ActivityScenario<EditProfileActivity> scenario = ActivityScenario.launch(EditProfileActivity.class);

        // Update the fields
        onView(withId(R.id.edit_name)).perform(replaceText("New Name"));
        onView(withId(R.id.edit_password)).perform(replaceText("new_password"));
        onView(withId(R.id.edit_address)).perform(replaceText("New Address"));
        onView(withId(R.id.edit_phone)).perform(replaceText("0987654321"));

        // Click the Save Changes button
        onView(withId(R.id.btn_save_changes)).perform(click());


        // Verify that the profile is updated successfully (Toast message)
        onView(withText("Profile updated successfully!")).inRoot(new ToastMatcher()).check(matches(androidx.test.espresso.matcher.ViewMatchers.isDisplayed()));
    }

    @Test
    public void testEditProfile_InvalidInput() {
        // Launch the EditProfileActivity
        ActivityScenario<EditProfileActivity> scenario = ActivityScenario.launch(EditProfileActivity.class);

        // Leave the name field empty
        onView(withId(R.id.edit_name)).perform(replaceText(""));

        // Click the Save Changes button
        onView(withId(R.id.btn_save_changes)).perform(click());

        // Verify that a Toast message "Name cannot be empty!" is shown
        onView(withText("Name cannot be empty!")).inRoot(new ToastMatcher()).check(matches(androidx.test.espresso.matcher.ViewMatchers.isDisplayed()));

        // Try to update with an empty address
        onView(withId(R.id.edit_name)).perform(replaceText("New Name"));
        onView(withId(R.id.edit_address)).perform(replaceText(""));

        onView(withId(R.id.btn_save_changes)).perform(click());

        // Verify that a Toast message "Address cannot be empty!" is shown
        onView(withText("Address cannot be empty!")).inRoot(new ToastMatcher()).check(matches(androidx.test.espresso.matcher.ViewMatchers.isDisplayed()));
    }
}