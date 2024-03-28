package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    /* Player representation
       0 -> x
       1 -> O
     */

    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    /*
        State Meanings
       0 -> x
       1 -> 0
       2 -> NULL
     */

    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @SuppressLint("SetTextI18n")
    public void PlayerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tappedimage = Integer.parseInt(img.getTag().toString());
        if (!gameActive)
        {
            GameReset(view);
        }
        if (gameState[tappedimage] == 2)
        {
            gameState[tappedimage] = activePlayer;
            img.setTranslationY(-1000f);
            for(int k=0;k<gameState.length;k++)
            {
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("                 0's turn - TAP TO PLAY");
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("                 X's turn - TAP TO PLAY");
                }
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        String winnerStr;
        for (int[] winPosition : winPositions)
        {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2)
            {
                // Somebody has won! - Find out who!
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X HAS WON (TAP TO PLAY AGAIN)";
                } else {
                    winnerStr = "O HAS WON (TAP TO PLAY AGAIN)";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }

            boolean emptySquare = false;
            for (int squareState : gameState) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
            }
            if (!emptySquare && gameActive) {
                // Game is a draw
                gameActive = false;
                winnerStr = "                          No one won";
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }

        }
    }
    @SuppressLint("SetTextI18n")
    public void GameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i : gameState)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("                 X's Turn - Tap to play");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}