package com.example.trivia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button createAcctButton;
    private EditText inputEmail, inputPassword;
    FirebaseAuth authentication;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        loginButton = findViewById(R.id.email_sign_in_button);

        createAcctButton = findViewById(R.id.create_acct_button_login);

        createAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEmail= findViewById(R.id.email);
                inputPassword = findViewById(R.id.password);
                authentication = FirebaseAuth.getInstance();
                Authentication();
            }
        });

    }

    private void Authentication()
    {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if(password.isEmpty()|| password.length() < 6)
        {
            String shortPassword = getString(R.string.short_password);
            inputPassword.setError(shortPassword);
        }
        else
        {
            String waitLogin = getString(R.string.wait_login);
            progressDialog.setMessage(waitLogin);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        progressDialog.cancel();
                    }
                    else
                    {
                        String wrongDetails = getString(R.string.wrong_details);
                        Toast.makeText(LoginActivity.this, wrongDetails, Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
            });
        }
    }
}