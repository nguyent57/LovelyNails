package mymail.vcu.edu.lovelynails;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
[IN THIS SCREEN]
- choose date from calendar
- choose time from time picker
- choose services from drop down options
- store info button - hour + phone + prices
- menicure - which kind?
- pedicure - which kind?
 */
public class BookingPage extends AppCompatActivity implements View.OnClickListener {
    EditText editTextDate, editTextTime;
    TextView serviceText;
    FirebaseAuth mAuth;
    private int  mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        mAuth = FirebaseAuth.getInstance();
        //[SELECT DATE] - on click listener for text view date
        editTextDate = findViewById(R.id.date);
            editTextDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    mYear = mcurrentDate.get(Calendar.YEAR);
                    mMonth = mcurrentDate.get(Calendar.MONTH);
                    mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker = new DatePickerDialog(BookingPage.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            Calendar myCalendar = Calendar.getInstance();
                            myCalendar.set(Calendar.YEAR, selectedyear);
                            myCalendar.set(Calendar.MONTH, selectedmonth);
                            myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                            String myFormat = "MMM-dd-yyyy"; //Change as you need
                            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                            editTextDate.setText(sdf.format(myCalendar.getTime()));

                            mDay = selectedday;
                            mMonth = selectedmonth;
                            mYear = selectedyear;
                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.show();
                }
            });
        editTextTime = findViewById(R.id.time);
            editTextTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        serviceText = findViewById(R.id.services);
        //[ONCLICK]- BUTTONS
        findViewById(R.id.StoreInfo).setOnClickListener(this);
        findViewById(R.id.Medicure).setOnClickListener(this);
        findViewById(R.id.Pedicure).setOnClickListener(this);

        //[QUESTION MARK BTNS]
        findViewById(R.id.aboutMark).setOnClickListener(this);
        findViewById(R.id.MediMark).setOnClickListener(this);
        findViewById(R.id.PediMark).setOnClickListener(this);

        //[SET DATE] - call on setDate method
        TextView dateView = findViewById(R.id.BookingHD);
        setDate(dateView);
    }
    //[SET TIME - DATE ON TOP OF SCREEN]
    private void setDate(TextView dateView) {
        Date today = Calendar.getInstance().getTime();
        //[DIFFERENT FORMATS]:
        // yyyy-MM-dd
        // EEE MMM dd hh:mm:ss yyyy - result [date in week] [month] [date] [time] [year]
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss EEE MMM-dd-yyyy");
        String date = formatter.format(today);
        dateView.setText(date);
    }

    @Override
    //[IMPLEMENT ON CLICK FOR BUTTONS]
    public void onClick(View v) {
        switch (v.getId()) {
            //[CASE 1] - BUTTON STORE INFO
            case R.id.StoreInfo:
                startActivity(new Intent(BookingPage.this, MoreInfo.class));
                finish();
                break;
            //[CASE 2] - BUTTON MEDICURE
            case R.id.Medicure:
                startActivity(new Intent(BookingPage.this, MedicureGridView.class));
                finish();
                break;
            //[CASE 3] - BUTTON PEDICURE
            case R.id.Pedicure:
                startActivity(new Intent(BookingPage.this, PedicureGridView.class));
                finish();
                break;
            //[CASE 4] -
            case R.id.aboutMark:
                startActivity(new Intent(BookingPage.this,BookingPage.class));
                Toast.makeText(this, "Select me for store info :)", Toast.LENGTH_LONG).show();
                finish();
                break;

            case R.id.MediMark:
                startActivity(new Intent(BookingPage.this,BookingPage.class));
                Toast.makeText(this, "Select me for your Medicure options :)", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.PediMark:
                startActivity(new Intent(BookingPage.this,BookingPage.class));
                Toast.makeText(this, "Select me for your Pedicure options :)", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

    }
}

