package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class GoldenMasterTest {
    @Test
    void something() {
        final ByteArrayOutputStream fakeStdoutStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(fakeStdoutStream));

        GameRunner.main(new String[0]);

        final String allTextFromStdout = fakeStdoutStream.toString(Charset.forName("UTF-8"));

        Approvals.verify(allTextFromStdout);
    }
}
