package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtWei, edtHei;
    TextView showResult;
    Button btnBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnBMI(View view) {

            edtHei = findViewById(R.id.idHei);
            edtWei = findViewById(R.id.idWei);
            showResult = findViewById(R.id.idResult);
            btnBMI = findViewById(R.id.idBtnBmi);

            // check if input values are null
            String strHei = edtHei.getText().toString();
            String strWei = edtWei.getText().toString();
            if(!strHei.isEmpty()  && !strWei.isEmpty()) {
                float hei = Float.parseFloat(edtHei.getText().toString());
                float wei = Float.parseFloat(edtWei.getText().toString());

                // height in meter & weight in kilogram
                // wei is already in kg but height in cm so convert into meter
                hei = hei/100;
                float bmi = wei / (hei*hei);
                String str;

                if(bmi < 18) {
                    // below line is only for single activity (MainActivity)
        //            showResult.setText(String.format("BMI : %.1f\nUnderWeight", bmi));

                    Intent obj = new Intent(MainActivity.this, UnderWeightActivity.class);
                    str = String.format("BMI : %.1f\nYou are UnderWeight", bmi);
                    obj.putExtra("Data_Pass", str);
                    startActivity(obj);
                } else if(bmi > 24) {
        //            showResult.setText(String.format("BMI : %.1f\nYou are OverWeight", bmi));

                    Intent obj = new Intent(MainActivity.this, OverWeightActivity.class);
                    str = String.format("BMI : %.1f\nYou are OverWeight.", bmi);
                    obj.putExtra("Data_Pass", str);
                    startActivity(obj);
                } else {
        //            showResult.setText(String.format("BMI : %.1f\nYou are Healthy", bmi));
                    Intent obj = new Intent(MainActivity.this, HealthyActivity.class);
                    str = String.format("BMI : %.1f\nYou are Healthy.", bmi);
                    obj.putExtra("Data_Pass", str);
                    startActivity(obj);
                }
            }   else {
                showResult.setText("Please Enter the Values....");
            }

    }

}
