import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #14950 정복자
class Edge implements Comparable<Edge>{
    int node, cost;
    public Edge(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Edge>[] list = new ArrayList[N];
        int M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        long answer = 0;
        int cnt = 0;
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        for(int i = 0 ; i < N ; i ++){
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Edge(end, cost));
            list[end].add(new Edge(start, cost));
        }
        boolean[] visited = new boolean[N];
        pQ.offer(new Edge(0, 0));
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(visited[tmp.node]) continue;
            visited[tmp.node] = true;
            if(tmp.node != 0) answer += tmp.cost + (t * cnt++);
            for(Edge next : list[tmp.node]){
                pQ.offer(next);
            }
        }
        System.out.println(answer);
    }
}
