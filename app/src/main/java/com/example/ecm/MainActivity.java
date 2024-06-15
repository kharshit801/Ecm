package com.example.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText regNoEditText;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name_input);
        regNoEditText = findViewById(R.id.reg_no_input);
        startButton = findViewById(R.id.login_button);

        startButton.setOnClickListener(v -> {
            String userName = nameEditText.getText().toString().trim();
            String regNo = regNoEditText.getText().toString().trim();

            if (userName.isEmpty() || regNo.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both name and registration number", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, Homescreen.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);

                String welcomeMessage = "Welcome " + userName + "!";
                Toast.makeText(MainActivity.this, welcomeMessage, Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}