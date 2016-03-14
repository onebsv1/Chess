package com.chessbsv;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by OM on 3/9/2016.
 */

public class Rook extends Piece {


    ArrayList<Integer> possibleMovesLeft = new ArrayList<>();
    ArrayList<Integer> possibleMovesTop = new ArrayList<>();
    ArrayList<Integer> possibleMovesRight = new ArrayList<>();
    ArrayList<Integer> possibleMovesBottom = new ArrayList<>();

    ArrayList<Integer> allowedMovesLeft = new ArrayList<>();
    ArrayList<Integer> allowedMovesTop = new ArrayList<>();
    ArrayList<Integer> allowedMovesRight = new ArrayList<>();
    ArrayList<Integer> allowedMovesBottom = new ArrayList<>();

    HashSet<Integer> Left = new HashSet<>();
    HashSet<Integer> Top = new HashSet<>();
    HashSet<Integer> Right = new HashSet<>();
    HashSet<Integer> Bottom = new HashSet<>();


    Rook(String xID, String currentPos,piece_color color,piece_type type) throws IllegalArgumentException {
        this.xID = xID;
        this.color = color;
        this.type = type;
        if(this.validPosition(currentPos)){
            this.currentPos = currentPos;
        } else {
            throw new IllegalArgumentException("Invalid Rook position");
        }
    }

    public boolean dispMove(String newPosition, Board currentBoard) {

        //System.out.println("This is Rook:dispMove.");
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
            if(!Board.xIDPositionAssoc.containsKey(newPosition)) {
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
        boolean validMoves = false;
        System.out.println("Current Pos, New Pos: "+this.xIDResolver(currentPos)+" , "+this.xIDResolver(newPosition));

        loadLeftRight();
        loadTopBottom();
        Integer tempPos = currentPos;

        while (!Left.contains(currentPos)){
            currentPos = currentPos - 1;
            possibleMovesLeft.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nTop Right: ");

        while (!Top.contains(currentPos)){
            currentPos = currentPos - 8;
            possibleMovesTop.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Right: ");

        while (!Right.contains(currentPos)){
            currentPos = currentPos + 1;
            possibleMovesRight.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Left: ");

        while (!Bottom.contains(currentPos)){
            currentPos = currentPos + 8;
            possibleMovesBottom.add(currentPos);
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

        System.out.println("\nLeft: ");
        int i = 0;
        boolean condition = false;
        while((!condition)&&(i<possibleMovesLeft.size())){
            Integer currentPosition = possibleMovesLeft.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesLeft.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesLeft.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        System.out.println("\nTop: ");

        i=0;
        condition=false;
        while((!condition)&&(i<possibleMovesTop.size())){
            Integer currentPosition = possibleMovesTop.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesTop.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesTop.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        System.out.println("\nRight: ");

        i=0;
        condition=false;
        while((!condition)&&(i<possibleMovesRight.size())){
            Integer currentPosition = possibleMovesRight.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesRight.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesRight.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        System.out.println("\nBottom: ");

        i=0;
        condition=false;
        while((!condition)&&(i<possibleMovesBottom.size())){
            Integer currentPosition = possibleMovesBottom.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = Board.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesBottom.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesBottom.add(currentPosition);
                condition = true;
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                condition = true;
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }
            i=i+1;
        }

        boolean allowedMoveStatus = false;

        for (int j = 0; j < allowedMovesLeft.size(); j++) {
            if(newPosition == allowedMovesLeft.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesTop.size(); j++) {
            if(newPosition == allowedMovesTop.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesRight.size(); j++) {
            if(newPosition == allowedMovesRight.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesBottom.size(); j++) {
            if(newPosition == allowedMovesBottom.get(j)){
                allowedMoveStatus = true;
            }
        }

        possibleMovesLeft.clear();
        possibleMovesTop.clear();
        possibleMovesRight.clear();
        possibleMovesBottom.clear();

        allowedMovesLeft.clear();
        allowedMovesTop.clear();
        allowedMovesRight.clear();
        allowedMovesBottom.clear();

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


    public void loadLeftRight(){

        for (Integer i = 0; i < 64; i+=8) {
            Left.add(i);
        }

        for (Integer i = 7; i < 64; i+=8) {
            Right.add(i);
        }

    }

    public void loadTopBottom(){

        for (Integer i = 0; i < 8; i++) {
            Top.add(i);
        }

        for (Integer i = 56; i < 64; i++) {
            Bottom.add(i);
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
