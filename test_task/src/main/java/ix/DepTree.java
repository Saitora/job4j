package ix;

import java.util.*;

public class DepTree {

    private TreeMap<String, DepTree> innerMap = new TreeMap<>();

    public void fillTree(List<String> depList) {
        for(String row: depList) {
            addDepHier(Arrays.asList(row.split("\\\\")));
        }
    }

    private void addDepHier(List<String> depHier) {
        if (depHier.size() == 0) return;
        String dep = depHier.get(0);
        if (!innerMap.containsKey(dep)) {
            innerMap.put(dep, null);
        }
        depHier = depHier.subList(1, depHier.size());
        if (depHier.size() != 0) {
            if (innerMap.get(dep) == null) {
                innerMap.put(dep, new DepTree());
            }
            innerMap.get(dep).addDepHier(depHier);
        }
    }

    public List<String> getSorterDepStructureList(String mode) {
        List<String> sorterDepStructure = new ArrayList<>();
        getSubTreeDepList("", sorterDepStructure, mode);
        return sorterDepStructure;
    }

    private void getSubTreeDepList(String prefix, List<String> sortedDepStructure, String mode) {
        NavigableSet<String> keySet;
        if (mode.equals("D")) {
            keySet = innerMap.descendingKeySet();
        } else if (mode.equals("A")) {
            keySet = innerMap.navigableKeySet();
        } else {
            return;
        }

        for(String key: keySet) {
            if (prefix.equals("")) {
                sortedDepStructure.add(key);
            } else {
                sortedDepStructure.add(prefix + "\\" + key);
            }
            DepTree child = innerMap.get(key);
            if (child != null) {
                child.getSubTreeDepList(prefix.equals("") ? key : (prefix + "\\" + key), sortedDepStructure, mode);
            }
        }
    }

}
