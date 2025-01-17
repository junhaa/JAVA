import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #10282 해킹 
class Result{
    int count, totalCost;

    public Result(int count, int totalCost) {
        this.count = count;
        this.totalCost = totalCost;
    }
}

class Edge implements Comparable<Edge> {
    int node, weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o){
        return this.weight - o.weight;
    }
}
public class Main {
    private static int[] cost;
    private static List<Edge>[] edges;
    private static int n, d, c;

    private static final int MAX = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken()) - 1;

            edges = new ArrayList[n];
            cost = new int[n];
            Arrays.fill(cost, MAX);
            for(int i = 0 ; i < n ; i ++){
                edges[i] = new ArrayList<>();
            }
            for(int i = 0 ; i < d ; i ++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());

                edges[b].add(new Edge(a, s));
            }
            Result result = solve();
            sb.append(result.count).append(" ").append(result.totalCost).append("\n");
        }
        System.out.println(sb);
    }
    private static Result solve(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(c, 0));
        cost[c] = 0;

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(cost[cur.node] < cur.weight) continue;
            for(Edge next : edges[cur.node]){
                int nextCost = next.weight + cost[cur.node];
                if(cost[next.node] > nextCost){
                    cost[next.node] = nextCost;
                    pq.add(new Edge(next.node, nextCost));
                }
            }
        }

        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; i ++){
            if(cost[i] != MAX){
                count ++;
                max = Math.max(max, cost[i]);
            }
        }
        return new Result(count, max);
    }
}
