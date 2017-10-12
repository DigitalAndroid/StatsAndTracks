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


public class MainActivity extends AppCompatActivity {
    Button loginBtn, createAccBtn;
    EditText username, password;

    int loginCounter = 5; //Login attempts

    //Intent createAccIntent = new Intent(this, CreateAccountActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Minimal variables
        loginBtn = (Button) findViewById(R.id.loginBtn);
        createAccBtn = (Button) findViewById(R.id.createAccount);
        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);


        //Login button clicked
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If username is correct
                if (usernameExists(username.getText().toString())) {
                    //if password is correct
                    if (passwordMatch(password.getText().toString())) {
                        login(username.getText().toString());
                    } else {
                        loginCounter--;

                        if (loginCounter != 0) {
                            Toast.makeText(getApplicationContext(), "Password incorrect", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login limit exceeded", Toast.LENGTH_LONG).show();
                            loginBtn.setEnabled(false);
                        }
                    }
                }

                //If username is incorrect
                else {
                    loginCounter--;

                    if (loginCounter != 0) {
                        Toast.makeText(getApplicationContext(), "Incorrect login", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login limit exceeded", Toast.LENGTH_LONG).show();
                        loginBtn.setEnabled(false);
                    }


                }
            }
        });

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(v);
            }
        });

    }

    public void createAccount(View view) {
        Intent createAccIntent = new Intent(this, CreateAccountActivity.class);
        String message = "CREATE_ACCOUNT";
        createAccIntent.putExtra(message, message);
        startActivity(createAccIntent);
    }

    public void login(String username){
        Intent loginIntent = new Intent(this, WelcomeActivity.class);
        String message = username;
        loginIntent.putExtra("USERNAME", message);
        startActivity(loginIntent);
    }

    //checks if username is available from credentials.txt
    public boolean usernameExists(String username) {
        //setting up streams, readers and buffers
        String credData = "";
        StringBuffer sBuffer = new StringBuffer();

        InputStream iStream = this.getResources().openRawResource(credentials);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));

        //for indexing through credentials.txt
        int lineNumber = 0;
        int indexNumber = 0;

        if (iStream != null) {
            try {
                while ((credData = bReader.readLine()) != null) {
                    //line 0 is a comment line
                    if (lineNumber != 0) {
                        String credUsername = "";

                        while (credData.charAt(indexNumber) != ',') {
                            credUsername += credData.charAt(indexNumber);
                            indexNumber++;
                        }

                        //if username exists
                        if (credUsername.equalsIgnoreCase(username)) {
                            return true;
                        }
                    }

                    indexNumber = 0;
                    lineNumber++;

                    //adding entire credentials.txt to a StringBuffer
                    sBuffer.append(credData);
                }

                iStream.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //if username does not exist
        return false;
    }

    public boolean passwordMatch(String password) {
        //setting up streams, readers and buffers
        String credData = "";
        StringBuffer sBuffer = new StringBuffer();

        InputStream iStream = this.getResources().openRawResource(credentials);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));

        //for indexing through credentials.txt
        int lineNumber = 0;
        int indexNumber = 0;
        int credDetail = 0;

        if (iStream != null) {
            try {
                while ((credData = bReader.readLine()) != null) {
                    //line 0 is a comment line
                    if (lineNumber != 0) {
                        String credPassword = "";

                        while (credData.charAt(indexNumber) != ',') {
                            indexNumber++;
                        }

                        if (credData.charAt(indexNumber) == ',') {
                            indexNumber++;
                        }

                        while (credData.charAt(indexNumber) != ',') {
                            credPassword += credData.charAt(indexNumber);
                            indexNumber++;
                        }

                        //if password matches
                        if (credPassword.equalsIgnoreCase(password)) {
                            return true;
                        }
                    }

                    indexNumber = 0;
                    lineNumber++;

                    //adding entire credentials.txt to a StringBuffer
                    sBuffer.append(credData);
                }

                iStream.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //if password is incorrect
        return false;
    }
}