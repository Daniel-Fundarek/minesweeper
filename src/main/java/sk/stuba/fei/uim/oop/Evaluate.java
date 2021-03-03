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
            board[x][y]='@';
            if(evalSquare==0){
                boardForPlayer[x][y]='0';
            }
            else {

                char evalSquareChar = (char) (evalSquare + '0');
                boardForPlayer[x][y] = evalSquareChar;
            }
            if(x>0 && y>0 && x< board.length && y < board.length) {
              //  evalSquaresAround(x - 1, y - 1);
              //  evalSquaresAround(x + 1, y + 1);
              //  evalSquaresAround(x + 1, y - 1);
              //  evalSquaresAround(x - 1, y + 1);

                evalSquaresAround(x , y - 1);
                evalSquaresAround(x , y + 1);
                evalSquaresAround(x + 1, y );
                evalSquaresAround(x - 1, y );

            }
        }
    }
    public int checkForMine(int x,int y,int typeFlagPress){
        if(typeFlagPress==0){
            if (board[x][y]=='X') {
                boardForPlayer[x][y]='X';
                return 1;
            }
            else{
                evalSquaresAround(x,y);
                return 0;
            }
        }
        else{
            boardForPlayer[x][y]='!';
            if (board[x][y]=='X') {
                return 2;
            }
            else{
                return 0;
            }
        }
    }


    }


