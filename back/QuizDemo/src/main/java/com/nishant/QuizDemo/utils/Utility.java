package com.nishant.QuizDemo.utils;

import java.util.*; 
import java.lang.*;

public class Utility {
	
	   // function to sort hashmap by values 
    public static HashMap<String, Long> sortByValue(HashMap<String, Long> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Long> > list = 
               new LinkedList<Map.Entry<String, Long> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Long> >() { 
            public int compare(Map.Entry<String, Long> o1,  
                               Map.Entry<String, Long> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Long> temp = new LinkedHashMap<String, Long>(); 
        for (Map.Entry<String, Long> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 

}
