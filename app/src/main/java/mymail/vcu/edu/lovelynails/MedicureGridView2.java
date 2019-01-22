package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MedicureGridView2 extends AppCompatActivity implements View.OnClickListener{
    //[INITIALIZE] imageView and String for basic, french, acrylic, bio
    ImageView serviceText;
    String ShellacMeServ = "You Choose Basic Medicure as Your Service Today! See you soon!";
    String UVGelMeServ = "You Choose French Medicure as Your Service Today! See you soon!";
    String PinkNWhiteMeServ = "You Choose Acrylic Medicure Butter as Your Service Today! See you soon!";
    String OmbreMeServ = "You Choose iBio Seaweed as Your Service Today! See you soon!";

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menicure_grid_view2);
        mAuth=FirebaseAuth.getInstance();
        //[CHANGE TEXT TO SHELLAC MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.Shellac);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", ShellacMeServ);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO UV GEL MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.UVGel);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", UVGelMeServ);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO PINK AND WHITE MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.Pink_White);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", PinkNWhiteMeServ);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO OMBRE MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.Ombre);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", OmbreMeServ);
                startActivity(intent);
            }
        });
        //[SCROLL DOWN BTN]
        findViewById(R.id.scrollUp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrollUp:
                startActivity(new Intent(this, MedicureGridView.class));
                overridePendingTransition(R.anim.slide_up_bottom,R.anim.slide_up_top);
                finish();
                break;
        }
    }
}
