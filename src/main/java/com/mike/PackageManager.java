package com.mike;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael Brennan on 8/27/15.
 * Assumes at least one dependency
 * Assumes no duplicate packages
 *
 */

public class PackageManager{

    final Pattern p = Pattern.compile(".*:\\s*(.*)");
    Matcher m;
    String dependant;
    String dependency;
    String[] split;

    public String[] orderPacks(String[] input){

        if (isCyclic(input)){
            return null;
        }

        List<String> output = new ArrayList<>();
        String single;
        int index;

        for (String both : input) {
            m = p.matcher(both);
            if (m.find() && !m.group(1).equals("")) {

                split = both.split("\\:"); //split items dependency is index 1
                dependant = split[0].replaceAll("\\s", "");
                dependency = split[1].replaceAll("\\s", "");

                if (output.isEmpty()) {
                    output.add(both);
                    continue;
                } else {
                    for (String outPutString : output) {
                        if (outPutString.contains(dependency)  || outPutString.contains(dependant)) {
                            index = output.indexOf(outPutString);
                            output.add(index, both);
                            break;
                        }
                    }
                    output.add(both);
                }
            }else {
                //single item, need to check to see if output contains this item, if so then move above item in index
                split = both.split("\\:"); //split items dependency is index 1
                single = split[0].replaceAll("\\s", "");
                if (output.isEmpty()) {
                    output.add(both);
                    continue;
                }else{
                    for(String outPutString : output){
                        if (outPutString.contains(single)){
                            index = output.indexOf(outPutString);
                            output.add(index, both);
                            break;
                        }
                    }
                    output.add(both);
                }
            }
        }

        LinkedHashSet<String> set  = new LinkedHashSet<>(output);
        ArrayList<String> uniqueList = new ArrayList<>();
        getUniqueList(set, uniqueList);
        set  = new LinkedHashSet<>(uniqueList);

        return set.toArray(new String[set.size()]);
    }

    private void getUniqueList(LinkedHashSet<String> set, ArrayList<String> uniqueList) {

        for(String s : set){
            m = p.matcher(s);
            split = s.split("\\:"); //split items dependency is index 1
            dependant = split[0].replaceAll("\\s", "");
            if(m.find() && !m.group(1).equals("")){
                dependency = split[1].replaceAll("\\s", "");
                uniqueList.add(dependency);
            }
            uniqueList.add(dependant);
        }
    }

    public boolean isCyclic(String[] input){

        HashMap<String, String> depMap = new HashMap<>();

        for(String s : input){
            m = p.matcher(s);
            if (m.find() && !m.group(1).equals("")) {
                split = s.split("\\:"); //split items dependency is index 1
                dependant = split[0].replaceAll("\\s", "");
                dependency = split[1].replaceAll("\\s", "");
                depMap.put(dependant, dependency);
            }
        }

        String value;
        String nextOne;

        for (String key : depMap.keySet()){
            value = depMap.get(key);
            nextOne = depMap.get(value);
            if(nextOne != null){
                for(int i = 0; i < depMap.size(); i++){
                    nextOne = depMap.get(nextOne);
                    if (depMap.get(nextOne) != null && nextOne.equals(key)){
                        return true;
                    }
                }
            }

        }
        return false;
    }

}
