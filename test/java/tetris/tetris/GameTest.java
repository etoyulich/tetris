package tetris;

import event.GameActionEvent;
import event.GameActionListener;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private GameModel gameModel;
    private int countScoresUpdated = 0;
    private int countGameIsOver = 0;
    private int countShapeIsUpd = 0;
    private int countNextShapeUpd = 0;

    private class GameModelListener implements GameActionListener{

        @Override
        public void scoresUpdated(@NotNull GameActionEvent event) {
            countScoresUpdated++;
        }

        @Override
        public void shapeIsUpdated(@NotNull GameActionEvent event, @NotNull Shape shape) {
            countShapeIsUpd++;
        }

        @Override
        public void gameIsOver(@NotNull GameActionEvent event) {
            countGameIsOver++;
        }

        @Override
        public void nextShapeUpdated(@NotNull GameActionEvent event, @NotNull Shape shape) {
            countNextShapeUpd++;
        }
    }

    @BeforeEach
    public void testSetup() {
        countScoresUpdated = 0;
        countGameIsOver = 0;
        countShapeIsUpd = 0;
        countNextShapeUpd = 0;
        gameModel = new GameModel(4, 6, new GameModelListener());
    }

    @Test
    void test_pointsScored(){
        int expPoints = 100;
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < gameModel.getGlass().getWidth(); x++) {
                gameModel.getGlass().cell(x, y).setPiece(new Piece(Color.CYAN));
            }
        }

        boolean canMove;
        do {
            canMove = gameModel.makeOneStep();
        }while (canMove);

        int expScoresUpdated = 1;
        int expGameIsOver = 1;
        assertEquals(expScoresUpdated, countScoresUpdated);
        assertEquals(expGameIsOver, countGameIsOver);
        assertTrue(countShapeIsUpd >= 2);
        assertTrue(countNextShapeUpd >= 2);
        assertEquals(expPoints, gameModel.getPoints());
    }

    @Test
    void test_pointsNotScored(){
        int expPoints = 0;

        boolean canMove;
        do {
            canMove = gameModel.makeOneStep();
        }while (canMove);


        int expScoresUpdated = 0;
        int expGameIsOver = 1;
        assertEquals(expScoresUpdated, countScoresUpdated);
        assertEquals(expGameIsOver, countGameIsOver);
        assertTrue(countShapeIsUpd >= 2);
        assertTrue(countNextShapeUpd >= 2);
        assertEquals(expPoints, gameModel.getPoints());
    }
}
