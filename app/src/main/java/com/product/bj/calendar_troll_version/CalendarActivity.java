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

    //TODO calendarView的各項數值
    int dayBlockCommonWidth;
    int defaultRowHeight;
    int dayBlockCommonHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Log.d("CalendarLog","This is Calendar Activity");

        //TODO 需要取得的數值:螢幕長寬,statusBar的height,useHeight
        getScreenSize();
        getStatusBarHeight();
        useHeight = screenHeight - statusBarHeight;

        //TODO 設定topBar的height,calendarView的height
        setTopBarHeight();
        setCalendarViewHeight();

        //TODO 設定topBar的year,month文字的大小,左右箭頭的大小
        setTopBarYearMonthSize();
        setArrowSize();

        //TODO 設定calendarView每個row,block的width,height
        setDefaultRowWidthHeight();
        setCalendarRowHeight();

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

    private void setDefaultRowWidthHeight()
    {
        int commonWidth = (int)(screenWidth/7); //前面6個dayBlock都使用commonWidth
        int commonHeight = (int)(calendarViewHeight*0.055);

        RelativeLayout sundayDayBlock = (RelativeLayout)findViewById(R.id.Sunday);
        ViewGroup.LayoutParams params1 = sundayDayBlock.getLayoutParams();
        params1.width = commonWidth;
        params1.height = commonHeight;
        sundayDayBlock.setLayoutParams(params1);

        RelativeLayout mondayDayBlock = (RelativeLayout)findViewById(R.id.Monday);
        ViewGroup.LayoutParams params2 = mondayDayBlock.getLayoutParams();
        params2.width = commonWidth;
        params2.height = commonHeight;
        mondayDayBlock.setLayoutParams(params1);

        RelativeLayout tuesdayDayBlock = (RelativeLayout)findViewById(R.id.Tuesday);
        ViewGroup.LayoutParams params3 = tuesdayDayBlock.getLayoutParams();
        params3.width = commonWidth;
        params3.height = commonHeight;
        tuesdayDayBlock.setLayoutParams(params3);

        RelativeLayout wednesdayDayBlock = (RelativeLayout)findViewById(R.id.Wednesday);
        ViewGroup.LayoutParams params4 = wednesdayDayBlock.getLayoutParams();
        params4.width = commonWidth;
        params4.height = commonHeight;
        wednesdayDayBlock.setLayoutParams(params4);

        RelativeLayout thursdayDayBlock = (RelativeLayout)findViewById(R.id.Thursday);
        ViewGroup.LayoutParams params5 = thursdayDayBlock.getLayoutParams();
        params5.width = commonWidth;
        params5.height = commonHeight;
        thursdayDayBlock.setLayoutParams(params5);

        RelativeLayout fridayDayBlock = (RelativeLayout)findViewById(R.id.Friday);
        ViewGroup.LayoutParams params6 = fridayDayBlock.getLayoutParams();
        params6.width = commonWidth;
        params6.height = commonHeight;
        fridayDayBlock.setLayoutParams(params6);

        RelativeLayout saturdayDayBlock = (RelativeLayout)findViewById(R.id.Saturday);
        ViewGroup.LayoutParams params7 = saturdayDayBlock.getLayoutParams();
        params7.width = screenWidth - commonWidth*6;
        params7.height = commonHeight;
        saturdayDayBlock.setLayoutParams(params7);

        dayBlockCommonWidth = commonWidth;
        defaultRowHeight = commonHeight;
    }

    private void setCalendarRowHeight()
    {
        int commonHeight = (int)(calendarViewHeight*0.945/6);
        dayBlockCommonHeight = commonHeight;

        String IDfoo;

        for(int i=1; i<=35; ++i)
        {
            IDfoo = "DayBlock"+Integer.toString(i);
            int resID = getResources().getIdentifier(IDfoo,"id",getPackageName());
            RelativeLayout dayBlock = (RelativeLayout)findViewById(resID);
            ViewGroup.LayoutParams dayBlockParams = dayBlock.getLayoutParams();
            dayBlockParams.height = commonHeight;
        }

        commonHeight = calendarViewHeight - defaultRowHeight - dayBlockCommonHeight*5;

        for(int i=36; i<=42; ++i)
        {
            IDfoo = "DayBlock"+Integer.toString(i);
            int resID = getResources().getIdentifier(IDfoo,"id",getPackageName());
            RelativeLayout dayBlock = (RelativeLayout)findViewById(resID);
            ViewGroup.LayoutParams dayBlockParams = dayBlock.getLayoutParams();
            dayBlockParams.height = commonHeight;
        }
    }
}
