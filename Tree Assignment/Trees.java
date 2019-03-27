// V00873161 Browell, Jesse
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
class Kitten{
    public static int getRandom(int[] use) {
        int rand = new Random().nextInt(use.length);
        return use[rand];
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> bad = new ArrayList<Integer>();
        ArrayList<Integer> beg = new ArrayList<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int start = Integer.parseInt(sc.nextLine());
        int[][] tree = new int[101][];
        int treeLen = 0;
        int end = 0;
        while(sc.hasNextLine()){
            String[] temp= sc.nextLine().split(" ");
            if(treeLen == 0){
                end = Integer.parseInt(temp[0]);
            }
            if(Integer.parseInt(temp[0]) >= 0){
                beg.add(Integer.parseInt(temp[0]));
                tree[Integer.parseInt(temp[0])] = new int[temp.length-1];
                for(int i = 0; i < temp.length-1; i++){
                    if(Integer.parseInt(temp[i+1]) != 0){ 
                        tree[Integer.parseInt(temp[0])][i] = Integer.parseInt(temp[i+1]);
                    }
                }
                treeLen++;
            }
        }
        boolean wrongend = true;
        while(wrongend){
            boolean test = false;
            for(int i = 0; i < beg.size(); i++){
                for(int k = 0; k < tree[beg.get(i)].length; k++){
                    if(end == tree[beg.get(i)][k]){
                        test = true;
                        end = beg.get(i);
                    }
                }
            }
            if(!test){
                wrongend = false;
            }
        }       
        
        boolean stuck = true;
        while(stuck){
            int current = end;
            int[] path = new int[101];
            int num = 0;
            
            try{
                while(current != start){
                    path[num] = current;
                    num++;
                    boolean test = false;
                    do{ 
                        current = getRandom(tree[current]);
                        for(int i = 0; i < bad.size(); i++){
                            if(current == bad.get(i)){
                                test = true;
                            }
                        }
                    }while(test == true);
                }
                if(current == start){
                    stuck = false;
                    path[num] = current;
                    ans.add(0, path[0]);
                    for(int i = 1; i < path.length; i++){
                        if(path[i] == 0){
                            i = path.length;
                        }else{
                            ans.add(0, path[i]);
                        }
                    }
                }
            }catch(Exception e){    
                bad.add(current);
            }   
        }
        System.out.print(ans.get(0));
        for(int i = 1; i < ans.size(); i++){
            System.out.print(" " + ans.get(i));
        }
    }
}