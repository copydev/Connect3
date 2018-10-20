package com.example.idtyp.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {

    //0 = yellow 1 = red

    int activePlayer = 0;

    boolean gameIsActive = true;

    //2 means empty spot
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        // System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive ) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }

                    gameIsActive = false;

                    String playerWon = gameState[winningPosition[0]] + "";

                   // Toast.makeText(getApplicationContext(), playerWon, Toast.LENGTH_SHORT).show();

                    TextView winnerMessage = findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }

            }

            boolean gameIsOver = true;

            for(int i =0;i<gameState.length;i++){
                if(gameState[i]==2){
                    gameIsOver = false;
                }
            }

            if(gameIsOver){
                TextView winnerMessage = findViewById(R.id.winnerMessage);

                winnerMessage.setText("It is a draw!");

                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }


        }
    }

    public void playAgain(View view){
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        gameIsActive = true;


        for(int i = 0;i<9;i++){
            gameState[i] = 2;
        }

//        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i =0;i<9;i++){
//            ImageView img = gridLayout.findViewWithTag(Integer.toString(i));
//            img.setImageResource(0);
//
//            System.out.println(gridLayout.getColumnCount());
            ImageView img = (ImageView) findViewById(R.id.gridLayout).findViewWithTag(Integer.toString(i));
            img.setImageResource(0);
        }

        System.out.println("Exit");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
