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
    ArrayList<Integer> allowedMoves = new ArrayList<>();

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
        if (moveStatus) {
            System.out.println("This is a vaild move: "+this.type+" to "+newPosition);
        } else {
            System.out.println("Not a vaild position, try again.");
            return moveStatus;
        }

        boolean allowedMoveStatus = allowedMoves(newPos,currentBoard);

        if (allowedMoveStatus) {
            System.out.println("This is an allowed move: "+this.type+" to "+newPosition);
        } else {
            System.out.println("Not an allowed position, try again.");
            return allowedMoveStatus;
        }

        if(allowedMoveStatus){
            currentBoard.removePieces(this);
            this.currentPos = newPosition;
            if(!currentBoard.xIDPositionAssoc.containsValue(newPosition)) {
                currentBoard.populatePieces(this);
            } else {
                System.out.println("This is a kill");
                killFunction(newPosition,currentBoard);
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

        return validMoves;
    }
    public boolean allowedMoves(Integer newPosition, Board currentBoard) {
        /*
        find position occupied by pieces in the diagonal array and move there
        if same color found, move to that (square-1)
        else if opp. color move to that square and kill the piece.
         */

        System.out.println("\nLeft: ");
        for (Integer i = 0; i < possibleMovesLeft.size(); i++) {
            Integer currentPosition = possibleMovesLeft.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesLeft.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesLeft.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }

        }

        System.out.println("\nTop: ");

        for (Integer i = 0; i < possibleMovesTop.size(); i++) {
            Integer currentPosition = possibleMovesTop.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesTop.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesTop.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }

        }

        System.out.println("\nRight: ");

        for (Integer i = 0; i < possibleMovesRight.size(); i++) {
            Integer currentPosition = possibleMovesRight.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesRight.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesRight.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }

        }

        System.out.println("\nBottom: ");

        for (Integer i = 0; i < possibleMovesBottom.size(); i++) {
            Integer currentPosition = possibleMovesBottom.get(i);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMovesBottom.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesBottom.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }

        }

        boolean allowedMoveStatus = false;

        for (int j = 0; j < allowedMovesLeft.size(); j++) {
            allowedMoves.add(allowedMovesLeft.get(j));
            if(newPosition == allowedMovesLeft.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesTop.size(); j++) {
            allowedMoves.add(allowedMovesTop.get(j));
            if(newPosition == allowedMovesTop.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesRight.size(); j++) {
            allowedMoves.add(allowedMovesRight.get(j));
            if(newPosition == allowedMovesRight.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesBottom.size(); j++) {
            allowedMoves.add(allowedMovesBottom.get(j));
            if(newPosition == allowedMovesBottom.get(j)){
                allowedMoveStatus = true;
            }
        }

        possibleMovesLeft.clear();
        possibleMovesTop.clear();
        possibleMovesRight.clear();
        possibleMovesBottom.clear();

        updateKingHash(allowedMoves);

        allowedMovesLeft.clear();
        allowedMovesTop.clear();
        allowedMovesRight.clear();
        allowedMovesBottom.clear();
        allowedMoves.clear();

        return allowedMoveStatus;

    }


    public void killFunction(String newPosition, Board currentBoard) throws IllegalArgumentException {
        Piece killedPiece  = currentBoard.positionPieceAssoc.get(newPosition);
        currentBoard.removePieces(currentBoard.positionPieceAssoc.get(newPosition));
        if((killedPiece.color == piece_color.BLACK) &&(this.color == piece_color.WHITE)){
            killedPiece.currentPos = "DEAD";
            currentBoard.deadBlackPieces.add(killedPiece);
        } else if ((killedPiece.color == piece_color.WHITE) && (this.color == piece_color.BLACK)) {
            killedPiece.currentPos = "DEAD";
            currentBoard.deadWhitePieces.add(killedPiece);
        } else {
            throw new IllegalArgumentException("Unknown dead piece!");
        }


    }

    public void updateKingHash(ArrayList<Integer> allowedMoves){
        String tempPos = new String();
        for (Integer x: allowedMoves) {
            tempPos = xIDResolver(x);
            if(kingsEight.containsKey(tempPos)){
                kingsEight.replace(tempPos,false);
            }
        }
    }

    public void sonar(String currentPos, String newPos, Board currentBoard){

        for (String pos : kingsEight.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard);
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
        String buf = "";
        buf = buf.concat("ID: "+xID+"\n");
        buf = buf.concat("Piece type: "+type+"\n");
        buf = buf.concat("Piece color: "+color+"\n");
        buf = buf.concat("Current Position: "+currentPos+"\n");
        return buf;
    }



}

