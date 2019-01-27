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

/*
[IN THIS ACTIVITY]
- Register for new users:
    - Email is required
        - If email is empty --> toast error message asking for email from input
        - If email does not match the pattern (example@email.com) --> toast error message asking for correct pattern
    - Password is required
        - If password is empty --> toast error message asking for password to input (when sign-up)
        - If password is less than 8 characters --> toast a message to ask user for more secure password
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    // [INITIALIZE]
    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // [FIND VIEW ID] - editText, btn, and progressbar
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        // [ON CLICK]
        findViewById(R.id.buttonSignUp).setOnClickListener(this); // BTN
        findViewById(R.id.textViewLogin).setOnClickListener(this); // TEXT VIEW
    }
    // [REGISTER USER METHOD] - get email and password as strings
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // [FROM MAIN ACTIVITY] if user click sign up, here is where it leads to.

        /* [EMPTY EMAIL LOGIC]
         * If the email is empty and user wants to enter the app
         * message will display 'Email is required'
         * return back to if statement until email is entered
         */
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus(); // if users press any kind of key or give any input, the input is heard by the respective Listener for that component.
            return; // return back to if statement if user accidentally or purposely hits log in button
        }
        /* [INCORRECT PATTERN LOGIC]
         * If email pattern does not match the email format
         * toast a message "Please enter a valid email"
         * return back to if statement until it does
         */
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        /* [EMPTY PASSWORD LOGIC]
         * If the password is empty and user wants to enter the app
         * message will display 'Password is required'
         * return back to if statement until Password is entered
         */
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        /* [PASSWORD LENGTH LOGIC]
         * The password length is require to be 8 or larger
         * if the password is entered under 8
         * it will display 'Minimum length of password should be 8'
         * until the user enters correct length
         */
        if (password.length() < 8) {
            editTextPassword.setError("Minimum length of password should be 8");
            editTextPassword.requestFocus();
            return;
        }
        //[PROGRESS BAR]
        progressBar.setVisibility(View.VISIBLE);
        //[CREATE USER]
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                // If email is registered, go to profile activity
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
                } else {
                    /*
                     * If user already registered with the current email
                     * a message This email is already registered, letting the users know
                     * that they could log in with the current
                     * email or request to renew password.
                    */
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
    //[BACK PRESS ON PHONE'S SCREEN]
    public void onBackPressed() {
        Boolean backPress = true;
        if (backPress)
        {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    // [START APP]
    public void onClick(View view) {
        // [ONCLICK BUTTONS]

        // [registerUser METHOD GETS CALLED]
       switch (view.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                break;
            //[OPEN MAIN ACTIVITY] - when users click textView 'log in'
            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }
}