package com.tallermovil.studentform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tallermovil.studentform.model.Student;

public class Result extends AppCompatActivity {

    /* Resources */
    TextView tvName, tvNum, tvAge, tvCareer;
    ImageView imgCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        /* Linking */
        tvName = findViewById(R.id.tvName);
        tvNum = findViewById(R.id.tvNum);
        tvAge = findViewById(R.id.tvAge);
        tvCareer = findViewById(R.id.tvCareer);
        imgCareer = findViewById(R.id.imgCareer);

        /* Recover Data */
        Bundle bundle = getIntent().getExtras();
        Student student;

        if(bundle !=null){
            student = (Student) bundle.getSerializable("student");
            Toast.makeText(this,  getString(R.string.welcomeMsg) + student.getName(), Toast.LENGTH_LONG).show();

            /* Writing */
            tvName.setText(student.getName());
            tvNum.setText(student.getNum());
            tvAge.setText(student.getAge() + getResources().getString(R.string.age));
            tvCareer.setText(student.getCareer());

            /* Set image */
            setImage(student.getCareer());
        }
    }

    /* Set Image Method */
    public void setImage(String career){
        if(career.equals(getString(R.string.engAero))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.aeroespacial));
        } else if(career.equals(getString(R.string.engCiv))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.civil));
        } else if(career.equals(getString(R.string.engGeo))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.geomatica));
        } else if(career.equals(getString(R.string.engEnv))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.ambiental));
        } else if(career.equals(getString(R.string.engGeoph))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.geofisica));
        } else if(career.equals(getString(R.string.engGeol))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.geologica));
        }else if(career.equals(getString(R.string.engPet))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.petrolera));
        } else if(career.equals(getString(R.string.engMin))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.minas));
        } else if(career.equals(getString(R.string.engCom))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.computacion));
        } else if(career.equals(getString(R.string.engElec))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.electrica));
        } else if(career.equals(getString(R.string.engTel))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.telecomunicaciones));
        } else if(career.equals(getString(R.string.engMech))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.mecanica));
        } else if(career.equals(getString(R.string.engInd))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.industrial));
        } else if(career.equals(getString(R.string.engMecha))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.mecatronica));
        } else if(career.equals(getString(R.string.engBio))){
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.biomedicos));
        }else{
            imgCareer.setImageDrawable(getResources().getDrawable(R.drawable.computacion));
        }
    }

}