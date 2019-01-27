package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class PedicureGridView2 extends AppCompatActivity implements View.OnClickListener{
ImageView serviceText;
String SilkyMilkSpa = "You Choose Silky Milk Pedicure as Your Service Today! Please select your date and time!";
String PurrissimaSpa = "You Choose Purrissima Pedicure as Your Service Today! Please select your date and time!";
String GreenTea = "You Choose Green Tea Pedicure as Your Service Today! Please select your date and time!";
String HoneyPearlPackage = "You Choose Honey Pearl Package Pedicure as Your Service Today! Please select your date and time!";


FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view2);
        mAuth=FirebaseAuth.getInstance();
        findViewById(R.id.scrollUp).setOnClickListener(this);
        findViewById(R.id.scrollDown).setOnClickListener(this);
        //[CHANGE TEXT TO SILKY MILK SERVICE ON CLICK]
        serviceText = findViewById(R.id.SilkyMilk);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(PedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", SilkyMilkSpa);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO PURRISSIMA SERVICE ON CLICK]
        serviceText = findViewById(R.id.Purrissima);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(PedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", PurrissimaSpa);
                startActivity(intent);
            }
        });
        //[CHANGE TEXT TO GREEN TEA SERVICE ON CLICK]
        findViewById(R.id.GreenTea).setOnClickListener(this);
        serviceText = findViewById(R.id.GreenTea);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString(); // [FOR EDIT TEXT] - use getText().toString() ---[FOR IMAGE VIEW] - use .toString()
                Intent intent = new Intent(PedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", GreenTea);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrollUp:
                startActivity(new Intent(PedicureGridView2.this, PedicureGridView.class));
                overridePendingTransition(R.anim.slide_up_bottom, R.anim.slide_up_top);
                finish();
                break;
            case R.id.scrollDown:
                startActivity(new Intent(PedicureGridView2.this, PedicureGridView3.class));
                overridePendingTransition(R.anim.slide_down_top, R.anim.slide_down_bottom);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        boolean backPressed = true;
        if (backPressed)
        {
            startActivity(new Intent(this, PedicureGridView.class));
            overridePendingTransition(R.anim.slide_up_bottom, R.anim.slide_up_top);
        }
    }
}
