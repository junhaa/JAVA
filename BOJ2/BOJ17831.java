import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #17831 대기업 승범이네
public class Main {

    private static final int NULL = -1;

    enum CurrentState {
        MENTOR,
        MENTEE,
        ;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] point = new int[N];
        List<Integer>[] children = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken()) - 1; // 0-based
            children[parent].add(i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[CurrentState.values().length][N];

        for (CurrentState currentState : CurrentState.values()) {
            Arrays.fill(dp[currentState.ordinal()], NULL);
        }

        int result = Math.max(
                main.getMaxCost(0, CurrentState.MENTOR, children, point, dp),
                main.getMaxCost(0, CurrentState.MENTEE, children, point, dp)
        );

        System.out.println(result);
    }

    private int getMaxCost(int cur, CurrentState currentState, List<Integer>[] children, int[] point, int[][] dp) {
        if (dp[currentState.ordinal()][cur] != NULL) {
            return dp[currentState.ordinal()][cur];
        }

        int maxSum = 0;

        if (currentState == CurrentState.MENTOR) {
            int curSum = 0;

            for (int child : children[cur]) {
                curSum += Math.max(
                        getMaxCost(child, CurrentState.MENTOR, children, point, dp),
                        getMaxCost(child, CurrentState.MENTEE, children, point, dp)
                );
            }

            for (int child : children[cur]) {
                curSum -= Math.max(
                        getMaxCost(child, CurrentState.MENTOR, children, point, dp),
                        getMaxCost(child, CurrentState.MENTEE, children, point, dp)
                );

                curSum += point[cur] * point[child];
                curSum += getMaxCost(child, CurrentState.MENTEE, children, point, dp);

                maxSum = Math.max(maxSum, curSum);

                curSum -= point[cur] * point[child];
                curSum -= getMaxCost(child, CurrentState.MENTEE, children, point, dp);

                curSum += Math.max(
                        getMaxCost(child, CurrentState.MENTOR, children, point, dp),
                        getMaxCost(child, CurrentState.MENTEE, children, point, dp)
                );
            }
        }

        if (currentState == CurrentState.MENTEE) {
            int curSum = 0;

            for (int child : children[cur]) {
                curSum += Math.max(
                        getMaxCost(child, CurrentState.MENTOR, children, point, dp),
                        getMaxCost(child, CurrentState.MENTEE, children, point, dp)
                );
            }

            maxSum = curSum;
        }

        return dp[currentState.ordinal()][cur] = maxSum;
    }
}
