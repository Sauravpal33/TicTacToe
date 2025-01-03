package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private TextView playerOne, playerTwo;
    private ImageView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private String currentPlayer = "X";
    private String[][] board = new String[3][3];
    private boolean gameActive = true;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        playerOne = findViewById(R.id.playerNameOne);
        playerTwo = findViewById(R.id.playerNameTwo);

        playerOne.setText(getIntent().getStringExtra("playerOne"));
        playerTwo.setText(getIntent().getStringExtra("playerTwo"));

        processGame();
    }

    private void processGame() {
        btn1 = findViewById(R.id.imageBtn1);
        btn2 = findViewById(R.id.imageBtn2);
        btn3 = findViewById(R.id.imageBtn3);
        btn4 = findViewById(R.id.imageBtn4);
        btn5 = findViewById(R.id.imageBtn5);
        btn6 = findViewById(R.id.imageBtn6);
        btn7 = findViewById(R.id.imageBtn7);
        btn8 = findViewById(R.id.imageBtn8);
        btn9 = findViewById(R.id.imageBtn9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
        btn1.setOnClickListener(v -> onButtonClick(0, 0, btn1));
        btn2.setOnClickListener(v -> onButtonClick(0, 1, btn2));
        btn3.setOnClickListener(v -> onButtonClick(0, 2, btn3));
        btn4.setOnClickListener(v -> onButtonClick(1, 0, btn4));
        btn5.setOnClickListener(v -> onButtonClick(1, 1, btn5));
        btn6.setOnClickListener(v -> onButtonClick(1, 2, btn6));
        btn7.setOnClickListener(v -> onButtonClick(2, 0, btn7));
        btn8.setOnClickListener(v -> onButtonClick(2, 1, btn8));
        btn9.setOnClickListener(v -> onButtonClick(2, 2, btn9));
    }
    private void onButtonClick(int row, int col, ImageView btn) {
        if (gameActive && board[row][col].equals("")) {
            board[row][col] = currentPlayer;

            if (currentPlayer.equals("X")) {
                btn.setImageResource(R.drawable.o);
                currentPlayer = "O";
            } else {
                btn.setImageResource(R.drawable.cancel);
                currentPlayer = "X";
            }

            if (checkWinner()) {
                gameActive = false;
                showDialog();
            } else if (checkDraw()) {
                gameActive = false;
                showDialog();

            }
        }
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogue_box);
        Button restartBtn = dialog.findViewById(R.id.restartBtn);
        TextView statusText = dialog.findViewById(R.id.statusText);
        if (checkWinner()) {
            String winner = currentPlayer;
            statusText.setText("Winner: " + winner);
        }if (checkDraw()){
            statusText.setText("It's a Draw!");
        }
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void restartGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }

        btn1.setImageResource(0);
        btn2.setImageResource(0);
        btn3.setImageResource(0);
        btn4.setImageResource(0);
        btn5.setImageResource(0);
        btn6.setImageResource(0);
        btn7.setImageResource(0);
        btn8.setImageResource(0);
        btn9.setImageResource(0);
        currentPlayer = "X";
        gameActive = true;
    }


    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            // Check columns
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
