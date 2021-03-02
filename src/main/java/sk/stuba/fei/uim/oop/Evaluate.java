package sk.stuba.fei.uim.oop;

public class Evaluate {
    char[][] board;


    public Evaluate(char[][] board,int x, int y){
        this.board=board;
        this.x = x;
        this.x = x;
    }

    private void evalSquaresAround(int x, int y){

        if (board[x][y]=='0') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if(board[x-1][y-1]=='0')

                }
            }
        }
        else
            ;
    }
    private void checkBoarder(int x, int y){
        int hore = 0;
        int dole = 0;
        int doprava = 0;
        int dolava = 0;
        if(x==0){
             hore=1;   //kraj
        }
        if(y==0){
             dolava=1;   //kraj
        }
        if(x== board.length){
             dole=1;   //kraj
        }
        if(y== board[1].length){
             doprava=1;   //kraj
        }


    }

}
