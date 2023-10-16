import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #1823 수확
public class Main {
    static int[] arr;
    static int[][] dp;
    static int N;

    static int recursive(int start, int end, int cnt) {
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        if (cnt == N - 1) {
            return dp[start][end] = (cnt + 1) * arr[start];
        } else {
            return dp[start][end] = Math.max((cnt + 1) * arr[start] + recursive(start + 1, end, cnt + 1), (cnt + 1) * arr[end] + recursive(start, end - 1, cnt + 1));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int answer = recursive(0, N - 1, 0);
        System.out.println(answer);
    }
}
