package com.example.nirav.demoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    public static final String DATABASE_NAME = "mydatabase";

    TextView textViewViewEmployees;
    EditText editTextName, editTextSalary;
    Spinner spinnerDepartment;
    Button btn;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewViewEmployees = (TextView) findViewById(R.id.textViewViewEmployees);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);
        btn = (Button) findViewById(R.id.buttonAddEmployee);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee();
            }
        });

        findViewById(R.id.buttonAddEmployee).setOnClickListener(this);
        findViewById(R.id.textViewViewEmployees).setOnClickListener(this);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();

    }

    private void createEmployeeTable () {
        String sql =
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                        "    id int NOT NULL CONSTRAINT employees_pk PRIMARY KEY,\n" +
                        "    name varchar(200) NOT NULL,\n" +
                        "    department varchar(200) NOT NULL,\n" +

                        "    salary double NOT NULL\n" +
                        ");";

        mDatabase.execSQL(sql);
    }

    private boolean inputsAreCorrect (String name, String salary){
        if (name.isEmpty()) {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return false;
        }

        if (salary.isEmpty() || Integer.parseInt(salary) <= 0) {
            editTextSalary.setError("Please enter salary");
            editTextSalary.requestFocus();
            return false;
        }
        return true;
    }


    private void addEmployee () {

        String name = editTextName.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();
        String dept = spinnerDepartment.getSelectedItem().toString();



        if (inputsAreCorrect(name, salary)) {

            String insertSQL = "INSERT INTO employees \n" +
                    "(name, department, salary)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";
            mDatabase.execSQL(insertSQL, new String[]{name, dept,  salary});

            Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
        }


        }

    @Override
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.buttonAddEmployee:
                addEmployee();
                break;
            case R.id.textViewViewEmployees:
                startActivity(new Intent(this, EmployeeActivity.class));
                break;
        }
    }







}


