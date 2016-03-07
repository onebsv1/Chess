package com.chessbsv;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Board extends Square{

    ArrayList<Piece> allPieces = new ArrayList<>();
    ArrayList<Square> squares = new ArrayList<>();
    public static HashMap<String,Piece> squarePieceAssoc= new HashMap<>();
    public static HashMap<String,String> positionXIDAssoc = new HashMap<>();

    boolean toggle = false;


    public squareColor toggleColor(boolean toggle){
        squareColor col;
        if(toggle){
            col = squareColor.B;
        } else {
            col = squareColor.W;
        }
        return col;
    }

    public void spawnSquares(boolean toggle, int row){
        squareColor col = toggleColor(toggle);

        for (Integer i = ROW_START; i < ROW_END; i++) {
            String currentPos = rowLabels.values()[row] + i.toString();

            Square a = new Square(currentPos,col);
            if(squarePieceAssoc.containsKey(currentPos)){
                a.holds = squarePieceAssoc.get(currentPos);
            }

            squares.add(a);
            toggle = !toggle;
            col = toggleColor(toggle);
        }
    }

    public String Draw() {
        StringBuffer buf = new StringBuffer();
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        buf.append(squares.get(0).ID+" |"+squares.get(0).color+squares.get(0).holds.xID+"| |"+squares.get(1).color+squares.get(1).holds.xID+"| |"+squares.get(2).color+squares.get(2).holds.xID+"| |"+squares.get(3).color+squares.get(3).holds.xID+"| |"+squares.get(4).color+squares.get(4).holds.xID+"| |"+squares.get(5).color+squares.get(5).holds.xID+"| |"+squares.get(6).color+squares.get(6).holds.xID+"| |"+squares.get(7).color+squares.get(7).holds.xID+"| \n");
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        return buf.toString();
    }

    public void populatePieces(Piece p){
        squarePieceAssoc.put(p.currentPos,p);
        positionXIDAssoc.put(p.currentPos,p.xID);
    }

    public void removePieces(Piece p){
        squarePieceAssoc.remove(p.currentPos,p);
        positionXIDAssoc.remove(p.currentPos,p.xID);
    }

    public void drawBoard(){

        for (int i = 0; i < 8; i++) {
            spawnSquares(toggle,i);
            System.out.print(Draw());
            squares.clear();
            toggle = !toggle;
        }
    }

}
