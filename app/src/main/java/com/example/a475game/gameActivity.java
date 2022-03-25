    package com.example.a475game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

    public class gameActivity extends AppCompatActivity {
//        ImageView imageView;
//        Bitmap bitmap;
//        Canvas canvas;
//        Paint paint;
//        float downx = 0, downy = 0, upx = 0, upy = 0;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageButton rect = (ImageButton) findViewById(R.id.vrect);

        rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rect.setBackgroundColor(Color.RED);

            }
        });

//            imageView = (ImageView) this.findViewById(R.id.imageView3);
//
//            Display currentDisplay = getWindowManager().getDefaultDisplay();
//            float dw = currentDisplay.getWidth();
//            float dh = currentDisplay.getHeight();
//
//            bitmap = Bitmap.createBitmap((int) dw, (int) dh,
//                    Bitmap.Config.ARGB_8888);
//            canvas = new Canvas(bitmap);
//            paint = new Paint();
//            paint.setColor(Color.GREEN);
//            paint.setStrokeWidth(5f);
//            imageView.setImageBitmap(bitmap);
//
//            imageView.setOnTouchListener(this);
        }

//        public boolean onTouch(View v, MotionEvent event) {
//            int action = event.getAction();
//            switch (action) {
//                case MotionEvent.ACTION_DOWN:
//                    downx = event.getX();
//                    downy = event.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    break;
//                case MotionEvent.ACTION_UP:
//                    upx = event.getX();
//                    upy = event.getY();
//                    canvas.drawLine(downx, downy, upx, upy, paint);
//                    imageView.invalidate();
//                    break;
//                case MotionEvent.ACTION_CANCEL:
//                    break;
//                default:
//                    break;
//            }
//            return true;
//        }
    }