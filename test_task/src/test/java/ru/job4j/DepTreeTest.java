package ru.job4j;

import org.junit.Test;
import ru.jobj4j.DepTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepTreeTest {

    private static List<String> getStringListFromFile(String fname) throws IOException {
        List<String> depList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fname));

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            depList.add(line);
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
        assertEquals(String.join("\n", expectedDepList), String.join("\n", sortedList));
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
        assertEquals(String.join("\n", expectedDepList), String.join("\n", sortedList));
    }

}
