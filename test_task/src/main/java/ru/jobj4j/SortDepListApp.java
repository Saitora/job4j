package ru.jobj4j;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SortDepListApp {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Error! Wrong arguments number.");
            printHelp();
            return;
        }

        if (!(args[2].equals("A") || args[2].equals("D"))) {
            System.out.println("Error! Wrong last argument.");
            printHelp();
            return;
        }

        List<String> depList;
        try {
            depList = getDepListFromFile(args[0]);
        } catch (IOException e) {
            System.out.println("Error! Input file not found!");
            printHelp();
            return;
        }

        List<String> sortedDepList;
        sortedDepList = getSortedDepList(depList, args[2]);
        System.out.println(String.join("\n", sortedDepList));
    }

    private static void printHelp() {
        System.out.println(
                "Usage: sort_deps [input_file] [output_file] [-A|-D]\n"
                        + "    Options\n"
                        + "      -A    Ascending order\n"
                        + "      -D    Descending order"
        );
    }

    public static List<String> getDepListFromFile(String fname) throws IOException {
        List<String> depList = new ArrayList<>();
        String content;
        content = new String(Files.readAllBytes(Paths.get(fname)));
        String[] listContent = content.split("\n");
        for (String row: listContent) {
            depList.add(row);
        }
        return depList;
    }

    private static List<String> getSortedDepList(List<String> unsortedDepList, String mode) {
        List<String> sortedDepList;

        DepTree depTree = new DepTree();
        depTree.fillTree(unsortedDepList);
        sortedDepList = depTree.getSorterDepStructureList(mode);

        return sortedDepList;

    }
}
