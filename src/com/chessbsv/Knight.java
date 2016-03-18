package com.chessbsv;

import java.util.ArrayList;

/**
 * Created by OM on 3/13/2016.
 */
public class Knight extends Piece{

    ArrayList<Integer> possibleMoves = new ArrayList<>();

    /*
    Diagonals considering blocking pieces, filled in by Bishop_rules,
    Considering the current state of the board.
    */

    ArrayList<Integer> allowedMoves= new ArrayList<>();


    Knight(String xID, String currentPos, Piece.piece_color color, Piece.piece_type type) throws IllegalArgumentException {
        this.xID = xID;
        this.color = color;
        this.type = type;
        if(this.validPosition(currentPos)){
            this.currentPos = currentPos;
        } else {
            throw new IllegalArgumentException("Invalid Knight position");
        }
    }

    public boolean dispMove(String newPosition, Board currentBoard) {

        //System.out.println("This is Knight:dispMove.");
        String curPos = this.currentPos;
        Integer cPos = this.positionResolver(curPos);
        Integer newPos = this.positionResolver(newPosition);
        boolean moveStatus = possibleMoves(cPos,newPos);

        three_state blk = three_state.NEITHER;


        if(!moveStatus){
            System.out.println("Not a vaild position, try again.");
            return moveStatus;
        } else {
            System.out.println("This is a vaild move: "+this.type+" to "+newPosition);
        }

        boolean allowedMoveStatus = allowedMoves(newPos, currentBoard,blk);

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
                killFunction(newPosition,currentBoard);
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

        Integer tempPos = currentPos;

        //System.out.println("\nTop Left: ");

        currentPos = currentPos -6;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if (newPosition == currentPos) {
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nTop Right: ");

        currentPos = currentPos - 10;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Right: ");

        currentPos = currentPos -15;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Left: ");


        currentPos = currentPos -17;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +6;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +10;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +15;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +17;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }


        return validMoves;
    }

    @Override
    public boolean allowedMoves(Integer newPosition, Board currentBoard, three_state blk) {
        /*
        find position occupied by pieces in the diagonal array and move there
        if same color found, move to that (square-1)
        else if opp. color move to that square and kill the piece.
         */

        //System.out.println("\nTop Left: ");

        for (int j = 0; j < possibleMoves.size(); j++) {
            Integer currentPosition = possibleMoves.get(j);
            String exID = this.xIDResolver(currentPosition);
            //System.out.println(exID);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMoves.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMoves.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }

        }

        boolean allowedMoveStatus = false;

        for (int j = 0; j < allowedMoves.size(); j++) {
            if(newPosition == allowedMoves.get(j)){
                allowedMoveStatus = true;
            }
        }


        possibleMoves.clear();

        if(blk == three_state.BLACK){
            updateBlkKingHash(allowedMoves,currentBoard);
        } else if(blk == three_state.WHITE){
            updateWhtKingHash(allowedMoves,currentBoard);
        } else {
        }

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

    // ########################## whtSonar <-> blkKingHashes ##########################

    @Override
    public void updateBlkKingHash(ArrayList<Integer> allowedMoves,Board currentBoard){
        String tempPos = new String();
        for (Integer x: allowedMoves) {
            tempPos = xIDResolver(x);
            if(currentBoard.blkKingsEight.containsKey(tempPos)){
                System.out.println("Calling white knight edits blkKingsEight");
                currentBoard.blkKingsEight.replace(tempPos,false);
            }

            if(currentBoard.blkKingsPos.containsKey(tempPos)){
                System.out.println("Calling white knight edits blkKingsPos");
                currentBoard.blkKingsPos.replace(tempPos,false);
            }
        }
    }

    @Override
    public void whtSonar(String currentPos, Board currentBoard){

        three_state blk = three_state.BLACK;

        for (String pos : currentBoard.blkKingsEight.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            System.out.println("Calling white knight's moves"+pos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

        for (String pos : currentBoard.blkKingsPos.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            System.out.println("Calling white knight's moves"+pos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

    }


    // ########################## blkSonar <-> whtKingHashes ##########################


    @Override
    public void updateWhtKingHash(ArrayList<Integer> allowedMoves, Board currentBoard) {
        String tempPos = new String();
        for (Integer x: allowedMoves) {
            tempPos = xIDResolver(x);
            if(currentBoard.whtKingsEight.containsKey(tempPos)){
                System.out.println("calling knight edits Kingshash");
                currentBoard.whtKingsEight.replace(tempPos,false);
            }

            if(currentBoard.whtKingsPos.containsKey(tempPos)){
                System.out.println("calling knight edits Kingspos");
                currentBoard.whtKingsPos.replace(tempPos,false);
            }
        }

    }


    @Override
    public void blkSonar(String currentPos, Board currentBoard) {
        three_state blk = three_state.WHITE;

        for (String pos : currentBoard.whtKingsEight.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            System.out.println("calling knight H5's moves: "+currentPos+pos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

        for (String pos : currentBoard.whtKingsPos.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            System.out.println("calling knight H5's moves: "+currentPos+pos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
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


