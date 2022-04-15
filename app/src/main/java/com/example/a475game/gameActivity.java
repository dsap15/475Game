package com.example.a475game;
import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;



public class gameActivity extends AppCompatActivity {
    private Canvas canvas;
    private Bitmap bitmap;
    private ImageView imageView;
    private TextView player1Text;
    private TextView player2Text;
    private TextView p1score;
    private TextView p2score;
    private TextView player1TurnView;
    private TextView player2TurnView;
    Dot[][] dots;
    private boolean player1Turn = true;
    private final int player1Color = Color.argb(127, 0, 0, 255);
    private final int player2Color = Color.argb(127, 255, 0,0 );
    private List<String> arr = new ArrayList<>();
    private int squares =0;
    private int d;
    private int playerScore1;
    private int playerScore2;
    public static int grid;
    private int totalSquares = (grid - 1) * (grid - 1);
    private int total =0;
    private int prevTotal =0;
    private int max,button_sound_int,Square_sound,Result_sound;
    private SoundPool sound_effects;





    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        imageView = (ImageView) findViewById(R.id.MyImageview);
        player1Text = findViewById(R.id.P1text);
        player2Text = findViewById(R.id.P2text);
        player1TurnView = findViewById(R.id.P1Turn);
        player1TurnView.setVisibility(View.VISIBLE);
        player2TurnView = findViewById(R.id.P2Turn);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
                canvas = new Canvas(bitmap);
                imageView.setImageBitmap(bitmap);
                dots = generateDotGrid(grid, imageView.getWidth(), imageView.getHeight());
                drawDots(dots, canvas);
            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    onClick(event.getX(), event.getY());

                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sound_effects = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            sound_effects = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        // line drawing sound
        button_sound_int = sound_effects.load(this, R.raw.ring, 10);
        // box finish sound
        Square_sound = sound_effects.load(this,R.raw.squaresound,5);
        // result sound
        Result_sound = sound_effects.load(this,R.raw.resultsound,0);

    }


    private Dot[][] generateDotGrid(int numDots, int screenWidth, int screenHeight) {
        float borderProportion = 0.2f;

        float boxSpaceWidth = (1 - 2 * borderProportion) * screenWidth;
        float boxSpaceHeight = (1 - 2 * borderProportion) * screenHeight;

        float boxSpaceSideLength = min(boxSpaceWidth, boxSpaceHeight);

        float cellSideLength = boxSpaceSideLength / (numDots - 1);

        float centerX = screenWidth / 2.0f;
        float centerY = screenHeight / 2.0f;

        float startX = centerX - boxSpaceSideLength / 2.0f;
        float startY = centerY - boxSpaceSideLength / 2.0f;

        Dot[][] dots = new Dot[numDots][numDots];
        for (int r = 0; r < numDots; r++) {
            for (int c = 0; c < numDots; c++) {
                dots[r][c] = new Dot((startX + c * cellSideLength), (startY + r * cellSideLength), 25f);
            }
        }
        return dots;
    }
    private void drawDots(Dot[][] dots, Canvas canvas){
        Paint paint = new Paint();
        if(!SettingsActivity.clicked )
            paint.setARGB(255,0,0,0);
        if(!SettingsActivity.sailesh)
            paint.setARGB(255,0,0,0);
        else{
            paint.setARGB(255,255,255,255);
        }


        for(int r = 0; r < dots.length; r++){
            for(int c =0; c < dots[r].length; c++){
                Dot dotsForDrawing = dots[r][c];
                canvas.drawCircle(dotsForDrawing.x, dotsForDrawing.y,dotsForDrawing.radius, paint);
            }
        }
    }

    private Index2D getClickedDotIndex(float cx, float cy, Dot[][] dots){
        Index2D index2D = null;
        for (int i = 0; i < dots.length; i++){
            for( int j = 0; j < dots[i].length; j++){
                if (dots[i][j].contains(cx, cy)){
                    index2D = new Index2D(i,j);
                }
            }
        }
        return index2D;
    }
    //Player(s) Score
    private Index2D firstDotClickedIndex = null;
    private void onClick(float clickX, float clickY) {

        Index2D dotIndex = getClickedDotIndex(clickX, clickY, dots);
        if (dotIndex == null) // if the user didn't click a dot
            return;

        if (firstDotClickedIndex == null) {
            firstDotClickedIndex = dotIndex;
            return;
        }

        if (!dotIndex.isAdjacentTo(firstDotClickedIndex)) {
            firstDotClickedIndex = dotIndex;
            return;
        }

        Dot firstDotClicked = dots[firstDotClickedIndex.row][firstDotClickedIndex.col];
        Dot clickedDot = dots[dotIndex.row][dotIndex.col];
        Paint linePaint = new Paint();

        //arr = new String[12];
        int firstDot = firstDotClickedIndex.row;
        int firstDotcol = firstDotClickedIndex.col;
        int secondDotRow = dotIndex.row;
        int secondDotCol = dotIndex.col;
        String line;

        //String line = Integer.toString(firstDot) + Integer.toString(firstDotcol)+ Integer.toString(clickedDotS)+ Integer.toString(clickedDotcol);
        if (firstDot == secondDotRow) {
            if (firstDotcol < secondDotCol) {
                line = Integer.toString(firstDot) + Integer.toString(firstDotcol) + Integer.toString(secondDotCol);
            } else {
                line = Integer.toString(firstDot) + Integer.toString(secondDotCol) + Integer.toString(firstDotcol);
            }
        } else {
            if (firstDot < secondDotRow) {
                line = "-" + Integer.toString(firstDotcol) + Integer.toString(firstDot) + Integer.toString(secondDotRow);
            } else {
                line = "-" + Integer.toString(firstDotcol) + Integer.toString(secondDotRow) + Integer.toString(firstDot);
            }
        }

        if (arr.contains(line)) {
            firstDotClickedIndex = dotIndex;
            return;
        }

        arr.add(line);
        System.out.println(arr);

        if (player1Turn) {
            linePaint.setColor(player1Color);
            player1TurnView.setVisibility(View.INVISIBLE);
            player2TurnView.setVisibility(View.VISIBLE);

        } else {
            linePaint.setColor(player2Color);
            player1TurnView.setVisibility(View.VISIBLE);
            player2TurnView.setVisibility(View.INVISIBLE);
        }

        linePaint.setStrokeWidth(clickedDot.radius / 3);

        //sound
//        if (SettingsActivity.settingBoolean = true) {
//
//
//
//
//
//
//        }
//        else if (SettingsActivity.settingBoolean=false){
//            return;
//        }

        //playing sound
        sound_effects.play(button_sound_int,1,1,0,0,1);

        canvas.drawLine(clickedDot.x, clickedDot.y, firstDotClicked.x, firstDotClicked.y,linePaint);
        firstDotClickedIndex = null;
        player1Turn = lineAndBoxChecker(line,  arr, firstDotcol, firstDot,  secondDotCol,  secondDotRow, player1Turn, linePaint);
        winChecker();


        player1Turn = !player1Turn;
        p1score = findViewById(R.id.P1Score);
        p1score.setText(Integer.toString(playerScore1));
        p2score = findViewById(R.id.P2Score);
        p2score.setText(Integer.toString(playerScore2));

        imageView.invalidate();
    }
    /// we need to:
    // fill rectangle
    // keep and update score
    // if last edge makes a box update score and dont switch the player


    public void winChecker (){
        //System.out.println("t: " + total);


        if(total == totalSquares) {
            sound_effects.play(Result_sound, 1, 1, 0, 0, 1);
            String result;
            if (playerScore2 == playerScore1) {
                result = getString(R.string.Draw);


            } else if (playerScore2 > playerScore1) {
                result = getString(R.string.Player2Wins);
            } else {
                result = getString(R.string.Player1Wins);
            }

            Intent intent = new Intent(gameActivity.this, ResultActivity.class);
            intent.putExtra("Result", result);
            startActivity(intent);

            // exit to result page
            System.out.println(result);


        }
    }



    public boolean lineAndBoxChecker(String line, List<String> arrayLine, int firstDotCol, int firstDotRow, int secondDotCol, int secondDotRow, boolean player1Turn, Paint linePaint) {



        squares = 0;
        prevTotal = total;
        if(!line.substring(0,1).equalsIgnoreCase("-")) {
            String opposite1 = Integer.toString(firstDotRow - 1) + (line.substring(line.length() - 2));
            String adjacent1 = "-" + (line.substring(line.length() - 2, line.length() - 1)) + Integer.toString(firstDotRow - 1) + Integer.toString(firstDotRow);
            String adjacent2 = "-" + (line.substring(line.length() - 1)) + Integer.toString(firstDotRow - 1) + Integer.toString(firstDotRow);

            String opposite2 = Integer.toString(firstDotRow + 1) + (line.substring(line.length() - 2));
            String adjacent3 = "-" + (line.substring(line.length() - 2, line.length() - 1)) + Integer.toString(firstDotRow) + Integer.toString(firstDotRow + 1);
            String adjacent4 = "-" + (line.substring(line.length() - 1)) + Integer.toString(firstDotRow) + Integer.toString(firstDotRow + 1);


            if (arrayLine.contains(opposite1) && arrayLine.contains(adjacent1) && arrayLine.contains(adjacent2)) {
                //playing sound
                sound_effects.play(Square_sound,10,10,0,0,1);


                int parsingOpposite1 = Integer.parseInt(opposite1); // checking top box
                int row = parsingOpposite1/100;
                int col1 = (parsingOpposite1 / 10) % 10;

                canvas.drawRect(dots[row][col1].x, dots[row][col1].y, dots[row+1][col1+1].x, dots[row+1][col1+1].y, linePaint);
                if(linePaint.getColor() == player1Color){
                    player1TurnView.setVisibility(View.VISIBLE);
                    player2TurnView.setVisibility(View.INVISIBLE);
                }
                else{
                    player2TurnView.setVisibility(View.VISIBLE);
                    player1TurnView.setVisibility(View.INVISIBLE);
                }
                // add square to current player
                squares++;

            }

            if (arrayLine.contains(opposite2) && arrayLine.contains(adjacent3) && arrayLine.contains(adjacent4)) {
                //playing sound
                sound_effects.play(Square_sound,10,10,0,0,1);

                int parsingLine = Integer.parseInt(line); //checking bottom box
                int lineRow = parsingLine/100;
                int lineCol1 = (parsingLine / 10) % 10;
                canvas.drawRect(dots[lineRow][lineCol1].x, dots[lineRow][lineCol1].y, dots[lineRow+1][lineCol1+1].x, dots[lineRow+1][lineCol1+1].y, linePaint);
                // add square to current player
                squares++;

                if(linePaint.getColor() == player1Color){
                    player1TurnView.setVisibility(View.VISIBLE);
                    player2TurnView.setVisibility(View.INVISIBLE);
                }
                else{
                    player2TurnView.setVisibility(View.VISIBLE);
                    player1TurnView.setVisibility(View.INVISIBLE);
                }
            }
        }
        else{
            String opposite1 = "-"+Integer.toString(firstDotCol- 1) + (line.substring(line.length() - 2));
            String adjacent1 = (line.substring(line.length() - 2, line.length() - 1)) + Integer.toString(firstDotCol - 1) + Integer.toString(firstDotCol);
            String adjacent2 = (line.substring(line.length() - 1)) + Integer.toString(firstDotCol- 1) + Integer.toString(firstDotCol);

            String opposite2 = "-" + Integer.toString(firstDotCol + 1) + (line.substring(line.length() - 2));
            String adjacent3 = (line.substring(line.length() - 2, line.length() - 1)) + Integer.toString(firstDotCol) + Integer.toString(firstDotCol + 1);
            String adjacent4 = (line.substring(line.length() - 1)) + Integer.toString(firstDotCol) + Integer.toString(firstDotCol + 1);

            if (arrayLine.contains(opposite1) && arrayLine.contains(adjacent1) && arrayLine.contains(adjacent2)) {
//playing sound
                sound_effects.play(Square_sound,10,10,0,0,1);

                // add square to current player
                // left box
                int cornerCol = firstDotCol -1;
                int minRow = min(firstDotRow, secondDotRow);
                canvas.drawRect(dots[minRow][cornerCol].x, dots[minRow][cornerCol].y, dots[minRow+1][cornerCol+1].x, dots[minRow+1][cornerCol+1].y, linePaint);
                 squares++;

                if(linePaint.getColor() == player1Color){
                    player1TurnView.setVisibility(View.VISIBLE);
                    player2TurnView.setVisibility(View.INVISIBLE);
                }
                else{
                    player2TurnView.setVisibility(View.VISIBLE);
                    player1TurnView.setVisibility(View.INVISIBLE);
                }
            }

            if (arrayLine.contains(opposite2) && arrayLine.contains(adjacent3) && arrayLine.contains(adjacent4)) {
//playing sound
                sound_effects.play(Square_sound,10,10,0,0,1);

                // add square to current player
                // left box
                int cornerCol = firstDotCol;
                int minRow = min(firstDotRow, secondDotRow);
                canvas.drawRect(dots[minRow][cornerCol].x, dots[minRow][cornerCol].y, dots[minRow+1][cornerCol+1].x, dots[minRow+1][cornerCol+1].y, linePaint);
                 squares++;

                if(linePaint.getColor() == player1Color){
                    player1TurnView.setVisibility(View.VISIBLE);
                    player2TurnView.setVisibility(View.INVISIBLE);
                }
                else{
                    player2TurnView.setVisibility(View.VISIBLE);
                    player1TurnView.setVisibility(View.INVISIBLE);
                }

            }

        }

        if(player1Turn){
            playerScore1 += squares;
        }

        else{
            playerScore2 += squares;
        }
        total = playerScore1+playerScore2;

        System.out.println("p1 " + playerScore1);
        System.out.println("p2 " + playerScore2);
        if(total != prevTotal)
        {
            prevTotal = total;
            return !player1Turn;
        }
        else{
            prevTotal = total;
            return player1Turn;
        }
    }
}
