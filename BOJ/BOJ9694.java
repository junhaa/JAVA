import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ #9694 무엇을 아느냐가 아니라 누구를 아느냐가 문제다
class Edge implements Comparable<Edge>{
    int node, cost;
    public Edge(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}

public class Main {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Edge>[] elist;
    static int[] seq;
    static Stack<Integer> stack;

    static void getRoot(int cur){
        if(cur == 0){
            stack.push(cur);
            return;
        }
        stack.push(cur);
        getRoot(seq[cur]);
    }

    static boolean dijkstra(int M){
        int[] dis = new int[M];
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(0, 0));
        dis[0] = 0;
        while(!pQ.isEmpty()){
            Edge tmp = pQ.poll();
            if(tmp.cost > dis[tmp.node]) continue;
            if(tmp.node == M - 1) return true;
            for(Edge next : elist[tmp.node]){
                if(dis[next.node] > tmp.cost + next.cost){
                    dis[next.node] = tmp.cost + next.cost;
                    pQ.offer(new Edge(next.node, dis[next.node]));
                    seq[next.node] = tmp.node;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int j = 1  ; j <= T ; j ++){
            sb.append("Case #" + j + ": ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            elist = new ArrayList[M];
            seq = new int[M];
            stack = new Stack<>();
            for(int i = 0 ; i < M ; i ++) elist[i] = new ArrayList<>();
            for(int i = 0 ; i < N ; i ++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                elist[start].add(new Edge(end, cost));
                elist[end].add(new Edge(start, cost));
            }
            if(dijkstra(M)){
                getRoot(M - 1);
                while(!stack.isEmpty()){
                    sb.append(stack.pop() + " ");
                }
                sb.append("\n");
            }
            else sb.append(-1 + "\n");
        }
        System.out.println(sb);
    }
}
