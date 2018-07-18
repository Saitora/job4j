package ix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class DepTree {

    private TreeMap<String, DepTreeNode> innerMap = new TreeMap<>();

    public void fillTree(List<String> depList) {
        for(String row: depList) {
            String processed = row.substring(1, row.length()-1);
            addDepHier(Arrays.asList(processed.split("\\\\")));
        }
    }

    public void addDepHier(List<String> depHier) {
        if (depHier.size() == 0) return;
        String dep = depHier.get(0);
        if (!innerMap.containsKey(dep)) {
            innerMap.put(dep, null);
        }
        depHier.remove(0);
        if (depHier.size() != 0) {
            if (innerMap.get(dep) == null) {
                innerMap.put(dep, new DepTreeNode());
            }
            innerMap.get(dep).addDepHier(depHier);
        }
    }

    public List<String> getSorterDepStructureList(String mode) {
        List<String> sorterDepStructure = new ArrayList<>();
        boolean descending = (mode == "D") ? true : false;
        if (descending) {
            for(String key: innerMap.descendingKeySet()) {
                sorterDepStructure.add(key);
                DepTreeNode child = innerMap.get(key);
                if (child != null) {
                    child.getSubTreeDepList(key, sorterDepStructure, descending);
                }
            }
        }
        return sorterDepStructure;
    }

    public class DepTreeNode {

        private TreeMap<String, DepTreeNode> nodeInnerMap = new TreeMap<>();

        public void addDepHier(List<String> depHier) {
            String dep = depHier.get(0);
            if (!innerMap.containsKey(dep)) {
                innerMap.put(dep, null);
            }
            depHier.remove(0);
            if (depHier.size() != 0) {
                if (innerMap.get(dep) == null) {
                    innerMap.put(dep, new DepTreeNode());
                }
                innerMap.get(dep).addDepHier(depHier);
            }
        }

        public void getSubTreeDepList(String prefix, List<String> sortedDepStructure, boolean descending) {
            if (descending) {
                for(String key: innerMap.descendingKeySet()) {
                    sortedDepStructure.add(prefix + "\\" + key);
                    DepTreeNode child = innerMap.get(key);
                    if (child != null) {
                        child.getSubTreeDepList(key, sortedDepStructure, descending);
                    }
                }
            }
        }

    }

}
