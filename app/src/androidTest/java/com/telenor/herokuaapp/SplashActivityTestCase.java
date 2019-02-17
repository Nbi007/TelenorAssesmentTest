package com.telenor.herokuaapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import com.telenor.herokuaapp.view.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class SplashActivityTestCase {


    @Rule
    public ActivityTestRule<SplashActivity> splashActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private SplashActivity splashActivity = null;

    Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        splashActivity = splashActivityTestRule.getActivity();

    }


    @Test
    public void checkCallFromSplash() {
       Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(mainActivity);
    }


    @After
    public void tearDown() throws Exception {
        splashActivity = null;
    }

}