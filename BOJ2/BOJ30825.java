import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #30825 건공펀치 등차수열
public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(main.solve(N, K, arr));
    }

    private long solve(int N, int K, long[] arr) {
        int maxIdx = getMaxValueIdx(N, K, arr);

        return calcMinPunchCount(maxIdx, N, K, arr);
    }

    private long calcMinPunchCount(int maxIdx, int N, int K, long[] arr) {
        long startValue = arr[maxIdx] - 1L * K * maxIdx;
        long punchCount = 0;

        for (int i = 0; i < N; i++) {
            punchCount += startValue + 1L * K * i - arr[i];
        }

        return punchCount;
    }

    private int getMaxValueIdx(int N, int K, long[] arr) {
        long max = Long.MIN_VALUE;
        int maxIdx = -1;

        for (int i = 0; i < N; i++) {
            long cur = arr[i];
            long balanced = cur - 1L * K * i;

            if (balanced > max) {
                max = balanced;
                maxIdx = i;
            }
        }

        return maxIdx;
    }
}
