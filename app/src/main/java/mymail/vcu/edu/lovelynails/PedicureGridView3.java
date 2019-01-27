package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PedicureGridView3 extends AppCompatActivity implements View.OnClickListener {
    ImageView serviceText;
    String SheaButter = "You Choose Shea Butter Pedicure as Your Service Today! Please select your date and time!";
    String LavenderNor = "You Choose Lavender Pedicure as Your Service Today! Please select your date and time!";
    String Herbal = "You Choose Herbal Pedicure as Your Service Today! Please select your date and time!";
    String Basic = "You Choose Basic Pedicure as Your Service Today! Please select your date and time!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view3);
        findViewById(R.id.scrollUp).setOnClickListener(this);
        //[CHANGE TEXT TO SHEA BUTTER SERVICE ON CLICK]
        findViewById(R.id.sheaButterNor).setOnClickListener(this);
        serviceText = findViewById(R.id.sheaButterNor);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString(); // [FOR EDIT TEXT] - use getText().toString() ---[FOR IMAGE VIEW] - use .toString()
                Intent intent = new Intent(PedicureGridView3.this, BookingPage.class);
                intent.putExtra("Description", SheaButter);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrollUp:
                startActivity(new Intent(PedicureGridView3.this, PedicureGridView2.class));
                overridePendingTransition(R.anim.slide_up_bottom, R.anim.slide_up_top);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        boolean backPressed = true;
        if (backPressed)
        {
            startActivity(new Intent(this, PedicureGridView2.class));
            overridePendingTransition(R.anim.slide_up_bottom, R.anim.slide_up_top);
        }
    }
}
