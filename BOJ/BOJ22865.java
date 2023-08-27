import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #22865 가장 먼 곳 
class Edge implements Comparable<Edge> {
    int node, cost;
    public Edge(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge O){
        return this.cost - O.cost;
    }
}
public class Main {
    static int N;
    static ArrayList<Edge>[] list;
    static void dijkstra(int start, int[] dis){
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(start, 0));
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.node]) continue;
            for(Edge next : list[tmp.node]){
                if(tmp.cost + next.cost < dis[next.node]){
                    dis[next.node] = tmp.cost + next.cost;
                    pQ.offer(new Edge(next.node, dis[next.node]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList[N];
        int A = Integer.parseInt(st.nextToken()) - 1;
        int B = Integer.parseInt(st.nextToken()) - 1;
        int C = Integer.parseInt(st.nextToken()) - 1;
        for(int i = 0 ; i < N ; i ++){
            list[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Edge(end, cost));
            list[end].add(new Edge(start, cost));
        }
        int[] disA = new int[N];
        int[] disB = new int[N];
        int[] disC = new int[N];

        dijkstra(A, disA);
        dijkstra(B, disB);
        dijkstra(C, disC);
        int maxNode = -1;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            disA[i] =  Math.min(Math.min(i == B ? Integer.MAX_VALUE : disB[i], i == C ? Integer.MAX_VALUE : disC[i]), i == A ? Integer.MAX_VALUE : disA[i]);
            if(max < disA[i]){
                maxNode = i;
                max = Math.max(max, disA[i]);
            }
        }
        System.out.println(maxNode + 1);
    }
}
