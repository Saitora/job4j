package ix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class SortDepListAppTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final String ERR_LAST_ARG = "Error! Wrong last argument.\n";
    private final String ERR_ARGS_NUM = "Error! Wrong arguments number.\n";
    private final String ERR_WRONG_INPUT_FILE = "Error! Input file not found!\n";
    private final String HELP_STRING = "Usage: sort_deps [input_file] [output_file] [-A|-D]\n" +
            "    Options\n" +
            "      -A    Ascending order\n" +
            "      -D    Descending order\n";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void appShouldAnswerWithErrorWhenWrongArgsNumber() {
        SortDepListApp.main(new String[]{});
        assertEquals(ERR_ARGS_NUM + HELP_STRING, outContent.toString());
    }

    @Test
    public void appShouldAnswerWithErrorWhenWrongLastArg() {
        SortDepListApp.main(new String[]{"a", "b", "c"});
        assertEquals(ERR_LAST_ARG + HELP_STRING, outContent.toString());
    }

    @Test
    public void appShouldAnswerWithErrorWhenInputFileNotFound() {
        SortDepListApp.main(new String[]{"a", "b", "A"});
        assertEquals(ERR_WRONG_INPUT_FILE + HELP_STRING, outContent.toString());
    }

    @Test
    public void getDepListFromFileShouldReturnDepListFromFile() throws IOException {
        String fname = SortDepListAppTest.class.getResource("dep_list.txt").getFile();
        String returnedContent = "".join("\n", SortDepListApp.getDepListFromFile(fname));
        String content = new String(Files.readAllBytes(Paths.get(fname)));
        assertEquals(content, returnedContent);
    }

    @Test
    public void getSortedDepListShouldReturnAscSortedListWhenModeIsA() {

    }

    @Test
    public void getSortedDepListShouldReturnDescSortedListWhenModeIsD() {

    }

    @Test
    public void getSortedDepListShouldReturnAscSortedListWithAdditionalRowsWhenNotAllDepsPresentAndModeIsA() {

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


}
