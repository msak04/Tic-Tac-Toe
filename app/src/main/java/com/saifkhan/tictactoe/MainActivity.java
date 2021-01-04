package com.saifkhan.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button [][] button = new Button[3][3];
    private boolean Player1Turn = true;
    private  int RoundCount, P1Point,P2Point;
    private TextView TVPlayer1, TVPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVPlayer1 = findViewById(R.id.main_textview_p1);
        TVPlayer2 = findViewById(R.id.main_textview_p2);
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++)
            {
                String buttonID = "main_button_"+i+j;
                int ResID = getResources().getIdentifier(buttonID,"id",getPackageName());
                button[i][j] = findViewById(ResID);
                button[i][j].setOnClickListener(this);
            }
        }

        Button ResetButton = findViewById(R.id.main_button_reset);
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetGame();

            }
        });
    }



    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if(Player1Turn){
            ((Button) v).setText("O");
        }
        else {
            ((Button) v).setText("X");
        }
        RoundCount ++;
        if(CheckforWin())
        {
            if(Player1Turn)
            {
                Player1Wins();
            }
            else{
                Player2Wins();
            }
        }
        else if(RoundCount == 9){
            Draw();
        }
        else {
            Player1Turn = !Player1Turn;
        }


    }


    private boolean CheckforWin(){

        String[][] field = new String[3][3];
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++)
            {
                field[i][j]= button[i][j].getText().toString();
            }
        }
        for(int i=0; i<3;i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])&& !field[i][0].equals("")){
                return true;
            }

        }
        for(int i=0; i<3;i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])&& !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])&& !field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0])&& !field[2][0].equals("")){
            return true;
        }
        return false;

    }

    private void Player1Wins() {
        P1Point++;
        Toast.makeText(this,"Player 1 wins!",Toast.LENGTH_SHORT).show();
        UpdatePointText();
        resetBoard();
    }


    private void Player2Wins() {
        P2Point++;
        Toast.makeText(this,"Player 2 wins!",Toast.LENGTH_SHORT).show();
        UpdatePointText();
        resetBoard();
    }

    private void Draw() {
        Toast.makeText(this,"DRAW!!!!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }



    private void UpdatePointText() {
        TVPlayer1.setText("Player 1: "+P1Point);
        TVPlayer2.setText("Player 1: "+P2Point);
    }



    private void resetBoard() {
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++)
            {
                button[i][j].setText("");

            }
        }
        RoundCount = 0;
        Player1Turn = true;
    }

    private void ResetGame() {
        P1Point = 0;
        P2Point = 0;
        UpdatePointText();
        resetBoard();
        
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("RoundCount",RoundCount);
        outState.putInt("P1Point",P1Point);
        outState.putInt("P2Point",P2Point);
        outState.putBoolean("PlayerTurn",Player1Turn);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        RoundCount = savedInstanceState.getInt("RoundCount");
        P1Point = savedInstanceState.getInt("P1Point");
        P2Point = savedInstanceState.getInt("P2Point");
        Player1Turn = savedInstanceState.getBoolean("PlayerTurn");
    }
}