import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2307 도로검문
class Edge implements Comparable<Edge>{
    int end, cost;
    public Edge(int end, int cost){
        this.end = end;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}

public class Main {
    static int N, M;
    static int[] seq, dis;
    static ArrayList<Edge>[] edge;
    static ArrayList<Edge> shortEdge = new ArrayList<>();
    static Edge checkPoint = null; // start, end
    static void dijkstra(){
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(0, 0));
        dis[0] = 0;
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.end]) continue;
            for(Edge next : edge[tmp.end]){
                if(dis[next.end] > tmp.cost + next.cost){
                    dis[next.end] = tmp.cost + next.cost;
                    seq[next.end] = tmp.end;
                    pQ.offer(new Edge(next.end, tmp.cost + next.cost));
                }
            }
        }
    }

    static void cdijkstra(){
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(0, 0));
        dis[0] = 0;
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.end]) continue;
            for(Edge next : edge[tmp.end]){
                if((next.end == checkPoint.end || next.end == checkPoint.cost) && (tmp.end == checkPoint.end || tmp.end == checkPoint.cost)) continue;
                if(dis[next.end] > tmp.cost + next.cost){
                    dis[next.end] = tmp.cost + next.cost;
                    seq[next.end] = tmp.end;
                    pQ.offer(new Edge(next.end, tmp.cost + next.cost));
                }
            }
        }
    }

    static void findShortCut(int last){
        shortEdge.add(new Edge(last, seq[last]));
        if(seq[last] == 0) {
            return;
        }
        findShortCut(seq[last]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // node
        M = Integer.parseInt(st.nextToken()); // edge
        dis = new int[N];
        edge = new ArrayList[N];
        seq = new int[N];
        Arrays.fill(seq, -1);
        for(int i = 0 ; i < N ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            edge[start].add(new Edge(end, cost));
            edge[end].add(new Edge(start, cost));
        }
        dijkstra();
        int min = dis[N - 1];
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        int answer = Integer.MIN_VALUE;
        findShortCut(N - 1);
        for(int i = 0 ; i < shortEdge.size() ; i ++){
            checkPoint = shortEdge.get(i);
            cdijkstra();
            answer = Math.max(answer, dis[N - 1]);
        }
        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else System.out.println(answer - min);
    }
}
