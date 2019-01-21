package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PedicureGridView extends AppCompatActivity implements View.OnClickListener{
Button button;
EditText serviceText;
String LavenderText = "You Choose Lavender as Your Service Today! See you soon!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view);
        //[DECLARE ON CLICKS]
        findViewById(R.id.scrollDown).setOnClickListener(this); // scroll down btn
        findViewById(R.id.lavenderImage).setOnClickListener(this); //image onclick
        serviceText = findViewById(R.id.lavenderImage);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.getText().toString();
                Intent intent = new Intent(PedicureGridView.this, BookingPage.class);
                intent.putExtra("NAME",name);
                intent.putExtra("Description", LavenderText);
                startActivity(intent);
            }
        });
    }
    //[CALL ON CLICKS]
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scrollDown:
                startActivity(new Intent(PedicureGridView.this, PedicureGridView2.class));
                overridePendingTransition(R.anim.slide_down_top,R.anim.slide_down_bottom);
                finish();
                break;
            case R.id.lavenderImage:
                Toast.makeText(getApplicationContext(), "Thank You! You chose Lavender as your service today. Please proceed to the next step!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PedicureGridView.this,BookingPage.class));
                break;

        }
    }
}
