import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #19542 전단지 돌리기
public class Main {
    private static int move = 0;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Integer>[] edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int end = Integer.parseInt(st.nextToken()) - 1;
            edges[start].add(end);
            edges[end].add(start);
        }

        System.out.println(main.solve(S, D, edges));
    }

    private int solve(int S, int D, List<Integer>[] edges) {
        boolean[] visited = new boolean[edges.length];
        visited[S - 1] = true;
        for(int next : edges[S - 1]) {
            dfs(next, D, edges, 2, visited);
        }

        return move;
    }

    private int dfs(int cur, int D, List<Integer>[] edges, int level, boolean[] visited) {
        visited[cur] = true;
        move ++;
        int maxLevel = Integer.MIN_VALUE;
        for (int next : edges[cur]) {
            if (visited[next]) {
                continue;
            }
            maxLevel = Math.max(maxLevel, dfs(next, D, edges, level + 1, visited));
        }

        if (maxLevel < level + D && D != 0) {
            move --;
            return Math.max(maxLevel, level);
        }

        move ++;
        return Math.max(maxLevel, level);
    }
}
