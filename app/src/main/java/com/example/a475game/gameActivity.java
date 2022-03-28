package com.example.a475game;
import static java.lang.Math.min;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;



public class gameActivity extends AppCompatActivity {
    private Canvas canvas;
    private Bitmap bitmap;
    private ImageView imageView;
    Dot[][] dots;
    private boolean player1Turn = true;
    private final int player1Color = Color.rgb(0, 0, 255);
    private final int player2Color = Color.rgb(255, 0, 0);

    private HashSet<Dot> dotHashSet = new HashSet<Dot>();
    private List<String> arr = new ArrayList<>();
    //private String [] arr = new String[12];
    private int squares =0;
    private int d;
    private int playerScore1;
    private int playerScore2;
    private int grid = 3;
    private int totalSquares = (grid - 1) * (grid - 1);
    private int total =0;
    private int prevTotal =0;





    @SuppressLint("ClickableViewAccessibility")
        @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
            imageView = (ImageView) findViewById(R.id.myimageview);
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
        paint.setARGB(255,0,0,0);
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
        if(dotIndex == null) // if the user didn't click a dot
            return;

        if(firstDotClickedIndex == null){
            firstDotClickedIndex = dotIndex;
            return;
        }

        if(!dotIndex.isAdjacentTo(firstDotClickedIndex)){
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
        if(firstDot == secondDotRow){
            if(firstDotcol < secondDotCol){
                line = Integer.toString(firstDot) + Integer.toString(firstDotcol) + Integer.toString(secondDotCol);
            }
            else{
                line = Integer.toString(firstDot) + Integer.toString(secondDotCol) + Integer.toString(firstDotcol);
            }
        }
        else{
            if(firstDot < secondDotRow){
                line = "-"+Integer.toString(firstDotcol) +Integer.toString(firstDot) + Integer.toString(secondDotRow);
            }
            else{
                line = "-"+Integer.toString(firstDotcol) +Integer.toString(secondDotRow) + Integer.toString(firstDot);
            }
        }

        if(arr.contains(line)){
            firstDotClickedIndex = dotIndex;
            return;
        }

        arr.add(line);
        System.out.println(arr);

        if(player1Turn)
            linePaint.setColor(player1Color);
        else
            linePaint.setColor(player2Color);

        linePaint.setStrokeWidth(clickedDot.radius / 3);
        canvas.drawLine(clickedDot.x, clickedDot.y, firstDotClicked.x, firstDotClicked.y,linePaint);
        firstDotClickedIndex = null;
        player1Turn = lineAndBoxChecker(line,  arr, firstDotcol, firstDot,  secondDotCol,  secondDotRow, player1Turn);
        winChecker();



        player1Turn = !player1Turn;

        imageView.invalidate();
        }
        /// we need to:
        // fill rectangle
        // keep and update score
        // if last edge makes a box update score and dont switch the player


        public void winChecker (){
        //System.out.println("t: " + total);

            if(total == totalSquares){
                String result ;
                if(playerScore2==playerScore1){
                    result=("Draw");
                }
                else if(playerScore2 > playerScore1)
                {
                   result=("player 2 wins");
                }
                else {
                    result=("player 1 wins");
                }

                Intent intent = new Intent(gameActivity.this, ResultActivity.class);
                intent.putExtra("Result",result);
                startActivity(intent);


            // exit to result page

            System.out.println(result);

            }




        }



        public boolean lineAndBoxChecker(String line, List<String> arrayLine, int firstDotCol, int firstDotRow, int secondDotCol, int secondDotRow, boolean player1Turn) {

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


                  // add square to current player

                  squares++;

              }

              if (arrayLine.contains(opposite2) && arrayLine.contains(adjacent3) && arrayLine.contains(adjacent4)) {

                  // add square to current player
                squares++;
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

                  // add square to current player
                squares++;
              }

              if (arrayLine.contains(opposite2) && arrayLine.contains(adjacent3) && arrayLine.contains(adjacent4)) {

                  // add square to current player
                squares++;
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
