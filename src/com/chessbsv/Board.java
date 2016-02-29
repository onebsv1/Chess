package com.chessbsv;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Board extends Row{

    public void drawBoard(){
        boolean toggle = false;
        for (int i = 0; i < 8; i++) {
            Row r = new Row();
            r.spawnSquares(toggle,rowLabels.values()[i]);
            System.out.print(r.Draw());
            toggle = !toggle;
        }
    }

}
