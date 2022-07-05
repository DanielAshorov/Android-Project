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
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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

        if(email.isEmpty()||password.isEmpty())
        {
            String emptyField = getString(R.string.empty_fields);
            Toast.makeText(LoginActivity.this, emptyField,
                    Toast.LENGTH_SHORT).show();
        }
        if(!email.matches(emailPattern))
        {
            String validEmail = getString(R.string.valid_email);
            inputEmail.setError(validEmail);
        }
        else if(password.length() < 6) {
            String validPassword = getString(R.string.valid_password);
            inputPassword.setError(validPassword);
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
                        if(task.getException().getMessage().contains("password"))
                        {
                            String validPass = getString(R.string.valid_password);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, validPass, Toast.LENGTH_SHORT).show();

                        }
                        else if(task.getException().getMessage().contains("email"))
                        {
                            String validEmail = getString(R.string.valid_email);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, validEmail, Toast.LENGTH_SHORT).show();

                        }
                        else {
                            String serverError = getString(R.string.server_error);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, serverError, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}