package lanou.carhome.find.timebuy;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import lanou.carhome.R;

/**
 * Created by dllo on 16/10/15.
 */

    public class LimitedBuyTextView {
        TextView day;
        TextView hours;
        TextView minutes;
        TextView seconds;
        String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        private TextView days;
        private Long finishL;
        Long nowL;
        private Long remainderL;
        Context context;
        String time;
        private CountDownTimer countDownTimer;
        private View view;

        public LimitedBuyTextView(Context context, String time) {
            this.context = context;
            this.time = time;
        }

        public View initTime() {
            try {
                view = LayoutInflater.from(context).inflate(R.layout.limbuy, null);

                finishL = stringToLong(time);
                nowL = System.currentTimeMillis();
                countDownTimer = new CountDownTimer(Integer.MAX_VALUE , 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        nowL = System.currentTimeMillis();
                        remainderL = finishL - nowL;
                        int remainderDay = (int) (remainderL / 1000 / 60 / 60 / 24);
                        int remainderHour = (int) (remainderL / 1000 / 60 / 60 % 24);
                        int remainderMin = (int) (remainderL / 1000 / 60 % 60);
                        int remainderSec = (int) (remainderL / 1000 % 60);
                        day = (TextView) view.findViewById(R.id.limited_times_day);
                        hours = (TextView) view.findViewById(R.id.limited_times_hour);

                        minutes = (TextView) view.findViewById(R.id.limited_times_minute);
                        seconds = (TextView) view.findViewById(R.id.limited_times_seconds);
                        day.setText(remainderDay + "");
//                    if(remainderHour >= 12){
                        //     hours.setText(remainderHour-12 + "");
//                    }else {
                        hours.setText(remainderHour + "");
//                    }

                        minutes.setText(remainderMin + "");
                        seconds.setText(remainderSec + "");
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }catch (NullPointerException e){




            }



            return view;
        }

        private long stringToLong(String time) {
            try {
                if (time != null) {
                    SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
                    return format.parse(time).getTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0l;
        }

    }

