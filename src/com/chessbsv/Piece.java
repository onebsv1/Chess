package com.chessbsv;

import java.util.HashMap;

/**
 * Created by bsriniva on 2/21/16.
 */

public abstract class Piece {

    String xID;
    public enum piece_color{BLACK,WHITE}
    public enum piece_type{BISHOP,KING,KNIGHT,PAWN,ROOK,QUEEN}
    piece_color color;
    piece_type type;
    String currentPos;

    public abstract void dispMove(String d1);

    HashMap<String,Integer> alphaNum = new HashMap<>();
    HashMap<Integer,String> numAlpha = new HashMap<>();

    public int positionResolver(String pos){

        loadAlphaNum();

        Integer rowNumber = alphaNum.get(pos.substring(0,1));
        Integer colNumber = Integer.decode(String.valueOf(pos.charAt(1)));
        //System.out.println("Row: "+rowNumber+"Col: "+colNumber);
        Integer boardIndex = (rowNumber-1)*8 + (colNumber-1);
        return boardIndex;

    }

    public String xIDResolver(Integer pos){

        loadNumAlpha();
        Integer colNumber = (pos%8) + 1;
        Integer rowNumber = (pos/8) + 1;

        StringBuffer ID = new StringBuffer();
        ID.append(numAlpha.get(rowNumber.intValue()));
        ID.append(colNumber.toString());

        //System.out.println("Row: "+rowNumber+"Col: "+colNumber+"Pos: "+pos);
        if(!validPosition(pos)){return null;}

        return ID.toString();
    }

    public boolean validPosition(String currentPos){
        Integer pos = this.positionResolver(currentPos);
        if(pos<0 || pos>=64){
            return false;
        } else {
            return true;
        }

    }

    public boolean validPosition(int currentPos){
        Integer pos = currentPos;
        if(pos<0 || pos>=64){
            return false;
        } else {
            return true;
        }

    }

    public void loadAlphaNum(){
        alphaNum.put("A",1);
        alphaNum.put("B",2);
        alphaNum.put("C",3);
        alphaNum.put("D",4);
        alphaNum.put("E",5);
        alphaNum.put("F",6);
        alphaNum.put("G",7);
        alphaNum.put("H",8);
    }

    public void loadNumAlpha(){
        numAlpha.put(1,"A");
        numAlpha.put(2,"B");
        numAlpha.put(3,"C");
        numAlpha.put(4,"D");
        numAlpha.put(5,"E");
        numAlpha.put(6,"F");
        numAlpha.put(7,"G");
        numAlpha.put(8,"H");
    }


    @Override
    public abstract String toString();
}
