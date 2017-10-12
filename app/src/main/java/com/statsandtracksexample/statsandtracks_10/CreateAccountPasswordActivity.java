package com.statsandtracksexample.statsandtracks_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.statsandtracksexample.statsandtracks_10.R.id.createPassword;

public class CreateAccountPasswordActivity extends AppCompatActivity {

    EditText password;
    Button proceedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_password);

        password = (EditText) findViewById(createPassword);
        proceedBtn = (Button) findViewById(R.id.createPasswordProceed);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountNameWeight(password.getText().toString());

            }
        });
    }

    //goes to next activity
    public void createAccountNameWeight(String password){
        Intent createAccountNameWeightIntent = new Intent(this, CreateAccountNameWeightActivity.class);
        String message = "PASSWORD";
        createAccountNameWeightIntent.putExtra(message, password);
        startActivity(createAccountNameWeightIntent);
    }

}
