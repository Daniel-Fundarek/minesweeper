package sk.stuba.fei.uim.oop;

import java.util.Random;

public class Table {
    private int sizeX;
    private int sizeY;
    private int numOfMines;
    private char[][] board;
    private Random random =new Random();



    public Table(int sizeX,int sizeY, int numOfMines){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.numOfMines=numOfMines;
    }
    public Table(){
        this(5,5,10);
    }

    public void createBoard(){    // neskor zmenit na private
        this.board = new char[sizeX+2][sizeY+2];
        fillBoard();
        printBoard();
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

    private void printBoard(){
        for(int i=0; i< board.length;i++){
            for(int j=0;j< board[1].length;j++){
                System.out.print(" "+ board[i][j]);

            }
            System.out.println();
        }
    }


    }

