package com.bsbo_08_19.lipukhin.internalfilestorage;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textViewResult;
    private static final String TAG = "someText";
    private final String fileName = "someText.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextText);
        textViewResult = findViewById(R.id.textResult);
        Button sendButton = findViewById(R.id.button);
        Button openButton = findViewById(R.id.button2);

        sendButton.setOnClickListener(view -> {
            FileOutputStream fileOutputStream;
            String fileText = editText.getText().toString();
            try {
                fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                fileOutputStream.write(fileText.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        openButton.setOnClickListener(view -> {
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textViewResult.post(() -> textViewResult.setText(getTextFromFile()));
            }).start();
        });
    }

    public String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(TAG, text);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }
}

