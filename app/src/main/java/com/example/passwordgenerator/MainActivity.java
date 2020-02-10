package com.example.passwordgenerator;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    EditText editText;
    Button button;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText.setText("6");
        button = findViewById(R.id.button);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        final TextView textView = findViewById(R.id.textView);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked())
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Выберите хотя бы одну галочку", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{

                    PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                            .useDigits(checkBox1.isChecked())
                            .useLower(checkBox2.isChecked())
                            .useUpper(checkBox3.isChecked())
                            .usePunctuation(checkBox4.isChecked())
                            .build();
                    String size = editText.getText().toString();
                    if(Integer.parseInt(size)== 0 || Integer.parseInt(size) > 20)
                    {
                        Toast toast=Toast.makeText(getApplicationContext(),
                                "Введите число от 1 до 19", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                        {
                    String[] passwords = new String[10];
                    StringBuilder all = new StringBuilder(new String());
                    for(int i = 0; i<10; i++){
                     passwords[i] = passwordGenerator.generate(Integer.parseInt(size));
                     all.append(passwords[i]).append("\n");
                    }


                    textView.setText(all.toString());
                        }
                }
            }
        };
        button.setOnClickListener(oclBtn);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
