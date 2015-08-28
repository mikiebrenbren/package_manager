package com.mike;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by michaelbrennan on 8/27/15.
 */
public class PackageManager{

    //separate everything with a dependency with things that do not have a dependency
    //into different data structures.
    public String[] orderPacks(String[] input){

        List<String> output = new ArrayList<>();
        Pattern p = Pattern.compile(".*:\\s*(.*)");
        Matcher m;
        String[] split;
        String dependant;
        String dependency;
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
        getUniqueList(p, set, uniqueList);

        LinkedHashSet<String> set1  = new LinkedHashSet<>(uniqueList);

        return set1.toArray(new String[set1.size()]);
    }

    private void getUniqueList(Pattern p, LinkedHashSet<String> set, ArrayList<String> uniqueList) {
        Matcher m;
        String[] split;
        String dependant;
        String dependency;
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


}
