package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PedicureGridView2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedicure_grid_view2);
        findViewById(R.id.scrollUp).setOnClickListener(this);
        findViewById(R.id.scrollDown).setOnClickListener(this);
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
