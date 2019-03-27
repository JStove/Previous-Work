import java.util.Scanner;
import java.util.Arrays;
class qn {

    static int[] x = new int[128];          // solution  From programming resourses on connex
    static boolean[] a = new boolean[128];  // row free?
    static boolean[] b = new boolean[128];  // / diag free?
    static boolean[] c = new boolean[128];  // \ diag free?
    static boolean[][] holes = new boolean[12][12];
    static int count = 0;
    static int N;

    static void HoleTest() {
        boolean hasHole = false;
        for (int i=0; i<N; ++i){
            if(holes[i][x[i]]){
                hasHole = true;
            }
        }
        if(!hasHole) ++count;
    }
    
    static void gen ( int col ) {   //From programming resourses on connex
        for ( int row = 0; row < N; ++row ) {
            if (a[row] && b[row+col] && c[row-col+N]) { 
                x[col] = row;
                a[row] = b[row+col] = c[row-col+N] = false;
                if (col < N-1) gen( col+1 ); else HoleTest(); 
                a[row] = b[row+col] = c[row-col+N] = true;
            }
        }      
    }

    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String temp[] = sc.nextLine().split(" ");
            N = Integer.parseInt(temp[0]);
            int H = Integer.parseInt(temp[1]);
            if(N == 0 && H == 0) break;
            for(int j = 0; j < H; j++){
                String hole[] = sc.nextLine().split(" ");
                holes[Integer.parseInt(hole[0])][Integer.parseInt(hole[1])] = true;
            }
            
            for (int i=0; i<2*N+2; ++i) a[i] = b[i] = c[i] = true;  //From programming resourses
            gen(0);
            System.out.println(count);
            count = 0;
            Arrays.fill(x, 0);
            Arrays.fill(a, false);
            Arrays.fill(b, false);
            Arrays.fill(c, false);
            for(int i = 0; i < holes.length ; i++) Arrays.fill(holes[i], false);
        }
    }
}