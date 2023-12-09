import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #16986 인싸들의 가위바위보
public class Main {
    static int N, K;

    static int[][] check;
    static int[][] arr = new int[2][20];
    static boolean[] jiwoo;
    static int[] winCnt = new int[3], idx = new int[3];

    static void backTracking(int a, int b, int L){ // 0 -> 지우, 1 -> a가 경희, 2 -> b가 민우
        for(int i = 0 ; i < 3 ; i ++){
            if(winCnt[i] == K){
                if(i == 0) {
                    System.out.println("1");
                    System.exit(0);
                }
                else return;
            }
        }
        if(L == 20){
            return;
        }
        if(a == 0){
            for(int i = 0 ; i < N ; i ++){
                if(jiwoo[i]) continue;
                int ah = i;
                int bh = arr[b - 1][idx[b] ++];
                jiwoo[i] = true;
                if(check[ah][bh] == 2){
                    winCnt[a] ++;
                    backTracking(0, 3 - b, L + 1);
                    winCnt[a] --;
                }
                else{
                    winCnt[b] ++;
                    backTracking(b, 3 - b, L + 1);
                    winCnt[b] --;
                }
                idx[b] --;
                jiwoo[i] = false;
            }
        }
        else if(b == 0) {
            for (int i = 0; i < N; i++) {
                if (jiwoo[i]) continue;
                int ah = arr[a - 1][idx[a] ++];
                int bh = i;
                jiwoo[i] = true;
                if (check[ah][bh] > 0) {
                    winCnt[a] ++;
                    backTracking(a, 3 - a, L + 1);
                    winCnt[a] --;
                } else {
                    winCnt[b] ++;
                    backTracking(0, 3 - a, L + 1);
                    winCnt[b] --;
                }
                idx[a] --;
                jiwoo[i] = false;
            }
        }
        else{
            int ah = arr[a - 1][idx[a] ++];
            int bh = arr[b - 1][idx[b] ++];
            if(check[ah][bh] == 2){
                winCnt[a] ++;
                backTracking(a, 0, L + 1);
                winCnt[a] --;
            }
            else if(check[ah][bh] == 1){
                if(a > b){
                    winCnt[a] ++;
                    backTracking(a, 0, L + 1);
                    winCnt[a] --;
                }
                else{
                    winCnt[b] ++;
                    backTracking(b, 0, L + 1);
                    winCnt[b] --;
                }
            }
            else{
                winCnt[b] ++;
                backTracking(b, 0, L + 1);
                winCnt[b] --;
            }
            idx[a] --;
            idx[b] --;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        check = new int[N][N];
        jiwoo = new boolean[N];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                check[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 20 ; i ++){
            arr[0][i] = Integer.parseInt(st.nextToken()) - 1;
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 20 ; i ++){
            arr[1][i] = Integer.parseInt(st.nextToken()) - 1;
        }
        for(int i = 0 ; i < N ; i ++){
            backTracking(0, 1, 0);
        }
        System.out.println(0);
    }
}
