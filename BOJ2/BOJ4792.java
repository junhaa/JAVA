import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #4792 레드 블루 스패닝 트리
class Edge{
    int weight, endNode;

    public Edge(int weight, int endNode) {
        this.weight = weight;
        this.endNode = endNode;
    }
}
public class Main {
    static int n = -1, m = -1, k = -1;
    static List<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0 && k == 0) {
                break;
            }

            edges = new ArrayList[n];
            for(int i = 0 ; i < n ; i ++){
                edges[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < m ; i ++){
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;

                edges[start].add(new Edge(c == 'B' ? 1 : 0, end));
                edges[end].add(new Edge(c == 'B' ? 1 : 0, start));
            }

            int bmst = mst(false, n);
            int rmst = mst(true, n);

            if(bmst < k || n - 1 - rmst > k) sb.append(0).append("\n");
            else sb.append(1).append("\n");
        }
        System.out.println(sb);
    }

    // flag true = red, false = blue
    private static int mst(boolean flag, int n){
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(flag ? (Edge e) -> e.weight : (Edge e) -> 1 - e.weight));
        boolean[] visited = new boolean[n];
        int sum = 0;
        pq.offer(new Edge(flag ? 1 : 0, 0));
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.endNode]) continue;

            visited[cur.endNode] = true;
            for(Edge e : edges[cur.endNode]){
                pq.offer(e);
            }
            sum += flag ? 1 - cur.weight : cur.weight;
        }

        return sum;
    }
}
