import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #17396 백도어 
class Edge implements Comparable<Edge> {
    int node;
    long cost;

    public Edge(int node, long cost){
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o){
        if(this.cost > o.cost) return 1;
        else if(this.cost == o.cost) return 0;
        else return -1;
    }
}
public class Main {
    static boolean[] ward;
    static ArrayList<Edge>[] list;
    static final long MAX = (long)1e15;

    static long dijkstra(int N){
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        long[] dis = new long[N];
        Arrays.fill(dis, MAX);
        dis[0] = 0;
        pQ.offer(new Edge(0, 0));
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.node]) continue;
            for(Edge next : list[tmp.node]){
                if(ward[next.node]) continue;
                if(tmp.cost + next.cost < dis[next.node]){
                    pQ.offer(new Edge(next.node, tmp.cost + next.cost));
                    dis[next.node] = tmp.cost + next.cost;
                }
            }
        }
        return dis[N - 1];
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ward = new boolean[N];
        for(int i = 0 ; i < N - 1 ; i ++){
            if(Integer.parseInt(st.nextToken()) == 1){
                ward[i] = true;
            }
        }
        list = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Edge(end, cost));
            list[end].add(new Edge(start, cost));
        }
        long res = dijkstra(N);
        if(res == MAX) {
            System.out.println(-1);
        }
        else System.out.println(res);
    }
}
