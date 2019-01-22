package mymail.vcu.edu.lovelynails;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
/*
[IN THIS SCREEN]
- [SAVE] information to database and [VERIFY] email from user

 */
public class ProfileActivity extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    //[INITIALIZE GLOBAL VARIABLES]
    TextView textView;
    ImageView imageView;
    EditText editText;

    Uri uriProfileImage;
    ProgressBar progressBar;

    String profileImageUrl;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        //[DECLARE] edit text, image view, progress bar, and text view
        editText = findViewById(R.id.editTextDisplayName);
        imageView =  findViewById(R.id.imageView);
        progressBar =  findViewById(R.id.progressbar);
        textView =  findViewById(R.id.textViewVerified);
        //[SET ON CLICK FOR IMAGE VIEW]
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            //[CALL ON showImageChooser() METHOD]
            public void onClick(View view) {
                showImageChooser();
            }
        });

        //[LOAD USER INFORMATION METHOD GETS CALLED]
        loadUserInformation();
        //[ON CLICK FOR SAVE BUTTON] - save user information method will be executed once clicked AND goes to booking page
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
                Intent intent = new Intent(ProfileActivity.this, BookingPage.class);
                startActivity(intent);
            }
        });
    }


    @Override
    // [START APP]
    protected void onStart() {
        super.onStart();
        //[NOT SIGNED OUT] - go to main activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    // [LOAD USER INFO METHOD]
    private void loadUserInformation() {
        //[SAVE DATA TO FIREBASE]
        final FirebaseUser user = mAuth.getCurrentUser();
        //[GET USER PHOTO]
        // if there's no image selected, then get image from user
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imageView);
            }

            if (user.getDisplayName() != null) {
                editText.setText(user.getDisplayName());
            }
            //[UPDATE TEXT-VIEW WHEN EMAIL IS VERIFIED OR NOT]
            if (user.isEmailVerified()) {
                //[WHEN VERIFIED]
                textView.setText("Your email has been verified, Thank you for choosing us!");
            } else {
                //[WHEN NOT VERIFIED]
                textView.setText("Email Not Verified (Click to Verify)");
                //[SET ON CLICK FOR TEXTVIEW]
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //[MESSAGE] - WHEN TEXTVIEW IS CLICKED, A MESSAGE IS APPEARED ON SCREEN AS REMINDER FOR USER TO CHECK EMAIL
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ProfileActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }

    //[SAVE USER INFO METHOD]
    private void saveUserInformation() {
        //[SET editText AS STRING]
        String displayName = editText.getText().toString();
        //[THROW ERROR MESSAGE WHEN DISPLAY NAME IS EMPTY]
        if (displayName.isEmpty()) {
            editText.setError("Name required");
            editText.requestFocus();
            return;
        }
        //[SAVE USER INFO TO FIREBASE]
        FirebaseUser user = mAuth.getCurrentUser();
        //[GET USER PROFILE IMAGE]
        if (user != null && profileImageUrl != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //[UP LOAD IMAGE TO FIREBASE STORAGE METHOD]
    private void uploadImageToFirebaseStorage() {
        //[UPLOAD TO FIREBASE STORAGE]
        StorageReference profileImageRef =
                FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");

        if (uriProfileImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            profileImageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    //[GENERATE MENU IN TASK BAR]
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    //[LOG OUT OPTION - task bar]
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLogout:
                //[SIGN OUT FROM FIRE BASE]
                FirebaseAuth.getInstance().signOut();
                finish();
                //[GOES BACK TO MAIN ACTIVITY SCREEN]
                startActivity(new Intent(this, MainActivity.class));

                break;
        }

        return true;
    }
    //[IMAGE CHOOSER METHOD]
    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }

}
