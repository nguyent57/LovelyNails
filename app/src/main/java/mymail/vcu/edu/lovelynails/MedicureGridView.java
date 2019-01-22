package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import javax.net.ssl.SSLEngineResult;

public class MedicureGridView extends AppCompatActivity implements View.OnClickListener {
    //[INITIALIZE] imageView and String for basic, french, acrylic, bio
    ImageView serviceText;
    String BasicMeServ = "You Choose Basic Medicure as Your Service Today! See you soon!";
    String FrenchMeServ = "You Choose French Medicure as Your Service Today! See you soon!";
    String AcrylicMeServ = "You Choose Acrylic Medicure Butter as Your Service Today! See you soon!";
    String iBioSWServ = "You Choose iBio Seaweed as Your Service Today! See you soon!";

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicure_grid_view);
        mAuth=FirebaseAuth.getInstance();
        //[CHANGE TEXT TO BASIC MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.BasicMedi);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", BasicMeServ);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO FRENCH MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.FrenchMedi);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", FrenchMeServ);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO ACRYLIC MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.AcrylicMedi);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", AcrylicMeServ);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO iBIO-SEAWEED MEDICURE SERVICE ON CLICK]
        serviceText = findViewById(R.id.iBioSeaweed);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(MedicureGridView.this, BookingPage.class);
                intent.putExtra("Description", iBioSWServ);
                startActivity(intent);
            }
        });
        // [SCROLL DOWN BTN]
        findViewById(R.id.scrollDown).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrollDown:
                startActivity(new Intent(this,MedicureGridView2.class));
                overridePendingTransition(R.anim.slide_down_top,R.anim.slide_down_bottom);
                break;
        }
    }
}
