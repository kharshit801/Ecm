package com.example.ecm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Homescreen extends AppCompatActivity {

    Button nextButton;
    Spinner semesterSpinner;
    Spinner subjectSpinner;
    ArrayAdapter<CharSequence> subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homescreen);

        Button nextButton = findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click

            }
        });

        String userName = getIntent().getStringExtra("USER_NAME");

        TextView nameTextView = findViewById(R.id.tv_name);
        nameTextView.setText(userName);

        semesterSpinner = findViewById(R.id.spinner_semester);
        subjectSpinner = findViewById(R.id.spinner_subject);

        // Populate the semester spinner
        ArrayAdapter<CharSequence> semesterAdapter = ArrayAdapter.createFromResource(this,
                R.array.semesters_array, android.R.layout.simple_spinner_item);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Load subjects based on the selected semester
                int semester = position + 1;
                loadSubjects(semester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                subjectSpinner.setEnabled(false);
            }
        });
    }

    private void loadSubjects(int semester) {
        int arrayId;
        switch (semester) {
            case 2:
                arrayId = R.array.subjects_sem2;
                break;
            case 3:
                arrayId = R.array.subjects_sem3;
                break;
            case 4:
                arrayId = R.array.subjects_sem4;
                break;
            case 5:
                arrayId = R.array.subjects_sem5;
                break;
            case 6:
                arrayId = R.array.subjects_sem6;
                break;
            case 7:
                arrayId = R.array.subjects_sem7;
                break;
            case 8:
                arrayId = R.array.subjects_sem8;
                break;
            case 1:
            default:
                arrayId = R.array.subjects_sem1;
                break;
        }

        subjectAdapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectAdapter);
        subjectSpinner.setEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}