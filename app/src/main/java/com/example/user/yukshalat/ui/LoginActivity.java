package com.example.user.yukshalat.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.yukshalat.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.example.user.yukshalat.MainActivity;

import com.example.user.yukshalat.helper.MyFunction;

    public class LoginActivity extends MyFunction {

    private TextView tvLoginLocAwal;
    private Button btnSubmit;
    private String name = null;
    Preference preference;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // call preference
        preference = new Preference(LoginActivity.this);

        btnSubmit = findViewById(R.id.btn_submit);
        tvLoginLocAwal = findViewById(R.id.tv_login_loc);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION);

        }

        tvLoginLocAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findLocation();

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLocation();

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == PERMISSION) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();

            } else {
                System.exit(0);

            }
        }
    }


    private void submitLocation() {

        name = tvLoginLocAwal.getText().toString();

        //Todo cek apabila kosong
        if (name.isEmpty()) {
            toast("Input Your Location");

        } else {
            preference.setLocLogin(name);
            intent(MainActivity.class);
            finish();

        }
    }



    private void findLocation() {

        try {
            AutocompleteFilter.Builder filter = new AutocompleteFilter.Builder();
            filter.setCountry("id");

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setFilter(filter.build())
                    .build(this);

            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);

        } catch (GooglePlayServicesRepairableException
                | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("TAG", "Place : " + place.getName());
                tvLoginLocAwal.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i("TAG", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public static class Preference {

        String KEY_NAME = "NAMA";
        String PREF_NAME = "SIMPAN";
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        public Preference(Context c) {
            sharedPreferences = c.getSharedPreferences(PREF_NAME,
                    Context.MODE_PRIVATE);
        }


        public String getLocLogin() {

            return sharedPreferences.getString(KEY_NAME, null);
        }


        public void setLocLogin(String nama) {
            editor = sharedPreferences.edit();
            // metode penyimpanan key dan value
            editor.putString(KEY_NAME, nama).apply();
        }


        public void logout() {
            editor = sharedPreferences.edit();
            editor.clear().apply();
        }
    }
}
