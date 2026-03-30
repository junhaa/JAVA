import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #28219 주유소
public class Main {

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer>[] edges = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int aIdx = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int bIdx = Integer.parseInt(st.nextToken()) - 1;
            edges[aIdx].add(bIdx);
            edges[bIdx].add(aIdx);
        }

        boolean[] visited = new boolean[N];
        new Main().calcGasStationCount(0, k, edges, visited);

        System.out.println(answer);
    }

    private int calcGasStationCount(int cur, int k, List<Integer>[] edges, boolean[] visited) {
        visited[cur] = true;

        int length1 = 0;
        int length2 = 0;

        for (int next : edges[cur]) {
            if(visited[next]) continue;
            int length = calcGasStationCount(next, k, edges, visited) + 1;
            if(length > length1) {
                length2 = length1;
                length1 = length;
            }
            else if(length > length2) {
                length2 = length;
            }
        }


        if(length1 + length2 >= k) {
            answer ++;
            return -1;
        }

        return length1;
    }
}
