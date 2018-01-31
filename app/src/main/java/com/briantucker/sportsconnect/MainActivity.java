package com.briantucker.sportsconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //0 = baseball, 1 = basketball, 2 = soccerball


    int activePlayer = 0;

    //3 means unplayed

    int[] gameState = {3, 3, 3, 3, 3, 3, 3, 3, 3};


    public void dropIn(View view) {

        //possibly allow an option to steal a spot? Currently the balls can replace each other if they are clicked


        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        counter.setTranslationY(-1000f);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.baseball);
            activePlayer = 1;
        }
        else {
            counter.setImageResource(R.drawable.basketball);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
