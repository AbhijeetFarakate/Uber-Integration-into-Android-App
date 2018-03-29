package com.example.smita.uberintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.RideRequestButtonCallback;
import com.uber.sdk.android.rides.internal.RideRequestButtonView;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.error.ApiError;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


		
//        RideRequestButton rideRequestButton = new RideRequestButton(MainActivity.this);
//        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);
//        relativeLayout.addView(rideRequestButton);

        RideRequestButton rideRequestButton = findViewById(R.id.rideButton);


        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("--Your-Own-ClientId-from-Uber-Developer--")
                .setServerToken("--Your-Own-ServerToken-from-Uber-Developer--")
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.PRODUCTION)
                .build();


        UberSdk.initialize(config);


        RideParameters rideParams = new RideParameters.Builder()
                // Optional product_id from /v1/products endpoint (e.g. UberX). If not provided, most cost-efficient product will be used
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                // Required for price estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of dropoff location
                .setDropoffLocation(
                        18.4920657, 73.8337911, "Janseva Sahakari Bank Ltd", "Gulawani Maharaj Road, Pandurang Colony, Erandwane, Pune")
                // Required for pickup estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of pickup location
                .setPickupLocation(18.497820, 73.833193, "Your Location", "")
                .build();

        // set parameters for the RideRequestButton instance
        rideRequestButton.setRideParameters(rideParams);



        ServerTokenSession session = new ServerTokenSession(config);
        rideRequestButton.setSession(session);


        rideRequestButton.loadRideInformation();



    }
}
