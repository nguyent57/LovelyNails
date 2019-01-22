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
Button button;
String LavenderPackage = "You Choose Lavender Package as Your Service Today! See you soon!";
String TropicalPackage = "You Choose Tropical Package as Your Service Today! See you soon!";
String SheaButter = "You Choose Shea Butter as Your Service Today! See you soon!";
String GreenTea = "You Choose Green Tea as Your Service Today! See you soon!";

FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view);
        mAuth=FirebaseAuth.getInstance();
        //[FIND VIEW ID ON CLICKS]
        findViewById(R.id.scrollDown).setOnClickListener(this); // BTN
        findViewById(R.id.lavenderImage).setOnClickListener(this); //lavender image onclick
        //[CHANGE TEXT TO LAVENDER SERVICE ON CLICK]
        serviceText = findViewById(R.id.lavenderImage); // service text for lavender
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
        findViewById(R.id.tropicalImage).setOnClickListener(this);
        serviceText = findViewById(R.id.tropicalImage);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString(); // [FOR EDIT TEXT] - use getText().toString() ---[FOR IMAGE VIEW] - use .toString()
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", TropicalPackage);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO SHEA BUTTER SERVICE ON CLICK]
        findViewById(R.id.sheaButterImage).setOnClickListener(this);
        serviceText = findViewById(R.id.sheaButterImage);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString(); // [FOR EDIT TEXT] - use getText().toString() ---[FOR IMAGE VIEW] - use .toString()
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", SheaButter);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO GREEN TEA SERVICE ON CLICK]
        findViewById(R.id.GreenTeaImage).setOnClickListener(this);
        serviceText = findViewById(R.id.GreenTeaImage);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString(); // [FOR EDIT TEXT] - use getText().toString() ---[FOR IMAGE VIEW] - use .toString()
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", GreenTea);
                startActivity(intent);
            }
        });

    }
    //[ON CLICK METHODS]
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // [IMAGE TEXT]
            case R.id.lavenderImage:
                //[TOAST MESSAGE] - change intent from PedicureGridView to BookingPage class [ONLY IF OF ONE THE SERVICES IS SELECTED]
                Toast.makeText(getApplicationContext(), "Thank You! You chose Lavender as your service today. Please proceed to the next step!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,BookingPage.class));
                break;
            case R.id.tropicalImage:
                startActivity(new Intent(this,BookingPage.class));
                Toast.makeText(getApplicationContext(), "Thank You! You chose Tropical as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sheaButterImage:
                startActivity(new Intent(this,BookingPage.class));
                Toast.makeText(getApplicationContext(), "Thank You! You chose Shea Butter as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.GreenTeaImage:
                startActivity(new Intent(this,BookingPage.class));
                Toast.makeText(getApplicationContext(), "Thank You! You chose Green Tea as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scrollDown:
                startActivity(new Intent(this,PedicureGridView2.class));
                overridePendingTransition(R.anim.slide_down_top,R.anim.slide_down_bottom);
                finish();
                break;

        }
    }
}
