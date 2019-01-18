package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    // declare progress bar, email, and password edit text
    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        // on click
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }
    // register user (get email and password as strings)
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        // from main activity, if user click sign up, here is where it leads to.
        // if email is empty and user wants to log in, it will display an error message "Email is required
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus(); // if users press any kind of key or give any input, the input is heard by the respective Listener for that component.
            return; // return back to if statement if user accidentally or purposely hits log in button
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
        // visible progress bar when user chooses a profile picture
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                // if email is registered, go to profile activity to choose profile picture and name display or
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
                } else {
                    // if user already registered with the current email, a message This email is already registered, letting the users know what they could log in with the current
                    // email or request to renew password.
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    @Override
    // start app again after turning off
    public void onClick(View view) {
        // if user clicks sign up, user will be led back to the "private void registerUser" method to execute the action
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                break;
            // if user clicks log-in (after finish entering email and password), users will be led back to the main activity to execute the action
            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}