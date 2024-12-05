package com.example.urlproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText enterUrl;
    private Button okButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        enterUrl = findViewById(R.id.entry);
        okButton = findViewById(R.id.ok);
        cancelButton = findViewById(R.id.cancel);

        okButton.setOnClickListener( view -> {
            String urlDirection = enterUrl.getText().toString().trim();

            if (!urlDirection.isEmpty()) {
                if (!urlDirection.startsWith("http://") && !urlDirection.startsWith("https://")) {
                    urlDirection = "http://" + urlDirection;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlDirection));

                if (isValidUrl(urlDirection)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Navigator isn't disponible", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please, enter a URL!", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(view -> {
            enterUrl.setText("");
            Toast.makeText(this, "URL delete", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para validar la URL
    private boolean isValidUrl(String url) {
        try {
            Uri.parse(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

