package com.mike;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by michaelbrennan on 8/27/15.
 */
public class PackageManager{

    //separate everything with a dependency with things that do not have a dependency
    //into different data structures.
    public String[] orderPacks(String[] packages){

        //pattern to find what is after the colon
        Pattern p = Pattern.compile(".*:\\s*(.*)");
        Matcher m;
        //array to hold a package with a dependency
        String[] split;
        List<String> output = new ArrayList<String>();
        for(String s : packages){
            m = p.matcher(s);
            if(m.find() && !m.group(1).equals("")){
                split = s.split("\\:");
                output.add(0, split[0].replaceAll("\\s", ""));
                output.add(output.size()-1, split[split.length-1].replaceAll("\\s", ""));
                for (String sp : output){
                    System.out.println(sp);
                }
            }
        }

        return output.toArray(new String[output.size()]);
    }
}
