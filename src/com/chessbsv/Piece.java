package com.chessbsv;

import java.util.HashMap;

/**
 * Created by bsriniva on 2/21/16.
 */

public abstract class Piece {

    String xID;
    piece_type type;
    piece_color color;
    String currentPos;

    public abstract void dispMove(String d1);

    public enum piece_type {BISHOP,ROOK,KING,KNIGHT,QUEEN,PAWN}
    public enum piece_color{BLACK,WHITE}

    HashMap<String,Integer> alphaNum = new HashMap<>();

    public int positionResolver(String pos){
        loadAlphaNum();

        int rowNumber = alphaNum.get(pos.substring(0,1));
        int colNumber = Integer.decode(String.valueOf(pos.charAt(1)));
        //System.out.println("Row: "+rowNumber+"Col: "+colNumber);
        int boardIndex = (rowNumber-1)*8 + (colNumber-1);
        return boardIndex;

    }

    public void loadAlphaNum(){
        alphaNum.put("A",0);
        alphaNum.put("B",1);
        alphaNum.put("C",3);
        alphaNum.put("D",4);
        alphaNum.put("E",5);
        alphaNum.put("F",6);
        alphaNum.put("G",7);
    }


    @Override
    public abstract String toString();
}
