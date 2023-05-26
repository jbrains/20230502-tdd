package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Random;

public class GoldenMasterTest {
    @Test
    void something() {
        final ByteArrayOutputStream fakeStdoutStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(fakeStdoutStream));

        runGame(762);

        final String allTextFromStdout = fakeStdoutStream.toString(Charset.forName("UTF-8"));

        Approvals.verify(allTextFromStdout);
    }

    private static void runGame(int pathNumber) {
        boolean notAWinner;

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(pathNumber);

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        } while (notAWinner);

    }
}
