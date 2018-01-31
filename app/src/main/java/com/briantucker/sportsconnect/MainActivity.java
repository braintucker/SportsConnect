package com.briantucker.sportsconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0 = baseball, 1 = basketball


    int activePlayer = 0;

    boolean isActive = true;

    //3 means unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions ={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {

        //possibly allow an option to steal a spot? Currently the balls can replace each other if they are clicked

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && isActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.baseball);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.basketball);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions){

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){

                    //Someone has won

                    isActive = false;

                    String winner = "Basketball";
                    if (gameState[winningPosition[0]] == 0){
                        winner = "Baseball";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + "has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLay);
                    layout.setVisibility(View.VISIBLE);
                }
                else{

                    boolean gameOver = true;

                    for(int counterState : gameState){
                        if (counterState == 2 ) gameOver = false;
                    }

                    if (gameOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw!");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLay);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {

        isActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLay);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLay);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
