import java.util.*;

class Q2
{

    public static void deduplicatePartial(ArrayList<Integer> vec)
    {

        //temp Vector to store count of each index
        HashMap<Integer,Integer> numCounts = new HashMap<Integer,Integer>();
        ArrayList<Integer> remove_indexes = new ArrayList<Integer>();

        for(Integer i: vec){

            Integer amt = numCounts.get(int);
            // Place all values in map, add count if duplicate
            if(amt == Null){
                numCounts.put(int, 1);
            }
            else if(amt > 2) {
                remove_indexes.add(vec.indexOf(i));
            }
            else {
                numCounts.put(int, (amt+1);
            }
        }
        // Removes value at specified index
        for(Integer i: remove_indexes) {
            vec.remove(i);
        }
    }
}

class Rextester
{
    public static void main(String[] args)
    {
       ArrayList<Integer> input =
           new ArrayList<Integer>(Arrays.asList(0, 10, 10, 100, -1, -1, -1, 5, 5, 5, 8, 9, 10, 10, 3, 9, 7, 0, 0, 0));

       Q2.deduplicatePartial(input);
    }
}
