//V00873161
import java.util.TreeMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

class Rest{
    public static void main(String[] args){
        TreeMap<String, TreeSet<String>> m = new TreeMap<String, TreeSet<String>>();
        Scanner sc = new Scanner(System.in);
        String firstline = sc.nextLine();
        while(sc.hasNextLine()){
            String[] temp= sc.nextLine().split(" ");
            if(temp.length == 1){
                m.forEach((k,v)->{
                    System.out.print(k + " ");
                    Iterator<String> it = v.iterator();
                    while (it.hasNext()){ 
                        System.out.print(it.next() + " "); 
                    } 


                    System.out.println();
                });
                m.clear();
                System.out.println();
            }           
            else if(temp.length > 1){
                for(int i = 1; i < temp.length; i++){
                    TreeSet<String> ph;
                    try{
                        ph = m.get(temp[i]);
                        ph.add(temp[0]);
                    }catch(Exception e){
                        ph = new TreeSet<String>();
                        ph.add(temp[0]);
                    }
                    m.put(temp[i], ph);
                }
            }
        }
    }
}