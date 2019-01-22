package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class PedicureGridView2 extends AppCompatActivity implements View.OnClickListener{
String SilkyMilk = "You Choose Silky Milk as Your Service Today! See you soon!";
ImageView serviceText;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view2);
        mAuth=FirebaseAuth.getInstance();
        findViewById(R.id.scrollUp).setOnClickListener(this);
        findViewById(R.id.scrollDown).setOnClickListener(this);
        serviceText = findViewById(R.id.SilkyMilk);
        serviceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceText.toString();
                Intent intent = new Intent(PedicureGridView2.this, BookingPage.class);
                intent.putExtra("Description", SilkyMilk);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.scrollUp:
                startActivity(new Intent(PedicureGridView2.this, PedicureGridView.class));
                overridePendingTransition(R.anim.slide_up_bottom,R.anim.slide_up_top);
                finish();
                break;
            case R.id.scrollDown:
                startActivity(new Intent(PedicureGridView2.this, PedicureGridView2.class));
                overridePendingTransition(R.anim.slide_down_top,R.anim.slide_down_bottom);
                finish();
                break;
        }
    }
}
