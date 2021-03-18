package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    ArrayList buttonsList = new ArrayList();
    TextView resultBox=null;
    Button equalBtn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultBox=findViewById(R.id.resBox);
        buttonsList=getAllButtons();
        initializeEvents(buttonsList,resultBox);
        equalBtn=findViewById(R.id.equalBtn);
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    float result = ExpressionEvaluator.Run(resultBox.getText().toString());
                    resultBox.setText(""+result);
            }
        });



    }

    private ArrayList getAllButtons()
    {
        ArrayList buttonsList=new ArrayList();
        String[] buttonNames={"one","two","three","four","five","six","seven","eight","nine","zero","plus","minus","divide","multiply","dot"};
        for(int i=0;i<buttonNames.length;i++)
        {
            int btnId = getResources().getIdentifier(buttonNames[i]+"Btn","id",getPackageName());
            buttonsList.add((Button)findViewById(btnId));

        }
        return buttonsList;
    }

    private void initializeEvents(ArrayList buttonsList,TextView resultBox)
    {
        for(Object btn:buttonsList)
        {
            try {
                Button fetchedBtn = (Button) btn;
                fetchedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        resultBox.setText(resultBox.getText() + "" + fetchedBtn.getText());
                    }
                });
            }
            catch(Exception exception)
            {
                Log.e(TAG, "Error - "+exception.getMessage());
            }
        }
    }


}