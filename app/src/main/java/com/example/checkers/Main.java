package com.example.checkers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    private Button[][] b1 = new Button[9][9];
    private Button selectedButton = null;
    private int selectedI, selectedJ;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        start=findViewById(R.id.button);
        start.setOnClickListener(view -> {main_stuff();});
    }

    public void main_stuff()
    {
        setContentView(R.layout.activity_main);
        GridLayout drig = findViewById(R.id.drig);
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.width = 0;
                param.height = 0;
                param.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                param.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);

                b1[i][j] = new Button(this);
                b1[i][j].setLayoutParams(param);

                if ((i + j) % 2 == 0) {
                    b1[i][j].setBackgroundColor(Color.BLACK);

                    if (i < 4) {
                        b1[i][j].setText("O");
                        b1[i][j].setTextColor(Color.BLUE);
                        b1[i][j].setTag("BLUE");
                    } else if (i > 5) {
                        b1[i][j].setText("O");
                        b1[i][j].setTextColor(Color.RED);
                        b1[i][j].setTag("RED");
                    }

                } else {
                    b1[i][j].setBackgroundColor(Color.WHITE);
                }

                drig.addView(b1[i][j]);

                int finalI = i;
                int finalJ = j;

                b1[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (selectedButton == null) {
                            if (checkStriker(b1[finalI][finalJ], finalI, finalJ, 0)) {
                                selectedButton = b1[finalI][finalJ];
                                selectedI = finalI;
                                selectedJ = finalJ;
                            }
                        } else {
                            if (selectedButton == b1[finalI][finalJ]) {
                                selectedButton = null;
                            } else {
                                boolean isRed = checkColor(selectedButton);
                                if (isRed) {
                                    redMovement(selectedI, selectedJ, finalI, finalJ);
                                } else {
                                    blueMovement(selectedI, selectedJ, finalI, finalJ);
                                }
                                selectedButton = null;
                            }
                        }
                    }
                });
            }
        }
    }
    public boolean checkStriker(Button b, int i, int j, int z) {
        String striker = (z == 0) ? b.getText().toString() : b1[i][j].getText().toString();
        return striker.equals("O") || striker.equals("K");
    }

    public boolean checkColor(Button b) {
        String colorTag = b.getTag().toString();
        return colorTag.equals("RED");
    }

    public void redMovement(int fromI, int fromJ, int toI, int toJ) {
        if (checkCaptureMove(fromI, fromJ, toI, toJ, "BLUE") || checkSimpleMove(fromI, fromJ, toI, toJ, "RED")) {
            if (toI == 1) {
                convertToKing(toI, toJ, Color.RED);
            }
        }
    }

    public void blueMovement(int fromI, int fromJ, int toI, int toJ) {
        if (checkCaptureMove(fromI, fromJ, toI, toJ, "RED") || checkSimpleMove(fromI, fromJ, toI, toJ, "BLUE")) {
            if (toI == 8) {
                convertToKing(toI, toJ, Color.BLUE);
            }
        }
    }

    private boolean checkCaptureMove(int fromI, int fromJ, int toI, int toJ, String opponentColor) {
        int midI = (fromI + toI) / 2;
        int midJ = (fromJ + toJ) / 2;
        if (Math.abs(fromI - toI) == 2 && Math.abs(fromJ - toJ) == 2) {
            Button midButton = b1[midI][midJ];
            if (checkStriker(midButton, midI, midJ, 0) && midButton.getTag().toString().equals(opponentColor)) {
                movePiece(fromI, fromJ, toI, toJ, checkColor(selectedButton) ? Color.RED : Color.BLUE);
                midButton.setText("");
                midButton.setTag("");
                return true;
            }
        }
        return false;
    }

    private boolean checkSimpleMove(int fromI, int fromJ, int toI, int toJ, String color) {
        if (checkPositionAvailable(toI, toJ)) {
            if (color.equals("RED") && toI == fromI - 1 && (toJ == fromJ - 1 || toJ == fromJ + 1)) {
                movePiece(fromI, fromJ, toI, toJ, Color.RED);
                return true;
            } else if (color.equals("BLUE") && toI == fromI + 1 && (toJ == fromJ - 1 || toJ == fromJ + 1)) {
                movePiece(fromI, fromJ, toI, toJ, Color.BLUE);
                return true;
            }
        }
        return false;
    }

    private void movePiece(int fromI, int fromJ, int toI, int toJ, int color) {
        String pieceText = b1[fromI][fromJ].getText().toString();
        b1[fromI][fromJ].setText("");
        b1[fromI][fromJ].setTag("");
        b1[toI][toJ].setText(pieceText);
        b1[toI][toJ].setTextColor(color);
        b1[toI][toJ].setTag((color == Color.RED) ? "RED" : "BLUE");
    }

    public boolean checkPositionAvailable(int i, int j) {
        return !checkStriker(b1[i][j], i, j, 1);
    }

    private void convertToKing(int i, int j, int color) {
        b1[i][j].setText("K");
        b1[i][j].setTextColor(color);
        b1[i][j].setTag((color == Color.RED) ? "RED" : "BLUE");
    }
    private void closeApp(View view)
    {
        finish();
    }
}
