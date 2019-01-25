package mymail.vcu.edu.lovelynails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoreInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
    }

    @Override
    public void onBackPressed() {
        boolean backPress = true;
        if (backPress)
        {
            startActivity(new Intent(this, BookingPage.class));
        }
    }
}
