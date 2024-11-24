package com.example.httpoperations;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Setup
        EditText urlInput = findViewById(R.id.urlInput);
        TextView outputView = findViewById(R.id.outputView);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        // GET Request
        findViewById(R.id.getButton).setOnClickListener(v -> 
            outputView.setText(httpRequest(urlInput.getText().toString(), "GET", null))
        );

        // POST Request
        findViewById(R.id.postButton).setOnClickListener(v -> 
            outputView.setText(httpRequest(urlInput.getText().toString(), "POST", "name=John&age=30"))
        );
    }

    private String httpRequest(String urlString, String method, String postData) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod(method);
            if ("POST".equals(method)) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(postData.getBytes());
                }
            }
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return new BufferedReader(new InputStreamReader(connection.getInputStream()))
                        .lines().reduce("", (acc, line) -> acc + line);
            }
            return method + " failed: " + connection.getResponseCode();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
