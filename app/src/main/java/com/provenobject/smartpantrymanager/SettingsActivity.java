package com.provenobject.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText etProfileName = findViewById(R.id.etProfileName);
        Button btnSaveProfile = findViewById(R.id.btnSaveProfile);

        SharedPreferences preferences =
                getSharedPreferences("SmartPantryPreferences", MODE_PRIVATE);

        String savedName =
                preferences.getString("profile_name", "");

        etProfileName.setText(savedName);

        btnSaveProfile.setOnClickListener(v -> {

            String profileName =
                    etProfileName.getText().toString().trim();

            if (profileName.isEmpty()) {
                etProfileName.setError("Please enter your name");
                return;
            }

            preferences.edit()
                    .putString("profile_name", profileName)
                    .apply();

            Toast.makeText(
                    SettingsActivity.this,
                    "Profile saved successfully",
                    Toast.LENGTH_SHORT
            ).show();

            etProfileName.clearFocus();
        });
    }
}