package com.example.darthvader.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean counterisActive=false;
    TextView textView;
    Button controllerButton;
    CountDownTimer countDownTimer;
    SeekBar seekBar;
    public void resetTimer()
    {
        textView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        seekBar.setEnabled(true);
        counterisActive=false;
    }
    public  void updateTimer(int secondsLeft)
    {
        int minutes=(int)secondsLeft/60;
        int seconds=secondsLeft-(minutes*60);
        String second="";
        if(seconds<10)
        {
            second="0"+Integer.toString(seconds);
        }
        else
            second=Integer.toString(seconds);
        textView.setText(Integer.toString(minutes)+":"+second);
    }

    public void controlTimer(View view)
    {
        if(counterisActive==false) {
            counterisActive = true;
            seekBar.setEnabled(false);
            controllerButton.setText("Stop");
            countDownTimer=new CountDownTimer(((seekBar.getProgress()) * 1000) + 200, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    textView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }

            }.start();
        }
        else if(counterisActive==true)
        {
            resetTimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar)findViewById(R.id.sb_timer);
        textView=(TextView)findViewById(R.id.textView);
        controllerButton=(Button)findViewById(R.id.btn_controller);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
