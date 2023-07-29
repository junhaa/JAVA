import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #13905 세부
class Edge implements Comparable<Edge>{
    int node, cost;
    public Edge(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
    public int compareTo(Edge o){
        return o.cost - this.cost;
    }
}
public class Main {

    static int N, M, start, end;
    static ArrayList<Edge>[] elist;
    static boolean[] visited;

    static int MST(){
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(start, Integer.MAX_VALUE));
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(visited[tmp.node]) continue;
            if(tmp.node == end) return tmp.cost;
            visited[tmp.node] = true;
            for(Edge e : elist[tmp.node]){
                pQ.offer(new Edge(e.node, Math.min(e.cost, tmp.cost)));
            }
        }
        return 0;
    }



    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        elist = new ArrayList[N];
        visited = new boolean[N];
        for(int i = 0 ; i < N ; i ++){
            elist[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            elist[s].add(new Edge(e, cost));
            elist[e].add(new Edge(s, cost));
        }
        System.out.println(MST());
    }
}


