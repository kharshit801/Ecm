package com.example.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class SubjectDetailActivity extends AppCompatActivity {

    int selectedSemester;
    int selectedSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subject_detail);

        // Get the selected semester and subject from the intent
        selectedSemester = getIntent().getIntExtra("SEMESTER", 1);
        selectedSubject = getIntent().getIntExtra("SUBJECT", 1);
        String subjectName = getIntent().getStringExtra("SUBJECT_NAME");

        // Set the subject name in the TextView
        TextView subjectTextView = findViewById(R.id.subject_text_view);
        subjectTextView.setText(subjectName);

        // Set up button click listeners
        Button syllabusButton = findViewById(R.id.btn_syllabus);
        Button notesButton = findViewById(R.id.btn_notes);
        Button referenceBooksButton = findViewById(R.id.btn_reference_books);
        Button previousYearPapersButton = findViewById(R.id.btn_previous_year_papers);

        View.OnClickListener materialButtonClickListener = v -> {
            String materialType = "";
            if (v.getId() == R.id.btn_syllabus) {
                materialType = "syllabus";
            } else if (v.getId() == R.id.btn_notes) {
                materialType = "notes";
            } else if (v.getId() == R.id.btn_reference_books) {
                materialType = "reference_books";
            } else if (v.getId() == R.id.btn_previous_year_papers) {
                materialType = "previous_year_papers";
            }

            Intent intent = new Intent(SubjectDetailActivity.this, MaterialActivity.class);
            intent.putExtra("SEMESTER", selectedSemester);
            intent.putExtra("SUBJECT", selectedSubject);
            intent.putExtra("MATERIAL_TYPE", materialType);
            startActivity(intent);
        };

        syllabusButton.setOnClickListener(materialButtonClickListener);
        notesButton.setOnClickListener(materialButtonClickListener);
        referenceBooksButton.setOnClickListener(materialButtonClickListener);
        previousYearPapersButton.setOnClickListener(materialButtonClickListener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}