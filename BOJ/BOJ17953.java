import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #17953 디저트
public class Main {
    static int N, M;
    static int[][] cost, dp;
    static int recursive(int L, int last){
        if(L == N){
            return 0;
        }
        if(dp[last][L] != -1) return dp[last][L];
        int max = Integer.MIN_VALUE; // 최소값으로 초기화, 만족도는 자연수
        for(int i = 0 ; i < M ; i ++){
            if(L == 0)max = Math.max(recursive(L + 1, i) + cost[i][L] , max); // 첫 날의 만족감은 이전 주기의 마지막 날에 영향을 받지 않으므로
            else max = Math.max(recursive(L + 1, i) + (last == i ? cost[i][L] / 2 : cost[i][L]) , max);
        }
        return dp[last][L] = max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[M][N];
        dp = new int[M][N];

        for(int i = 0 ; i < M ; i ++){
            Arrays.fill(dp[i], -1); // 아직 탐색하지 않았으면 -1
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = Integer.MIN_VALUE;
        for(int i = 0 ; i < M ; i ++){
            answer = Math.max(answer, recursive(0, i));
        }
        System.out.println(answer);
    }
}
