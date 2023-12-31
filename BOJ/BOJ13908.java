import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #13908 비밀번호 
public class Main {
    static int[][] combi;
    static int N, M;

    static void initCombi(){
        combi = new int[M + 1][M + 1];
        for(int n = 1 ; n <= M ; n ++){
            for(int r = 0 ; r <= n ; r ++){
                if(r == 0 || r == n) combi[n][r] = 1;
                else{
                    combi[n][r] = combi[n - 1][r - 1] + combi[n - 1][r];
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if(M == 0) {
            System.out.println((int)Math.pow(10, N));
            return;
        }
        initCombi();
        int answer = 0;
        for(int i = 0 ; i <= M ; i ++){
            answer += (int)Math.pow(-1, i) * Math.pow(10 - i, N) * combi[M][i];
        }
        System.out.println(answer);
    }
}
