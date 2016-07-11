package com.product.bj.calendar_troll_version;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity
{
    //TODO 基本數值
    int screenWidth;
    int screenHeight;
    int statusBarHeight;
    int useHeight;

    //TODO 設定的數值
    int topBarHeight;
    int calendarViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Log.d("CalendarLog","This is Calendar Activity");

        //TODO 需要取得的數值1:螢幕長寬,statusBar的height,useHeight
        getScreenSize();
        getStatusBarHeight();
        useHeight = screenHeight - statusBarHeight;

        //TODO 設定topBar的height,calendarView的height
        setTopBarHeight();
        setCalendarViewHeight();

        //TODO 設定topBar的year,month文字的大小,左右箭頭的大小
        setTopBarYearMonthSize();
        setArrowSize();

    }

    private void getScreenSize()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        Log.d("CalendarLog" , "Screen height is : "+screenHeight);
        Log.d("CalendarLog" , "Screen width is : "+screenWidth);
    }

    private void getStatusBarHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        Log.d("CalendarLog" , "Height of status bar : "+result);
        statusBarHeight = result;
    }

    private void setTopBarHeight()
    {
        RelativeLayout topBar = (RelativeLayout) findViewById(R.id.TopBar);
        ViewGroup.LayoutParams params = topBar.getLayoutParams();

        topBarHeight = (int)(useHeight*0.07);

        params.height = topBarHeight;
        topBar.setLayoutParams(params);
        Log.d("CalendarLog" , "Height of topBar : "+topBarHeight);
    }

    private void setCalendarViewHeight()
    {
        calendarViewHeight = useHeight - topBarHeight;
        Log.d("CalendarLog" , "Height of calendarView : "+calendarViewHeight);
    }

    private void setTopBarYearMonthSize()
    {
        RelativeLayout topBarMonthYearContainer = (RelativeLayout)findViewById(R.id.MonthYearContainer);
        ViewGroup.LayoutParams params = topBarMonthYearContainer.getLayoutParams();
        params.height = (int)(topBarHeight*85/90);
        topBarMonthYearContainer.setLayoutParams(params);

        int monthTextSize = (int)(topBarHeight*0.54);
        int yearTextSize = (int)(topBarHeight*0.21);

        TextView topBarMonthTextView = (TextView)findViewById(R.id.TopBarMonthView);
        TextView topBarYearTextView = (TextView)findViewById(R.id.TopBarYearView);

        topBarMonthTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,monthTextSize);
        topBarYearTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,yearTextSize);
    }

    private void setArrowSize()
    {
        ImageView arrowLeft = (ImageView)findViewById(R.id.ArrowLeft);
        ImageView arrowRight = (ImageView)findViewById(R.id.ArrowRight);

        int commonWidth = (int)(topBarHeight/3*2);
        int commonHeight = (int)(topBarHeight/2);

        ViewGroup.LayoutParams paramsLeft = arrowLeft.getLayoutParams();
        paramsLeft.height = commonHeight;
        paramsLeft.width = commonWidth;

        ViewGroup.LayoutParams paramsRight = arrowRight.getLayoutParams();
        paramsRight.height = commonHeight;
        paramsRight.width = commonWidth;

        arrowLeft.setLayoutParams(paramsLeft);
        arrowRight.setLayoutParams(paramsRight);
    }
}
