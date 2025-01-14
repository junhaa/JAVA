import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #14284 간선 이어가기 2 
class Edge implements Comparable<Edge> {
    int end;
    long weight;

    public Edge(int end, long weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o){
        if(this.weight > o.weight) return 1;
        else if(this.weight < o.weight) return -1;
        else return 0;
    }
}
public class Main {
    private static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n];
        for(int i = 0 ; i < n ; i ++){
            edges[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            edges[start].add(new Edge(end, weight));
            edges[end].add(new Edge(start, weight));
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(dijkstra(s, e, n));
    }

    private static long dijkstra(int startNode, int endNode, int n){
        PriorityQueue<Edge> pQ = new PriorityQueue();

        long[] minCost = new long[n];
        Arrays.fill(minCost, (int)1e9);

        minCost[startNode] = 0;
        pQ.offer(new Edge(startNode, 0));

        while(!pQ.isEmpty()){
            Edge cur = pQ.poll();
            if(cur.weight > minCost[cur.end]) continue;
            for(Edge next : edges[cur.end]){
                long nextCost = minCost[cur.end] + next.weight;
                if(nextCost < minCost[next.end]){
                    minCost[next.end] = nextCost;
                    pQ.offer(new Edge(next.end, nextCost));
                }
            }
        }
        return minCost[endNode];
    }
}
