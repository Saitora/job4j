package ix;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepTreeTest {

    public static List<String> getStringListFromFile(String fname) throws IOException {
        List<String> depList = new ArrayList<>();
        String content;
        content = new String(Files.readAllBytes(Paths.get(fname)));
        String[] listContent = content.split("\n");
        for (String row: listContent) {
            depList.add(row);
        }
        return depList;
    }

    @Test
    public void depTreeShouldReternDepsInDescendingOrderWhenModeIsD() throws IOException {
        String fname = DepTreeTest.class.getResource("baseDepList.txt").getFile();
        List<String> depList = getStringListFromFile(fname);
        DepTree dt = new DepTree();
        dt.fillTree(depList);
        List<String> sortedList = dt.getSorterDepStructureList("D");
        String expectedFname = DepTreeTest.class.getResource("descendingDepList.txt").getFile();
        List<String> expectedDepList = getStringListFromFile(expectedFname);
        assertEquals("".join("\n", expectedDepList), "".join("\n", sortedList));
    }

    @Test
    public void depTreeShouldReternDepsInAscendingOrderWhenModeIsA() throws IOException {
        String fname = DepTreeTest.class.getResource("baseDepList.txt").getFile();
        List<String> depList = getStringListFromFile(fname);
        DepTree dt = new DepTree();
        dt.fillTree(depList);
        List<String> sortedList = dt.getSorterDepStructureList("A");
        String expectedFname = DepTreeTest.class.getResource("ascendingDepList.txt").getFile();
        List<String> expectedDepList = getStringListFromFile(expectedFname);
        assertEquals("".join("\n", expectedDepList), "".join("\n", sortedList));
    }

}
