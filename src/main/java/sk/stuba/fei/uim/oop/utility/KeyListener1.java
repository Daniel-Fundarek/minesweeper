package sk.stuba.fei.uim.oop.utility;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  class KeyListener1 extends JFrame implements KeyListener {
    Table table = new Table();
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==38){
            table.pohybX--;
            table.printPlayerBoard();
        }
        if(e.getKeyCode()==40){
            table.pohybX++;
            table.printPlayerBoard();
        }
        if(e.getKeyCode()==37){
            table.pohybY--;
            table.printPlayerBoard();
        }
        if(e.getKeyCode()==39){
            table.pohybY++;
            table.printPlayerBoard();

        }
        if(e.getKeyCode()==10){
            table.printPlayerBoard();
            table.boomStatus=table.square.checkForMine(table.pohybX,table.pohybY,0);
            if(table.boomStatus==1){
                table.printPlayerBoard();
                System.out.println("BOOOOOM!!!!!!!!");
                table.frame.dispose();
            }
            else{
                table.printPlayerBoard();
            }
        }
        if(e.getKeyCode()==32){
            table.boomStatus=table.square.checkForMine(table.pohybX,table.pohybY,1);
            table.printPlayerBoard();
            if(table.boomStatus==2){
                table.MinesFlagged++;
                if(table.MinesFlagged==table.numOfMines){
                    table.printPlayerBoard();
                    System.out.println("Flaggol si vsetky miny Vyhral si!");
                    table.frame.dispose();

                }
            }
            else{
                table.printPlayerBoard();
            }
            //posli flag
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
