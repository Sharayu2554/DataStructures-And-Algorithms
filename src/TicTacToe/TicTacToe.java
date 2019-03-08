/**
 * Class to Implement TicTacToe
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/7/19
 * Project : TicTacToe
 **/

package TicTacToe;

public class TicTacToe {

    //use base 3 : sum * 3 + value : pre compute table with 369 possibilities. Very efficient
    //if we know what was last move made : solution #2

    enum Piece { EMPTY, RED, BLUE };

    boolean hasWonRow(Piece[][] board, int row) {
        Piece prev = board[row][0];
        for (int i =1; i < board.length; i++ ) {
            if (prev != board[row][i])
                return false;
        }
        return true;
    }

    boolean hasWonCol(Piece[][] board, int col) {
        Piece prev = board[0][col];
        for (int i = 1; i < board.length; i++) {
            if (prev != board[i][col])
                return false;
        }
        return true;
    }

    boolean checkDiagonalLeft(Piece[][] board) {
        Piece prev = board[0][0];
        for (int i =1; i< board.length; i++) {
            if (prev != board[i][i])
                return false;
        }
        return true;
    }

    boolean checkDiagonalRight(Piece[][] board) {
        Piece prev = board[0][board.length -1];
        for (int i = 1; i< board.length; i++) {
            if (prev != board[i][board.length -i - 1])
                return false;
            prev = board[i][board.length - i - 1];
        }
        return true;
    }

    boolean hasWonDiagonal(Piece[][] board, int row, int col) {
        if (row < 0 || row >= board.length  || col < 0 || col >= board.length )
            return true;

        if (row == col && checkDiagonalLeft(board))
            return true;

        if (row + col + 1 == board.length)
            return checkDiagonalRight(board);

        return false;
    }

    Piece hasWon(Piece[][] board, int row, int col) {

        if (board.length != board[0].length) {
            return Piece.EMPTY;
        }

        if (board[row][col] == Piece.EMPTY)
            return Piece.EMPTY;

        return (hasWonRow(board, row) || hasWonCol(board, col) || hasWonDiagonal(board, row, col)) ? board[row][col] : Piece.EMPTY;
    }

    /**
     * Problem in above code is too much repetation of code
     * instead we create generic class check with start row and start col value and increament of row, col values passed ot it.
     * @param args
     */
    public static void main(String args[]) {

    }
}
