package com.example.technology.lovedemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Love ll_love;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_love = (Love) findViewById(R.id.lovelayout);
//        ImageView iamge = findViewById(R.id.iamge);
        //增加监听事件

        ll_love.setOnTouchListener(new View.OnTouchListener() {
            long[] mHints = new long[2];//初始全部为0
            @Override//可以捕获触摸屏幕发生的Event事件
            public boolean onTouch(View v, MotionEvent e) {
                //使用GestureDetector转发MotionEvent对象给OnGestureListener

                if(e.getAction()== MotionEvent.ACTION_UP) {
                    System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
                    //获得当前系统已经启动的时间
                    mHints[mHints.length - 1] = SystemClock.uptimeMillis();
                    if (SystemClock.uptimeMillis() - mHints[0] <= 500) {
//                        Toast.makeText(MainActivity.this, "被电击了多次", Toast.LENGTH_SHORT).show();
                        Log.e("-----------", v.getX() + "========" + v.getY());
                        ll_love.addLoveView(e.getRawX(),e.getRawY());
                    } else{
//                        Toast.makeText(MainActivity.this, "被电击了", Toast.LENGTH_SHORT).show();

                        ll_love.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if ( mHints[1]- mHints[0] > 500) {
                                    Toast.makeText(MainActivity.this, "被电击了", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },300);
                    }
                }
                return true;
            }
        });

        ll_love.setDismissAnimInterface(new Love.DismissAnimInterface() {
            @Override
            public void showBack() {
                Log.e("---------","动画开始了3");
                Toast.makeText(MainActivity.this, "动画开始了3", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void dismissBack() {
                Toast.makeText(MainActivity.this, "动画消失了", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

