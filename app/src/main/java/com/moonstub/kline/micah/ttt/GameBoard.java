package com.moonstub.kline.micah.ttt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Micah on 4/5/2016.
 */
public class GameBoard extends AppCompatActivity {

    private TextView mTitleText;
    private Grid[] gameGrid = new Grid[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_activity);

        mTitleText = (TextView)findViewById(R.id.ttt_header);
        //mTitleText.setText("I'm the GameBoard Activity...Really I am!");

        initGame();
    }

    private void initGame() {
        //Initialize the game
        for(int index = 0; index < gameGrid.length; index++){
            gameGrid[index] = new Grid(index);
        }
    }
}
