import java.util.Scanner;
import java.util.TreeSet;

class tri{
    static class tnode{
        tnode[] prev = new tnode[26];
        boolean isleaf;
        
        public tnode(){
            isleaf = false;
            for(int i = 0; i < 26; i++){
                prev[i] = null;
            }
        }
    }
    
    static TreeSet<String> founds = new TreeSet<String>();
    
    static void place(tnode root, String s){
        tnode c = root;
        int n = s.length();
        
        for(int i = 0;i < n; i++){
            int t = s.charAt(i) - 'A';
            if(c.prev[t] == null){
                c.prev[t] = new tnode();
            }
            c = c.prev[t];
        }
        c.isleaf = true;
    }
    
    static boolean test(int a, int b, boolean vboard[][]){
        
        return a >= 0&& a < 4 && b >= 0 && b < 4 && !vboard[a][b];
    }
    
    static void search(tnode root, char board[][], int a, int b, boolean vboard[][], String found){
        if(root.isleaf){
            founds.add(found);
        }
        
        if(test(a, b, vboard)){
            vboard[a][b] = true;
            for(int i = 0; i < 26; i++){
                if(root.prev[i] != null){
                    char c = (char)(i+'A');
                    
                    if(test(a+1, b+1, vboard) && board[a+1][b+1]==c){
                        search(root.prev[i], board, a+1, b+1, vboard, found+c);
                    }
                    if(test(a, b+1, vboard) && board[a][b+1]==c){
                        search(root.prev[i], board, a, b+1, vboard, found+c);
                    }
                    if(test(a-1, b+1, vboard) && board[a-1][b+1]==c){
                        search(root.prev[i], board, a-1, b+1, vboard, found+c);
                    }               
                    if(test(a+1, b, vboard) && board[a+1][b]==c){
                        search(root.prev[i], board, a+1, b, vboard, found+c);
                    }
                    if(test(a+1, b-1, vboard) && board[a+1][b-1]==c){
                        search(root.prev[i], board, a+1, b-1, vboard, found+c);
                    }
                    if(test(a, b-1, vboard) && board[a][b-1]==c){
                        search(root.prev[i], board, a, b-1, vboard, found+c);
                    }
                    if(test(a-1, b-1, vboard) && board[a-1][b-1]==c){
                        search(root.prev[i], board, a-1, b-1, vboard, found+c);
                    }
                    if(test(a-1, b, vboard) && board[a-1][b]==c){
                        search(root.prev[i], board, a-1, b, vboard, found+c);
                    }
                }
            }
            vboard[a][b] = false;
        }
    }
        
    static void find(char board[][], tnode root){
        boolean vboard[][] = new boolean[4][4];
        tnode c = root;
        String found = "";
        
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                if(c.prev[board[a][b] - 'A'] != null){
                    found = found+board[a][b];
                    search(c.prev[board[a][b] - 'A'], board, a, b, vboard, found);
                    found = "";
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int dwords = Integer.parseInt(sc.nextLine());
        tnode root = new tnode();
        for(int i = 0; i < dwords; i++){
            place(root, sc.nextLine());
        }
        String a = sc.nextLine();
        int n = Integer.parseInt(sc.nextLine());
        for(int j = 0; j < n; j++){
            char[][] board = new char[4][4];
            board[0] = sc.nextLine().toCharArray();
            board[1] = sc.nextLine().toCharArray();
            board[2] = sc.nextLine().toCharArray();
            board[3] = sc.nextLine().toCharArray();
            find(board, root);
            
            String largest = "";
            for(String c : founds){
                if(largest.length() < c.length()){
                    largest = c;
                }
            }
            int points = 0;
            for(String t : founds){
                if(t.length() <= 2){
                    points += 0;
                }else if(t.length() <= 4){
                    points += 1;
                }else if(t.length() == 5){
                    points += 2;
                }else if(t.length() == 6){
                    points += 3;
                }else if(t.length() == 7){
                    points += 5;
                }else if(t.length() >= 8){
                    points += 11;
                }
            }
            System.out.println(points + " " + largest + " " + founds.size());

            founds.clear();
            try{
                String blanks = sc.nextLine();
            }catch(Exception e){}
        }
    }
}