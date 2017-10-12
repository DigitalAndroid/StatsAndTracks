package com.statsandtracksexample.statsandtracks_10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.statsandtracksexample.statsandtracks_10.R.raw.credentials;


public class CreateAccountActivity extends AppCompatActivity {

    EditText username;
    Button proceedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = (EditText) findViewById(R.id.createUsername);
        proceedBtn = (Button) findViewById(R.id.createUsernameProceed);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if username available, go to next activity
                if(usernameAvailable(username.getText().toString()) == true){
                    createAccountPassword(username.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Username taken", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //goes to next activity
    public void createAccountPassword(String username){
        Intent createAccountPasswordIntent = new Intent(this, CreateAccountPasswordActivity.class);
        String message = "USERNAME";
        createAccountPasswordIntent.putExtra(message, username);
        startActivity(createAccountPasswordIntent);
    }

    //checks if username is available from credentials.txt
    public boolean usernameAvailable(String username){
        //setting up streams, readers and buffers
        String credData = "";
        StringBuffer sBuffer = new StringBuffer();

        InputStream iStream = this.getResources().openRawResource(credentials);
       // iStream = (InputStream) findViewById(R.raw.credentials);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));

        //for indexing through credentials.txt
        int lineNumber = 0;
        int indexNumber = 0;

        if(iStream != null){
            try{
                while((credData = bReader.readLine()) != null){
                    //line 0 is a comment line
                    if(lineNumber != 0){
                        String credUsername = "";

                        while(credData.charAt(indexNumber) != ','){
                            credUsername += credData.charAt(indexNumber);
                            indexNumber ++;
                        }

                        //if username already exists
                        if(credUsername.equalsIgnoreCase(username)){
                            return false;
                        }
                    }

                    indexNumber = 0;
                    lineNumber ++;

                    //adding entire credentials.txt to a StringBuffer
                    sBuffer.append(credData);
                }

                iStream.close();


            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        //if username is available
        return true;
    }
}
