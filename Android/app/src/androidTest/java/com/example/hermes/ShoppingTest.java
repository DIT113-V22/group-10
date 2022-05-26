package com.example.hermes;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ShoppingTest {

    @Rule
    public ActivityTestRule<ShoppingScreen> mActivityTestRule = new ActivityTestRule<>(ShoppingScreen.class);

    @Test
    public void shoppingTest() {


        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.rapidTest), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                10)),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.mask), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                9)),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.painKiller), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                12)),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.painKiller), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout4),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                12)),
                                0),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.multivitamin), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        13),
                                0),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextTime),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextTime), withText("123456"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editTextTextPassword4),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("23/05/2022"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextTextPassword4), withText("23/05/2022"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.editTextDate2),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("1200"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.editTextDate2), withText("1200"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText8.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.submitOrder), withText("Submit Delivery"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton10.check(matches(isClickable()));

        ViewInteraction frameLayout = onView(
                allOf(withId(android.R.id.content),
                        withParent(allOf(withId(androidx.appcompat.R.id.action_bar_root),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
