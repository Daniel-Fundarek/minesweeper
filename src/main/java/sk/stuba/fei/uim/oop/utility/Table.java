package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.utility.ConsoleColors;
import sk.stuba.fei.uim.oop.utility.Evaluate;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Table {
    private int sizeX;
    private int sizeY;
    private int numOfMines;
    private char[][] board;
    private char[][] boardForPlayer;
    private Random random =new Random();
    private Evaluate square;
    ConsoleColors color= new ConsoleColors();
    private int pohybX=1;
    private int pohybY=1;
    public int boomStatus=0;
    public int MinesFlagged=0;


    public Table(int sizeX,int sizeY, int numOfMines){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.numOfMines=numOfMines;
    }
    public Table(){
        this(5,5,10);
    }

    public void createBoard(){    // neskor zmenit na private
        JFrame frame = new JFrame();
        this.board = new char[sizeX+2][sizeY+2];
        this.boardForPlayer = new char[sizeX+2][sizeY+2];
        square = new Evaluate(board,boardForPlayer);
        fillBoard();
        fillBoardForPlayer();
        printBoard();
        printPlayerBoard();



        KeyListener  keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==38){
                    pohybX--;
                    printPlayerBoard();
                }
                if(e.getKeyCode()==40){
                    pohybX++;
                    printPlayerBoard();
                }
                if(e.getKeyCode()==37){
                    pohybY--;
                    printPlayerBoard();
                }
                if(e.getKeyCode()==39){
                    pohybY++;
                    printPlayerBoard();

                }
                if(e.getKeyCode()==10){
                    printPlayerBoard();
                    boomStatus=square.checkForMine(pohybX,pohybY,0);//posli suradnice
                    if(boomStatus==1){
                        printPlayerBoard();
                        System.out.println("BOOOOOM!!!!!!!!");
                        frame.dispose();
                    }
                    else{
                        printPlayerBoard();
                    }
                }
                if(e.getKeyCode()==32){
                    boomStatus=square.checkForMine(pohybX,pohybY,1);
                    printPlayerBoard();
                     if(boomStatus==2){
                        MinesFlagged++;
                        if(MinesFlagged==numOfMines){
                            printPlayerBoard();
                            System.out.println("Flaggol si vsetky miny Vyhral si!");
                            frame.dispose();

                        }
                    }
                     else{
                         printPlayerBoard();
                     }
                    //posli flag
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }


        };
        frame.addKeyListener(keyListener);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1,1);
        frame.setVisible(true);


        //printBoard();
        //runGame();
    }
    private boolean randPerm(){
        int numOfTiles = sizeX*sizeY;
        int chanceOfMine = numOfTiles/numOfMines;
        boolean trueFalse = (random.nextInt(chanceOfMine) == 0);
        return  trueFalse;
    }


    private void fillBoard(){
        int minesPlaced=0;
        int emptyTiles =0;
        char mineOrEmpty; // bud 0 alebo 1 reprezentujuca minu alebo prazdne policko
        for(int i=0; i< board.length;i++){
            for(int j=0;j< board[1].length;j++){
                if(j==0||i==0||i==board.length-1||j==board[1].length-1){
                    board[i][j] = '*';
                }
                else {
                    if (minesPlaced < numOfMines && emptyTiles < (sizeX * sizeY - numOfMines)) {
                        mineOrEmpty = (randPerm()) ? 'X' : '0';
                        board[i][j] = mineOrEmpty;
                        if (mineOrEmpty == 'X') {
                            minesPlaced++;
                        } else
                            emptyTiles++;
                    } else if (minesPlaced < numOfMines && emptyTiles == (sizeX * sizeY - numOfMines)) {
                        board[i][j] = 'X';
                        minesPlaced++;
                    } else {
                        board[i][j] = '0';
                        emptyTiles++;
                    }
                }
            }
        }
    }
    private void fillBoardForPlayer(){

        for(int i=0; i< boardForPlayer.length;i++){
            for(int j=0;j< boardForPlayer[1].length;j++){
                if(j==0||i==0||i==boardForPlayer.length-1||j==boardForPlayer[1].length-1){
                    boardForPlayer[i][j] = '*';
                }
                else {

                        boardForPlayer[i][j] = '■';

                }
            }
        }
    }

    private void printBoard(){
        for(int i=0; i< board.length;i++){
            for(int j=0;j< board[1].length;j++){
                System.out.print(" "+ board[i][j]);

            }
            System.out.println();
        }
    }

    private void printPlayerBoard(){
        for(int i=0; i< boardForPlayer.length;i++){
            for(int j=0;j< boardForPlayer[1].length;j++){
                if(pohybX==i&&pohybY==j){
                    System.out.print("  "+colorSettting(-2,-2) + boardForPlayer[i][j]);
                }
                else {
                    System.out.print("  " + colorSettting(i, j) + boardForPlayer[i][j]);
                }
                if(j==boardForPlayer[1].length-1 && i>0 && i<boardForPlayer.length-1){
                    System.out.print(" "+i);  // test
                }
            }
            System.out.println();
        }
        System.out.print("     ");
        for(int cislicovanie=1;cislicovanie<=boardForPlayer[1].length-2;cislicovanie++){
            if(cislicovanie<10) {
                System.out.print(cislicovanie + "  ");
            }
            else{
                System.out.print(cislicovanie + " ");
            }
        }
        System.out.println(colorSettting(-1,-1));
    }
    private String colorSettting(int x, int y){
        String colorChar = new String();
        if(x==-1 && y==-1){
            colorChar=color.RESET;
        }
        else if(x==-2 && y==-2){
            colorChar=color.BLACK_BOLD_BRIGHT;
        }
        else {
            switch (boardForPlayer[x][y]) {

                case '1':
                    colorChar = color.BLUE;
                    break;
                case '2':
                    colorChar = color.GREEN;
                    break;
                case '3':
                    colorChar = color.RED;
                    break;
                case '4':
                    colorChar = color.BLUE_BRIGHT;
                    break;
                case '5':
                    colorChar = color.RED_BRIGHT;
                    break;
                case '6':
                    colorChar = color.CYAN;
                    break;
                case '7':
                    colorChar = color.PURPLE;
                    break;
                case '8':
                    colorChar = color.BLACK;
                    break;
                case '!':
                    colorChar = color.YELLOW;
                    break;
                case '*':
                    colorChar = color.BLACK;
                    break;

                default:
                    colorChar = color.RESET;
                    break;
            }
        }
            return colorChar;

    }
    private void runGame(){
        int boomStatus=0;
        int MinesFlagged=0;
        do {
            printPlayerBoard();
            boomStatus=square.checkForMine(KeyboardInput.readInt("zadaj x suradnicu: "), KeyboardInput.readInt("zadaj y suradnicu: "),KeyboardInput.readInt("zadaj 0 ak chces kliknut alebo 1 ak ches flagnut blok: "));
            if(boomStatus==1){
                printPlayerBoard();
                System.out.println("BOOOOOM!!!!!!!!");
            }
            else if(boomStatus==2){
                MinesFlagged++;
                if(MinesFlagged==numOfMines){
                    printPlayerBoard();
                    System.out.println("Flaggol si vsetky miny Vyhral si!");
                }
            }
        }while(boomStatus != 1 && MinesFlagged!=numOfMines);
    }


    }

