package com.product.bj.calendar_troll_version;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    int defaultYear = 2016;
    int defaultMonth = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCalendarActivity();
    }

    private void startCalendarActivity()
    {
        Intent calendarActivityIntent = new Intent(this,CalendarActivity.class);

        startActivity(calendarActivityIntent);

        finish();
    }
}
