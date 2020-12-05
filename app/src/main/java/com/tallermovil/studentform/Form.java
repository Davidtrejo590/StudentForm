package com.tallermovil.studentform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.tallermovil.studentform.model.Student;
import java.util.Calendar;

public class Form extends AppCompatActivity {

    /* Variables */
    int yearNow;
    int monthNow;
    int spaces;
    String studentAge;
    Spinner careerSpinner;
    String studentCareer;

    /* Resources */
    DatePickerDialog datePicker;
    EditText etName, etNum, etDate;
    Button btnDate;
    TextView tvDate, tvCareer;
    Student student;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Vibrator */
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        /* Link Resources */
        etName = findViewById(R.id.etName);
        etNum = findViewById(R.id.etNum);
        etDate = findViewById(R.id.etDate);

        /* Spinner Careers */
        careerSpinner = (Spinner) findViewById(R.id.spnCareer);

        careerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentCareer = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /* Input Date on Edit Text*/

        etDate.setInputType(InputType.TYPE_NULL);
        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                /* Start Date */
                int day  = 1;
                int month = 0;
                int year = 2000;

                /* Date Picker */
                datePicker = new DatePickerDialog(Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        final int monthN = month + 1;
                        String dayFormated = (day < 10)? "0" + String.valueOf(day): String.valueOf(day);
                        String monthFormated = (monthN < 10)? "0" + String.valueOf(monthN): String.valueOf(monthN);
                        yearNow = year;
                        monthNow = Integer.parseInt(monthFormated);
                        etDate.setText(dayFormated + "-" + monthFormated + "-" + year);
                        studentAge = calculateAge(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, yearNow, monthNow);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });
    }

    /* Send Data to Result Activity */
    public void clic(View view) {
        if(validate()){
            student = new Student(etName.getText().toString(), etNum.getText().toString(), studentAge, studentCareer);
            Intent intent = new Intent(this, Result.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            intent.putExtras(bundle);
            startActivity(intent);
            Animatoo.animateShrink(this);
        }
        else{
            if(vibrator.hasVibrator()){
                vibrator.vibrate(1000);
            }
        }
    }

    /* Validate Method */
    public boolean validate(){
        if(etName.getText().toString().equals("")){
            etName.setError(getResources().getString(R.string.nameError));
            return false;
        }
        if(!etName.getText().toString().equals("")){
            for(int i = 0; i < etName.getText().toString().length(); i++){
                if(etName.getText().toString().charAt(i) == ' '){
                    spaces += 1;
                }
            }
            if(spaces < 2){
                etName.setError(getResources().getString(R.string.nameFullError));
                return false;
            }
        }
        if(etNum.getText().toString().equals("") || etNum.getText().toString().length() < 9){
            etNum.setError(getResources().getString(R.string.numError));
            return false;
        }
        if(etDate.getText().toString().equals("")){
            etDate.setError(getResources().getString(R.string.dateError));
            return false;
        }
        if(etDate.getText().toString().matches(".*[a-z].*")){
            etDate.setError(getResources().getString(R.string.dateInvalidError));
            return false;
        }
        return true;
    }

    /* Calculate Age Method */

    public String calculateAge(int year, int month, int yearNow, int monthNow){
        int years = 0;
        int months = 0;

        if(monthNow < month){
            years = year - yearNow;
            months = month - monthNow;
        } else{
            years = year - yearNow - 1;
            months = 12 - (monthNow - month);
        }
        return Integer.toString(years);
    }

}