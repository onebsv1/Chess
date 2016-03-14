package com.chessbsv;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Bishop extends Piece {

    /*
    Diagonals considering the piece to be the center and extending from the
    piece all the way to the ends of the board irrespective of other pieces on the board.
    */

    ArrayList<Integer> possibleMovesDiagonal1 = new ArrayList<>();
    ArrayList<Integer> possibleMovesDiagonal2 = new ArrayList<>();
    ArrayList<Integer> possibleMovesDiagonal3 = new ArrayList<>();
    ArrayList<Integer> possibleMovesDiagonal4 = new ArrayList<>();

    /*
    Diagonals considering blocking pieces, filled in by Bishop_rules,
    Considering the current state of the board.
    */

    ArrayList<Integer> allowedMovesDiagonal1 = new ArrayList<>();
    ArrayList<Integer> allowedMovesDiagonal2 = new ArrayList<>();
    ArrayList<Integer> allowedMovesDiagonal3 = new ArrayList<>();
    ArrayList<Integer> allowedMovesDiagonal4 = new ArrayList<>();

    /*
    Boundary Hashes to determine out of bounds check, while
    populating the diagonals.
    */

    HashSet<Integer> topLeft = new HashSet<>();
    HashSet<Integer> topRight = new HashSet<>();
    HashSet<Integer> bottomLeft = new HashSet<>();
    HashSet<Integer> bottomRight = new HashSet<>();


    Bishop(String xID, String currentPos,piece_color color,piece_type type) throws IllegalArgumentException {
        this.xID = xID;
        this.color = color;
        this.type = type;
        if(this.validPosition(currentPos)){
            this.currentPos = currentPos;
        } else {
            throw new IllegalArgumentException("Invalid Bishop position");
        }
    }

    public boolean dispMove(String newPosition, Board currentBoard) {

        //System.out.println("This is Bishop:dispMove.");
        String curPos = this.currentPos;
        Integer cPos = this.positionResolver(curPos);
        Integer newPos = this.positionResolver(newPosition);
        boolean moveStatus = possibleMoves(cPos,newPos);
        if(!moveStatus){
            System.out.println("Not a vaild position, try again.");
            return moveStatus;
        } else {
            System.out.println("This is a vaild move: "+this.type+" to "+newPosition);
        }

        boolean allowedMoveStatus = allowedMoves(newPos);

        if(!allowedMoveStatus){
            System.out.println("Not an allowed position, try again.");
            return allowedMoveStatus;
        } else {
            System.out.println("This is an allowed move: "+this.type+" to "+newPosition);
        }

        if(allowedMoveStatus){
            currentBoard.removePieces(this);
            this.currentPos = newPosition;
            if(!currentBoard.xIDPositionAssoc.containsValue(newPosition)) {
                currentBoard.populatePieces(this);
            } else {
                killFunction(newPosition);
                currentBoard.removePieces(Board.positionPieceAssoc.get(newPosition));
                currentBoard.populatePieces(this);
            }
        }


        return allowedMoveStatus;
    }


    public boolean possibleMoves(Integer currentPos, Integer newPosition){
        //public int populate_diagonal();
        // check diagonal entries, store aLL hashed square ids along 4 1D arrays.
        boolean validMoves = false;
        System.out.println("Current Pos, New Pos: "+this.xIDResolver(currentPos)+" , "+this.xIDResolver(newPosition));

        loadtopLeft();
        loadtopRight();
        loadbottomLeft();
        loadbottomRight();

        Integer tempPos = currentPos;

        //System.out.println("\nTop Left: ");

        while (!topLeft.contains(currentPos)){
            currentPos = currentPos -9;
            possibleMovesDiagonal1.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nTop Right: ");

        while (!topRight.contains(currentPos)){
            currentPos = currentPos -7;
            possibleMovesDiagonal2.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Right: ");

        while (!bottomRight.contains(currentPos)){
            currentPos = currentPos + 9;
            possibleMovesDiagonal3.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Left: ");

        while (!bottomLeft.contains(currentPos)){
            currentPos = currentPos + 7;
            possibleMovesDiagonal4.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //while index !in (starting col && starting row)
        //pushback currpos-9
        //end

        return validMoves;
    }

    public boolean allowedMoves(Integer newPosition) {
        /*
        find position occupied by pieces in the diagonal array and move there
        if same color found, move to that (square-1)
        else if opp. color move to that square and kill the piece.
         */

        System.out.println("\nTop Left: ");
        int i = 0;
        boolean condition = false;
        while((!condition)&&(i<possibleMovesDiagonal1.size())){
            Integer currentPosition = possibleMovesDiagonal1.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesDiagonal1.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal1.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        System.out.println("\nTop Right: ");

        i=0;
        condition=false;
        while((!condition)&&(i<possibleMovesDiagonal2.size())){
            Integer currentPosition = possibleMovesDiagonal2.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesDiagonal2.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal2.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        System.out.println("\nBottom Right: ");

        i=0;
        condition=false;
        while((!condition)&&(i<possibleMovesDiagonal3.size())){
            Integer currentPosition = possibleMovesDiagonal3.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesDiagonal3.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal3.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        System.out.println("\nBottom Left: ");

        i=0;
        condition=false;
        while((!condition)&&(i<possibleMovesDiagonal4.size())){
            Integer currentPosition = possibleMovesDiagonal4.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesDiagonal4.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal4.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        boolean allowedMoveStatus = false;

        for (int j = 0; j < allowedMovesDiagonal1.size(); j++) {
            if(newPosition == allowedMovesDiagonal1.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesDiagonal2.size(); j++) {
            if(newPosition == allowedMovesDiagonal2.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesDiagonal3.size(); j++) {
            if(newPosition == allowedMovesDiagonal3.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesDiagonal4.size(); j++) {
            if(newPosition == allowedMovesDiagonal4.get(j)){
                allowedMoveStatus = true;
            }
        }

        possibleMovesDiagonal1.clear();
        possibleMovesDiagonal2.clear();
        possibleMovesDiagonal3.clear();
        possibleMovesDiagonal4.clear();

        allowedMovesDiagonal1.clear();
        allowedMovesDiagonal2.clear();
        allowedMovesDiagonal3.clear();
        allowedMovesDiagonal4.clear();

        return allowedMoveStatus;

    }

    public void killFunction(String newPosition) throws IllegalArgumentException {
        Piece killedPiece  = Board.positionPieceAssoc.get(newPosition);
        if(killedPiece.color == piece_color.BLACK){
            System.out.println("This is a kill");
            killedPiece.currentPos = "DEAD";
            Board.deadBlackPieces.add(killedPiece);
        } else if (killedPiece.color == piece_color.WHITE) {
            killedPiece.currentPos = "DEAD";
            Board.deadWhitePieces.add(killedPiece);
        } else {
            throw new IllegalArgumentException("Unknown dead piece!");
        }


    }


    public void loadtopLeft(){

        for (Integer i = 0; i < 8; i++) {
            topLeft.add(i);
        }

        for (Integer i=0; i<64; i+=8) {
            topLeft.add(i);
        }

    }

    public void loadtopRight(){

        for (Integer i = 0; i < 8; i++) {
            topRight.add(i);
        }

        for (Integer i=7; i<64; i+=8) {
            topRight.add(i);
        }

    }

    public void loadbottomLeft(){

        for (Integer i = 56; i < 64; i++) {
            bottomLeft.add(i);
        }

        for (Integer i=0; i<64; i+=8) {
            bottomLeft.add(i);
        }

    }

    public void loadbottomRight(){

        for (Integer i = 56; i < 64; i++) {
            bottomRight.add(i);
        }

        for (Integer i=7; i<64; i+=8) {
            bottomRight.add(i);
        }

    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("ID: "+xID+"\n");
        buf.append("Piece type: "+type+"\n");
        buf.append("Piece color: "+color+"\n");
        buf.append("Current Position: "+currentPos+"\n");
        return buf.toString();
    }


}
