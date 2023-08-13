import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #28447 마라탕 재료 고르기
public class Main {

    static boolean[] ch;
    static int[] seq;
    static int[][] arr;
    static int N, K;

    static int getSum(){
        int sum = 0;
        for(int i = 0 ; i < K ; i ++){
            for(int j = i ; j < K ; j ++) {
                sum += arr[seq[i]][seq[j]];
            }
        }
        return sum;
    }
    static int DFS(int L){
        int max = Integer.MIN_VALUE;
        if(L == K){
            return getSum();
        }
        for(int i = 0 ; i < N ; i ++){
            if(ch[i]) continue;
            seq[L] = i;
            ch[i] = true;
            max = Math.max(max, DFS(L + 1));
            ch[i] = false;
        }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        seq = new int[K];
        ch = new boolean[N];
        arr = new int[N][N];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(DFS(0));
    }
}
