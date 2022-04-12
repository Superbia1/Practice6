package com.bsbo_08_19.lipukhin.notebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "text";
    private EditText editTextText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextText = findViewById(R.id.editTextText);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editTextText.setText(sharedPreferences.getString(TAG, null));

        Button saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TAG, editTextText.getText().toString());
            editor.apply();
        });

    }
}