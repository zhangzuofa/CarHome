package lanou.carhome.personnal.setactivity.setuppush;

import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;
import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/10/14.
 */
public class SetUpPushActivity extends BaseActivity implements View.OnClickListener {

    private Switch systemSwitch;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private ImageView returnImg;
    private LinearLayout setup_push_gettime_ll;
    private PopupWindow timePopupWindow;
    private String[] dates;
    private NumberPicker startMinuteicker;
    private NumberPicker endMinuteicker;
    private View view;
    private TextView timeTv;

    @Override
    protected int setLayout() {
        return R.layout.setuppushactivity;
    }

    @Override
    protected void initView() {
        returnImg = bindView(R.id.setup_puch_img);
        returnImg.setOnClickListener(this);
        systemSwitch = bindView(R.id.setup_push_swich);
        setup_push_gettime_ll = bindView(R.id.setup_push_gettime_ll);
        setup_push_gettime_ll.setOnClickListener(this);
        view = LayoutInflater.from(this).inflate(R.layout.popuwindowgettime,null);
        startMinuteicker =bindView(R.id.from_minuteicker,view);
        endMinuteicker = bindView(R.id.to_minuteicker,view);
        Button okBtn = bindView(R.id.pupurwindow_ok_btn,view);
        okBtn.setOnClickListener(this);
        Button closeBtn = bindView(R.id.pupurwindow_close_btn,view);
        closeBtn.setOnClickListener(this);
        timeTv = bindView(R.id.setup_time_text);

    }

    @Override
    protected void initDate() {
        sp = getSharedPreferences("setPush", MODE_PRIVATE);
        ed = sp.edit();
        ed.commit();
        initSystemSwitch();
        dates = new String[]{"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00"
                ,"12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
        timeTv.setText("每日:"+ sp.getInt("start",00) +":00"+"-"+sp.getInt("end",24) +":00");


    }

    private void initSystemSwitch() {





        systemSwitch.setChecked(sp.getBoolean("systemSwitch", true));
//        ed.putBoolean("systemSwitch",true);
//        sp.getBoolean("systemSwitch",false);
        systemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    ed.putBoolean("systemSwitch", isChecked);
                    ed.commit();
                if (isChecked){
                    JPushInterface.onResume(getApplicationContext());
                }else {
                    JPushInterface.stopPush(getApplicationContext());
                }







            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (timePopupWindow !=null&&timePopupWindow.isShowing()){
            timePopupWindow.dismiss();

            timePopupWindow = null;
        }
        return super.onTouchEvent(event);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setup_puch_img:

                if (timePopupWindow !=null&&timePopupWindow.isShowing() ){
                    timePopupWindow.dismiss();
                }else {
                    this.finish();
                }


                break;
            case R.id.setup_push_gettime_ll:
                if (timePopupWindow ==null||!timePopupWindow.isShowing() ){


                timePopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,400);
                timePopupWindow.setAnimationStyle(R.style.push_date_anim);

                timePopupWindow.showAtLocation(returnImg, Gravity.BOTTOM, 0, 0);
                timePopupWindow.isShowing();
                    startMinuteicker.setDisplayedValues(dates);
                    startMinuteicker.setMinValue(0);
                    startMinuteicker.setMaxValue(dates.length - 1);

                    endMinuteicker.setDisplayedValues(dates);
                    endMinuteicker.setMinValue(0);
                    endMinuteicker.setMaxValue(dates.length - 1);

                timePopupWindow.setFocusable(false);
                timePopupWindow.setOutsideTouchable(true);

                } else {
                    timePopupWindow.dismiss();
                }


                break;
            case R.id.pupurwindow_ok_btn:
             int start= startMinuteicker.getValue();

             int end = endMinuteicker.getValue();
                ed.putInt("start",start);
                ed.putInt("end",end);
            ed.commit();

                timeTv.setText("每日:"+ start +":00"+"-"+end +":00");

               timePopupWindow.dismiss();
                break;
            case R.id.pupurwindow_close_btn:
                if (timePopupWindow != null|| timePopupWindow.isShowing()){

                    timePopupWindow.dismiss();
                }
                break;

        }


    }

    @Override
    public void onBackPressed() {

        if (timePopupWindow !=null&&timePopupWindow.isShowing() ){
            timePopupWindow.dismiss();
        }else {
            this.finish();
        }

    }
}
