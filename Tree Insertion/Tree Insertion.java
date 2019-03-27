import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigInteger;

class tree{
    public static BigInteger fact(int a){
        BigInteger res = BigInteger.valueOf(1);
        for(int i = 1; i <= a; i++){
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
    
    public static BigInteger choose(int a, int b){
        BigInteger one = fact(b);
        BigInteger two = one.multiply(fact(a-b));
        BigInteger thr = fact(a).divide(two);
        
        return thr;
    }
    
    public static BigInteger countOrder(ArrayList<Integer> bst){
        if(bst.size() == 0){
            return BigInteger.valueOf(1);
        }else{
            ArrayList<Integer> left = new ArrayList<Integer>();
            ArrayList<Integer> right = new ArrayList<Integer>();
            for(int test : bst.subList(1, bst.size())){
                if(test >= bst.get(0)){
                    right.add(test);
                }else{
                    left.add(test);
                }
            }
            
            BigInteger a = choose((left.size() + right.size()), right.size());
            BigInteger b = a.multiply(countOrder(left));
            BigInteger c = b.multiply(countOrder(right));
            
            
            return c;
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            int size = Integer.parseInt(sc.nextLine());
            if(size != 0){
                ArrayList<Integer> bst = new ArrayList<Integer>();
                for(String temp : sc.nextLine().split(" ")){
                    bst.add(Integer.parseInt(temp));
                }
                System.out.println(countOrder(bst));
            }
        }
    }
}