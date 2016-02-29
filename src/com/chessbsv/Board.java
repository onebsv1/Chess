package com.chessbsv;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Board extends Square{

    ArrayList<Piece> allPieces = new ArrayList<>();
    ArrayList<Square> squares = new ArrayList<>();
    HashMap<String,Piece> spAssoc= new HashMap<>();

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
            String xID = rowLabels.values()[row] + i.toString();
            //System.out.print(xID);
            Square a;
            if(spAssoc.containsKey(xID)){
                a = new Square(xID,col);
                a.holds = spAssoc.get(xID);
            } else {
                a = new Square(xID, col);
            }

            squares.add(a);
            toggle = !toggle;
            col = toggleColor(toggle);
        }
    }

    public String Draw() {
        StringBuffer buf = new StringBuffer();
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        buf.append(squares.get(0).ID+" |"+squares.get(0).color+squares.get(0).holds.type+"| |"+squares.get(1).color+squares.get(1).holds.type+"| |"+squares.get(2).color+squares.get(2).holds.type+"| |"+squares.get(3).color+squares.get(3).holds.type+"| |"+squares.get(4).color+squares.get(4).holds.type+"| |"+squares.get(5).color+squares.get(5).holds.type+"| |"+squares.get(6).color+squares.get(6).holds.type+"| |"+squares.get(7).color+squares.get(7).holds.type+"| \n");
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        return buf.toString();
    }

    public void populatePieces(String s,Piece p){
        spAssoc.put(s,p);
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
