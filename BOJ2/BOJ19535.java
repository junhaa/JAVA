import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// BOJ #19535 ㄷㄷㄷㅈ
public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] edges = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int v = Integer.parseInt(st.nextToken()) - 1;
            edges[u].add(v);
            edges[v].add(u);
        }

        System.out.println(main.solve(N, edges));
    }

    private String solve(int N, List<Integer>[] edges) {

        long DCount = 0;
        long GCount = 0;

        for (int i = 0; i < N; i++) {
            DCount += calcDTreeCount(i, edges);
            GCount += calcGTreeCount(edges[i].size());
        }

        return calcResult(DCount, GCount);
    }

    private long calcDTreeCount(int idx, List<Integer>[] edges) {
        long count = 0;
        List<Integer> nextIdxes = edges[idx].stream()
                .filter(nextIdx -> nextIdx > idx)
                .collect(Collectors.toList());

        for (int nextIdx : nextIdxes) {
            count += 1L * (edges[idx].size() - 1) * (edges[nextIdx].size() - 1);
        }

        return count;
    }

    private long calcGTreeCount(int childNodeCount) {
        return combination(childNodeCount, 3);
    }

    private long combination(int n, int r) {
        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (n - i + 1) / i;
        }

        return result;
    }

    private String calcResult(long DCount, long GCount) {
        if (DCount == GCount * 3) {
            return "DUDUDUNGA";
        }
        if (DCount >= GCount * 3) {
            return "D";
        }
        return "G";
    }
}
