package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
[IN THIS SCREEN]
- log in (if logged out)
    - email is required
        - if email is empty --> toast error message asking for email from input
        - if email does not match the pattern (example@email.com) --> toast error message asking for correct pattern
    - password is required
        - if password is empty --> toast error message asking for password to input (when sign-up)
        - if password is less than 8 characters --> toast a message to ask user for more secure password
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EmailPassword";
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        // declare all texts, buttons, and progress bar
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        progressBar =  findViewById(R.id.progressbar);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

    }

    private void userLogin() {
        // set email as string and get text
        String email = editTextEmail.getText().toString().trim();
        // set password as string and get text
        String password = editTextPassword.getText().toString().trim();
        // if the email is empty and user wants to enter the app, message will display Email is required (return back to if statement until email is entered)
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        // if email pattern does not matche the email format --> return back to if statement until it does
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        // if the password is empty and user wants to enter the app, message will display Password is required (return back to if statement until Password is entered)
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        // the password length is require to be 8 or larger, if the password is entered under 6, it will display Minimum length of password should be 6
        // until the user enters correct length
        if (password.length() < 8) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        // show view - progress bar
        progressBar.setVisibility(View.VISIBLE);
        // firebase - sign in with email-password
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                // if the task is successful --> go to profile activity and finish the loop
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // when open the app after log in, if the current user is empty, go to profile activity to update name and picture
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        // when click sign up (as new user)
        switch (view.getId()) {
            case R.id.textViewSignup:
                // go to sign up class
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;

                // if already have an account, muse email and password to log in (go back to private void userLogin method to execute the method)
            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
}