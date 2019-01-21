package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PedicureGridView3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view3);
        findViewById(R.id.scrollUp).setOnClickListener(this);
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
}
