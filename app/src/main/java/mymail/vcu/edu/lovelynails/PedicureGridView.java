package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedicureGridView extends AppCompatActivity implements View.OnClickListener{
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view);
        //[DECLARE ON CLICKS]
        findViewById(R.id.scrollDown).setOnClickListener(this);
        findViewById(R.id.lavanderImage).setOnClickListener(this);
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
            case R.id.lavanderImage:
                startActivity(new Intent(PedicureGridView.this,BookingPage.class));
        }
    }
}
