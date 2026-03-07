import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #18126 너구리 구구
class Edge {
    int idx;
    long distance;

    Edge(int idx, long distance) {
        this.idx = idx;
        this.distance = distance;
    }
}
public class Main {
    private static long max = Long.MIN_VALUE;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Edge>[] edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        visited = new boolean[N];
        StringTokenizer st;
        for(int i = 0 ; i < N - 1 ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());
            edges[start].add(new Edge(end, distance));
            edges[end].add(new Edge(start, distance));
        }

        main.checkAllRoute(new Edge(0, 0), edges);

        System.out.println(max);
    }

    private void checkAllRoute(Edge cur, List<Edge>[] edges){
        visited[cur.idx] = true;
        max = Math.max(max, cur.distance);
        for(Edge next : edges[cur.idx]){
            if(visited[next.idx]) continue;
            checkAllRoute(new Edge(next.idx, cur.distance + next.distance), edges);
        }
        visited[cur.idx] = false;
    }
}
