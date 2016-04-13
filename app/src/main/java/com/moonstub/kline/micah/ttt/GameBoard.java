package com.moonstub.kline.micah.ttt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Micah on 4/5/2016.
 */
public class GameBoard extends AppCompatActivity {

    private TextView mTitleText;
    private Grid[] gameGrid = new Grid[9];
    private ImageButton[] gameGridButton = new ImageButton[9];
    boolean PlayOnesTurn = true;
    boolean gameOver = false;
    String gameOverMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_activity);

        mTitleText = (TextView)findViewById(R.id.ttt_header);
        //mTitleText.setText("I'm the GameBoard Activity...Really I am!");

        gameGridButton[0] = (ImageButton)findViewById(R.id.gridButton0);
        gameGridButton[1] = (ImageButton)findViewById(R.id.gridButton1);
        gameGridButton[2] = (ImageButton)findViewById(R.id.gridButton2);
        gameGridButton[3] = (ImageButton)findViewById(R.id.gridButton3);
        gameGridButton[4] = (ImageButton)findViewById(R.id.gridButton4);
        gameGridButton[5] = (ImageButton)findViewById(R.id.gridButton5);
        gameGridButton[6] = (ImageButton)findViewById(R.id.gridButton6);
        gameGridButton[7] = (ImageButton)findViewById(R.id.gridButton7);
        gameGridButton[8] = (ImageButton)findViewById(R.id.gridButton8);


        initGame();

        for(int index = 0; index < gameGridButton.length; index++){
            gameGridButton[index].setTag(index);
            gameGridButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!gameOver){
                    //Sets the value of image to the Resource id of requested image
                    int image = newImage(v.getTag().toString());
                    setImageResource(image, valueOfIndex(v.getTag().toString()));

                    gameOver = winLoseDraw();
                    if(gameOver){
                        Toast.makeText(GameBoard.this, gameOverMessage, Toast.LENGTH_LONG).show();
                        //initGame();
                    }
                } else {
                        initGame();
                    }
                }
            });
        }
    }

    private boolean winLoseDraw() {
        if(checkWin(0,1,2) || checkWin(3,4,5) ||
                checkWin(6,7,8) || checkWin(0,3,6) ||
                checkWin(1,4,7) || checkWin(2,5,8) ||
                checkWin(0,4,8) || checkWin(2,4,6)){

            gameOverMessage = (!PlayOnesTurn) ? "X has Taken the Game" : "O has taken the Game";
            return true;
        }
        if(!checkForMoves()){
            gameOverMessage = "The Game Ends in a draw";
            return true;
        }
        return false;
    }

    private boolean checkWin(int x1, int x2, int x3) {
        if(gameGrid[x1].getType() == SQUARE_TYPE.B){
            return false;
        }

        if(gameGrid[x1].getType() == gameGrid[x2].getType() &&
                gameGrid[x1].getType() == gameGrid[x3].getType()){
            return true;
        }

        return false;
    }

    private boolean checkForMoves() {
        for(int i = 0; i < gameGrid.length;i++){
            if(gameGrid[i].getType() == SQUARE_TYPE.B){
                return true;
            }
        }
        return false;
    }

    private void setImageResource(int image, int i) {
        gameGridButton[i].setImageResource(image);
    }

    private int newImage(String index) {

        int i = Integer.parseInt(index);
        if(gameGrid[i].getType() ==
                SQUARE_TYPE.B){
            if(PlayOnesTurn){
                gameGrid[i].setType(SQUARE_TYPE.X);
                PlayOnesTurn = !PlayOnesTurn;
                return R.drawable.x;
            } else {
                gameGrid[i].setType(SQUARE_TYPE.O);
                PlayOnesTurn = !PlayOnesTurn;
                return R.drawable.o;
            }

    }
        Toast.makeText(this,"Square Already Taken", Toast.LENGTH_SHORT).show();
        return (gameGrid[i].getType() == SQUARE_TYPE.X) ? R.drawable.x : R.drawable.o;
    }

    private int valueOfIndex(String index) {
        Log.d("Does This Work", "Index Value passed is " + index);
        return Integer.parseInt(index);
    }

    private void initGame() {
        //Initialize the game
        gameOver = false;
        for(int index = 0; index < gameGrid.length; index++){
            gameGrid[index] = new Grid(index);
            gameGridButton[index].setImageResource(R.drawable.ic_launcher);
        }
    }
}
