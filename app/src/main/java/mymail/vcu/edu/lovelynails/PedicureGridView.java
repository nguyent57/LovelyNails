package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PedicureGridView extends AppCompatActivity implements View.OnClickListener{
ImageView serviceText;
String LavenderPackage = "You Choose Lavender Package Pedicure as Your Service Today! Please select your date and time!";
String TropicalPackage = "You Choose Tropical Package Pedicure as Your Service Today! Please select your date and time!";
String RomancePackage = "You Choose Romance Package Pedicure as Your Service Today! Please select your date and time!";
String OrangePackage = "You Choose Orange & Mandarin Pedicure as Your Service Today! Please select your date and time!";

FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view);
        mAuth=FirebaseAuth.getInstance();

        //[FIND VIEW ID ON CLICKS]
        findViewById(R.id.scrollDown).setOnClickListener(this); // BTN
        findViewById(R.id.lavenderPackage).setOnClickListener(this); //lavender image onclick
        //[CHANGE TEXT TO LAVENDER SERVICE ON CLICK]
        serviceText = findViewById(R.id.lavenderPackage); // service text for lavender
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", LavenderPackage);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO TROPICAL SERVICE ON CLICK]
        findViewById(R.id.tropicalNor).setOnClickListener(this);
        serviceText = findViewById(R.id.tropicalNor);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString(); // [FOR EDIT TEXT] - use getText().toString() ---[FOR IMAGE VIEW] - use .toString()
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", TropicalPackage);
                startActivity(intent);
            }
        });

        //[CHANGE TEXT TO ORANGE MANDARIN SERVICE ON CLICK]
        serviceText = findViewById(R.id.OrangeMandarin);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", OrangePackage);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO  ROMANCE MANDARIN SERVICE ON CLICK]
        serviceText = findViewById(R.id.romancePackage);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", RomancePackage);
                startActivity(intent);
            }
        });

    }
    //[ON CLICK METHODS]
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // [IMAGE TEXT]
            case R.id.lavenderPackage:
                //[TOAST MESSAGE] - change intent from PedicureGridView to BookingPage class [ONLY IF OF ONE THE SERVICES IS SELECTED]
                Toast.makeText(getApplicationContext(), "Thank You! You chose Lavender Pedi as your service today. Please proceed to the next step!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,BookingPage.class));
                break;
            case R.id.tropicalNor:
                startActivity(new Intent(this,BookingPage.class));
                Toast.makeText(getApplicationContext(), "Thank You! You chose Tropical Pedi as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.romancePackage:
                startActivity(new Intent(this,BookingPage.class));
                Toast.makeText(getApplicationContext(), "Thank You! You chose Romance Pedi as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.OrangeMandarin:
                startActivity(new Intent(this,BookingPage.class));
                Toast.makeText(getApplicationContext(), "Thank You! You chose Orange and Mandarin Pedi as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scrollDown:
                startActivity(new Intent(this,PedicureGridView2.class));
                overridePendingTransition(R.anim.slide_down_top,R.anim.slide_down_bottom);
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        boolean backPressed = true;
        if (backPressed)
        {
            startActivity(new Intent(this, BookingPage.class));
        }
    }
}
