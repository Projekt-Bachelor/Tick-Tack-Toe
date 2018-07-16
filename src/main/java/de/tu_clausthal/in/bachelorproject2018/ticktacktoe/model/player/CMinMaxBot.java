package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.player;

import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.brett.ISpieleBrett;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.EItem;
import de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model.item.IItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Klasse des Min-Max-Bot
 */
public final class CMinMaxBot extends IBasePlayer
{
    int[][] board = new int[3][3];
    List<int[]> availablePoints;
    int[] computersMove;
    /**
     * Konstruktor
     *
     * @param p_value Item (Kreuz / Kreis), das durch den Spieler verwendet wird
     */
    public CMinMaxBot()
    {
        super( "Min-Max-Bot", EItem.KREIS );
    }

    @Override
    public void accept( final ISpieleBrett p_brett )
    {
        toIntArray(p_brett);
        minimax(0, 1);
        while ( !p_brett.set( m_value.apply( computersMove[0], computersMove[1] ) ) ) {
            minimax(0, 1);
        }
    }

    public void toIntArray(final ISpieleBrett p_brett){
        AtomicReference<IItem>[][] m_elements = p_brett.getM_elements();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(m_elements[i][j].get() != null) {
                    if (m_elements[i][j].get().getM_item() == EItem.KREUZ) {
                        board[i][j] = 2;
                    } else if (m_elements[i][j].get().getM_item() == EItem.KREIS) {
                        board[i][j] = 1;
                    }
                }else{
                    board[i][j] = 0;
                }
            }
        }
    }

    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                return true;
            }
        }

        return false;
    }

    public List<int[]> getAvailableStates() {
        availablePoints = new ArrayList<int[]>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    int[] field = {i, j};
                    availablePoints.add(field);
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(int[] point, int player) {
        board[point[0]][point[1]] = player;
    }

    public int minimax(int depth, int turn) {
        if (hasOWon()) return +10;
        if (hasXWon()) return -10;

        List<int[]> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            int[] point = pointsAvailable.get(i);
            if (turn == 1) {
                placeAMove(point, 1);
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);

                if(currentScore >= 0){ if(depth == 0) computersMove = point;}
                if(currentScore == 1){board[point[0]][point[1]] = 0; break;}
                if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
            } else if (turn == 2) {
                placeAMove(point, 2);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if(min == -1){board[point[0]][point[1]] = 0; break;}
            }
            board[point[0]][point[1]] = 0;
        }
        return turn == 1?max:min;
    }

}
