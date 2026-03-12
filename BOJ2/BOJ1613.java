import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1613 역사
public class Main {
    private static final int CAN_MOVE = 1;
    private static final int CANNOT_MOVE = (int)1e9;
    private static final int FIRST_EVENT_FIRST = -1;
    private static final int SECOND_EVENT_FIRST = 1;
    private static final int ORDER_UNKNOWN = 0;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] cost = new int[n][n];

        for(int i = 0 ; i < n ; i ++){
            Arrays.fill(cost[i], CANNOT_MOVE);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int end = Integer.parseInt(st.nextToken()) - 1; // 0-based

            cost[start][end] = CAN_MOVE;
        }

        main.calcCost(cost, n);

        StringBuilder answer = new StringBuilder();
        int s = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < s ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int end = Integer.parseInt(st.nextToken()) - 1; // 0-based
            answer.append(main.checkAnswer(start, end, cost)).append("\n");
        }

        System.out.println(answer);
    }

    private void calcCost(int[][] cost, int n) {
        for (int mid = 0; mid < n; mid++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    int curCost = Math.min(cost[start][end], cost[start][mid] + cost[mid][end]);
                    if(curCost >= CANNOT_MOVE) cost[start][end] = CANNOT_MOVE;
                    if(curCost < CANNOT_MOVE) cost[start][end] = CAN_MOVE;
                }
            }
        }
    }

    private int checkAnswer(int start, int end, int[][] cost) {
        if(cost[start][end] == CAN_MOVE) {
            return FIRST_EVENT_FIRST;
        }
        if(cost[end][start] == CAN_MOVE) {
            return SECOND_EVENT_FIRST;
        }
        return ORDER_UNKNOWN;
    }
}

