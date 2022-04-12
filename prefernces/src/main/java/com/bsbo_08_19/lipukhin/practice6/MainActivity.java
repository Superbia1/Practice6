package com.bsbo_08_19.lipukhin.practice6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextStudyPlace;
    private TextView textViewResult;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextStudyPlace = findViewById(R.id.editTextTextStudyPlace);
        textViewResult = findViewById(R.id.textViewResult);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonLoad = findViewById(R.id.buttonLoad);

        buttonSave.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Name", editTextName.getText().toString());
            editor.putString("StudyPlace", editTextStudyPlace.getText().toString());
            editor.apply();
        });

        buttonLoad.setOnClickListener(view -> {
            String name = sharedPreferences.getString("Name", null);
            String placeOfStudy = sharedPreferences.getString("StudyPlace", "нигде");
            textViewResult.setText("Имя: " + name + ". Место учебы: " + placeOfStudy);
        });
    }
}