package com.telenor.herokuaapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.bumptech.glide.Priority;
import com.telenor.herokuaapp.controller.RetrofitController;
import com.telenor.herokuaapp.utils.Constants;
import com.telenor.herokuaapp.view.HomeActivity;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;

import okhttp3.mockwebserver.MockWebServer;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class HomeActivityTest {
private HomeActivity homeActivity;

    @Rule
    public final MockWebServer server = new MockWebServer();

    @Before
    public void setUp() throws Exception {
        homeActivity = Mockito.mock(HomeActivity.class);
    }

    @Test
    public void checkIfInternetConnected() throws Exception {
        final Context context = Mockito.mock(Context.class);
        final ConnectivityManager connectivityManager = Mockito.mock(ConnectivityManager.class);
        final NetworkInfo networkInfo = Mockito.mock(NetworkInfo.class);

        Mockito.when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        Mockito.when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        Mockito.when(networkInfo.isConnected()).thenReturn(true);
    }



    @After
    public void tearDown() throws Exception {
    }
}
