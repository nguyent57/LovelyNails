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
//starts
/*
[IN THIS SCREEN]
- Log in (if logged out)
    - Email is required
        - If email is empty --> toast error message asking for email from input
        - If email does not match the pattern (example@email.com) --> toast error message asking for correct pattern
    - Password is required
        - If password is empty --> toast error message asking for password to input (when sign-up)
        - If password is less than 8 characters --> toast a message to ask user for more secure password
 */
//ends
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // [INITIALIZE]
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        // [FIND VIEW ID] - editText, btn, and progressbar
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        progressBar =  findViewById(R.id.progressbar);
        //[ON CLICK]
        findViewById(R.id.textViewSignup).setOnClickListener(this); // TEXT VIEW -SIGN UP
        findViewById(R.id.buttonLogin).setOnClickListener(this); // BUTTON - LOGIN

    }
    //[USER NAME LOG IN METHOD]
    private void userLogin() {
        // Set email as string and get text
        String email = editTextEmail.getText().toString().trim();
        // Set password as string and get text
        String password = editTextPassword.getText().toString().trim();
        /* [EMPTY EMAIL LOGIC]
         * If the email is empty and user wants to enter the app
         * message will display 'Email is required'
         * return back to if statement until email is entered
        */
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
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
        /*
         * [FIREBASE - SIGN IN WITH EMAIL + PASSWORD]
         */
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                // if the task is successful --> go to profile activity and finish the loop
                if (task.isSuccessful()) {
                    //Log.d(TAG, "createUserWithEmail:success");
                    //FirebaseUser user = mAuth.getCurrentUser();
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
        // [OPEN APP AFTER LOGGED IN] if the current user is empty, go to profile activity to update name and picture
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        // [AS NEW USER]
        switch (view.getId()) {
            case R.id.textViewSignup:
                // [SIGN UP ACTIVITY IS CALLED]
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;

                // [SIGN IN] - userLogin method gets called
            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }

    @Override
    public void onBackPressed() {
    //[BACK PRESS ON PHONE'S SCREEN] - if user accidentally clicks back press on phone, it will give users 2 seconds to make sure users want to exit app
        if (backPressedTime + 2000 > System.currentTimeMillis()) { // 2000 milli = 2s
            backToast.cancel();
            super.onBackPressed();
            //[EXIT APP]
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}