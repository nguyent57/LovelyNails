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
- menicure - which kind? --> [UPDATE] text view in Booking page from PedicureGridView page
- pedicure - which kind? --> [UPDATE] text view in Booking page from MedicureGridView page
 */
public class BookingPage extends AppCompatActivity implements View.OnClickListener {
    //[INITIALIZE] - editText, TextView, [INT] - year, month, day
    EditText editTextDate, editTextTime;
    TextView serviceText;
    FirebaseAuth mAuth;
    private int  mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        mAuth = FirebaseAuth.getInstance();

        //[SELECT DATE] - on click listener for edit text to change date from calendar picker
        editTextDate = findViewById(R.id.date);
            editTextDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    mYear = mcurrentDate.get(Calendar.YEAR);
                    mMonth = mcurrentDate.get(Calendar.MONTH);
                    mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                    //[DATE PICKER] - create listener for date
                    DatePickerDialog mDatePicker = new DatePickerDialog(BookingPage.this, new DatePickerDialog.OnDateSetListener() {
                        //[DATE PARAMETER]
                        public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                            Calendar myCalendar = Calendar.getInstance();
                            myCalendar.set(Calendar.YEAR, selectedYear);
                            myCalendar.set(Calendar.MONTH, selectedMonth);
                            myCalendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                            String myFormat = "EEE, MMM dd"; //Change as you need
                            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                            editTextDate.setText(sdf.format(myCalendar.getTime()));

                            mDay = selectedDay;
                            mMonth = selectedMonth;
                            mYear = selectedYear;
                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.show();
                }
            });

        //[TIME - LOGISTIC - NON--OVERLAP]
        editTextTime = findViewById(R.id.time);
            editTextTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        //[SET - UPDATE TEXT VIEW + GET INTENT]
        serviceText = findViewById(R.id.TextViewServices);
        serviceText.setText(getIntent().getStringExtra("Description"));
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
        //[DIFFERENT FORMATS BASED ON]:
        // EEE  [date in week]
        // MMM  [month]
        // dd   [date]
        // hh   [time] - hour
        // mm   [time] - minute
        // ss   [time] - second
        // yyyy [year]
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm EEE, MMM dd");
        String date = formatter.format(today);
        dateView.setText(date);

    }

    @Override
    //[IMPLEMENT ON CLICK FOR BUTTONS]
    public void onClick(View v) {
        switch (v.getId()) {
            //[CASE 1] - BUTTON STORE INFO - DISPLAY STORE INFORMATION FROM STRING.XML
            case R.id.StoreInfo:
                startActivity(new Intent(BookingPage.this, MoreInfo.class));
                finish();
                break;
            //[CASE 2] - BUTTON MEDICURE - CHANGE ACTIVITY FROM BOOKING PAGE TO MEDICUREGIDVIEW CLASS
            case R.id.Medicure:
                startActivity(new Intent(BookingPage.this, MedicureGridView.class));
                finish();
                break;
            //[CASE 3] - BUTTON PEDICURE - CHANGE ACTIVITY FROM BOOKING PAGE TO PERDICUREGIDVIEW CLASS
            case R.id.Pedicure:
                startActivity(new Intent(BookingPage.this, PedicureGridView.class));
                finish();
                break;
            //[CASE 4] - IMAGE VIEW ON CLICK - DESCRIPTION OF WHAT ITS PARENTS BUTTON DOES
            case R.id.aboutMark:
                displayToast(getString(R.string.about_mark));
                //[STOP DISPLAYING MESSAGE]
                break;
            //[CASE 5] - IMAGE VIEW ON CLICK - DESCRIPTION OF WHAT ITS PARENTS BUTTON DOES
            case R.id.MediMark:
                displayToast(getString(R.string.medi_mark));
                //[STOP DISPLAYING MESSAGE]
                break;
            //[CASE 6] - IMAGE VIEW ON CLICK - DESCRIPTION OF WHAT ITS PARENTS BUTTON DOES
            case R.id.PediMark:
                displayToast(getString(R.string.pedi_mark));
                //[STOP DISPLAYING MESSAGE]
                break;
        }

    }
    //[METHOD TO DISPLAY THE DESCRIPTION MESSAGE]
    private void displayToast(String message) {
        //[DISPLAY THE MESSAGE IN STRING.XML]
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }
}

