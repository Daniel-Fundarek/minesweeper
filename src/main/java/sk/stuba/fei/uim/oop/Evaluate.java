package sk.stuba.fei.uim.oop;

public class Evaluate {
     char[][] board;
     char[][] boardForPlayer;
     int x;
     int y;

    public Evaluate(char[][] board,char[][] boardForPlayer){
        this.board=board;
        this.boardForPlayer = boardForPlayer;
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }


    public void evalSquaresAround(int x, int y){
        int boomStatus=0;
        if (board[x][y]=='0') {

            int evalSquare=0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if(board[x-1+i][y-1+j]=='X'){
                        evalSquare++;
                    }
                }
            }
            if(evalSquare==0){
                boardForPlayer[x][y]='@';
            }
            else {
                char evalSquareChar = (char) (evalSquare + '0');
                boardForPlayer[x][y] = evalSquareChar;
            }
            if(x>1 && y>1 && x< board.length-1 && y < board.length-1) {
                evalSquaresAround(x - 1, y - 1);
                evalSquaresAround(x + 1, y + 1);
                evalSquaresAround(x + 1, y - 1);
                evalSquaresAround(x - 1, y + 1);
            }
        }
    }
    public int checkForMine(int x,int y){
        if (board[x][y]=='X') {
            return 1;
        }
        else{
            evalSquaresAround(x,y);
            return 0;
        }
    }


    }


