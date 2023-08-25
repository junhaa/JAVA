import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #16681 등산 
class Edge implements Comparable<Edge>{
    int node;
    long cost;
    public Edge(int node, long cost){
        this.node = node;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o){
        if(this.cost - o.cost < 0) return -1;
        else if(this.cost == o.cost) return 0;
        else return 1;
    }
}
public class Main {
    static ArrayList<Edge>[] list;
    static int[] height;
    static long[] cost;
    static int N, D;

    static void up(long[] cost){
        long[] dis = new long[N];
        Arrays.fill(dis, Long.MAX_VALUE);
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(0, 0));
        dis[0] = 0;
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.node]) continue;
            for(Edge next : list[tmp.node]){
                if(height[next.node] <= height[tmp.node]) continue;
                if(tmp.cost + next.cost < dis[next.node]){
                    dis[next.node] = tmp.cost + next.cost;
                    pQ.offer(new Edge(next.node, dis[next.node]));
                }
            }
        }
        for(int i = 0 ; i < N ; i ++){
            if(dis[i] == Long.MAX_VALUE) cost[i] = Long.MAX_VALUE;
            else cost[i] = dis[i] * D;
        }
    }

    static void down(long[] cost){
        long[] dis = new long[N];
        Arrays.fill(dis, Long.MAX_VALUE);
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(N - 1, 0));
        dis[N - 1] = 0;
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.node]) continue;
            for(Edge next : list[tmp.node]){
                if(height[next.node] <= height[tmp.node]) continue;
                if(tmp.cost + next.cost < dis[next.node]){
                    dis[next.node] = tmp.cost + next.cost;
                    pQ.offer(new Edge(next.node, dis[next.node]));
                }
            }
        }
        for(int i = 0 ; i < N ; i ++){
            if(dis[i] == Long.MAX_VALUE) cost[i] = Long.MAX_VALUE;
            if(cost[i] != Long.MAX_VALUE) cost[i] += dis[i] * D;
        }
    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        height = new int[N];
        cost = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            list[i] = new ArrayList<>();
            height[i] = Integer.parseInt(st.nextToken()) * E;
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Edge(end, cost));
            list[end].add(new Edge(start, cost));
        }

        up(cost);
        down(cost);

        long max = Long.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            if(cost[i] == Long.MAX_VALUE) continue;
            max = Math.max(height[i] - cost[i], max);
        }
        if(max == Long.MIN_VALUE) System.out.println("Impossible");
        else System.out.println(max);
    }
}
