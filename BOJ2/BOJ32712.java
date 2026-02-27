import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #32712 다이얼 룰렛
public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(T.solve(n, k, a));
    }

    private long solve(int n, int k, int[] a) {
        long[] aSum = new long[n + 1];
        long[] aReverseSum = new long[n + 1];
        long maxScore = Long.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            aSum[i] = aSum[i - 1] + a[i - 1];
            aReverseSum[n - i] = aReverseSum[n - i + 1] + a[n - i];
        }

        for(int i = 0 ; i < n ; i ++){
            maxScore = Math.max(maxScore, calcDirectMoveSum(i, aSum, k));
            maxScore = Math.max(maxScore, calcReverseMoveSum(i, aReverseSum, k));
        }

        return maxScore;
    }

    private long calcDirectMoveSum(int idx, long[] aSum, int k) {
        int move = idx + 1;
        if (move > k) {
            return Long.MIN_VALUE;
        }
        long score = aSum[idx + 1];
        score += (k - move) * (aSum[idx + 1] - aSum[idx]);

        return score;
    }

    private long calcReverseMoveSum(int idx, long[] aReverseSum, int k) {
        int move = aReverseSum.length - 1 - idx;
        if (move > k) {
            return Long.MIN_VALUE;
        }

        long score = aReverseSum[idx];
        score += (k - move) * (aReverseSum[idx] - aReverseSum[idx + 1]);

        return score;
    }
}
