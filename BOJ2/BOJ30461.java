import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #30461 낚시
class Query {
    int n, m;

    public Query(int n, int m) {
        this.n = n;
        this.m = m;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken()); // 캐스팅 횟수

        int[][] fishes = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                fishes[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<Query> queries = new ArrayList<>();

        for(int i = 0 ; i < Q ; i ++){
            st = new StringTokenizer(br.readLine());
            queries.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        System.out.println(main.solve(N, M, queries, fishes));
    }

    private String solve(int N, int M, List<Query> queries, int[][] fishes) {
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = (isInBound(i - 1, j) ? dp[i - 1][j] : 0) + fishes[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] += isInBound(i - 1, j - 1) ? dp[i - 1][j - 1] : 0;
            }
        }

        for (Query query : queries) {
            sb.append(dp[query.n - 1][query.m - 1]).append("\n"); // 0-based
        }

        return sb.toString();
    }

    private boolean isInBound(int n, int m) {
        return n >= 0 && m >= 0;
    }
}
