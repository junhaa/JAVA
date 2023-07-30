import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ #2310 어드벤처 게임
class Adventurer{
    int node, cost;
    public Adventurer(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
}

public class Main {

    static int[] map, visited, cArr; // 0 E, 1 L, 2 T
    static ArrayList<Integer>[] edge;
    static StringBuilder sb = new StringBuilder();


    static int getNextCost(int N, int cost){
        if(map[N] == 2){
            return cost - cArr[N];
        }
        else{
            if(cArr[N] > cost) return cArr[N];
            else return cost;
        }
    }
    static String BFS(int N){
        Queue<Adventurer> Q = new LinkedList<>();
        if(map[0] != 2){
            visited[0] = cArr[0];
            Q.offer(new Adventurer(0, cArr[0]));
        }
        while(!Q.isEmpty()){
            Adventurer cur = Q.poll();
            if(cur.node == N - 1) return "Yes\n";
            if(cur.cost < visited[cur.node]) continue;
            for(int next : edge[cur.node]){
                int nc = getNextCost(next, cur.cost);
                if(nc >= 0  && visited[next] < nc){
                    visited[next] = nc;
                    Q.offer(new Adventurer(next, nc));
                }
            }
        }
        return "No\n";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            edge = new ArrayList[N];
            map = new int[N];
            visited = new int[N];
            cArr = new int[N];
            Arrays.fill(visited, -1);
            for(int i = 0 ; i < N ; i ++) {
                edge[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                char room = st.nextToken().charAt(0);
                if(room == 'E'){
                    map[i] = 0;
                }
                else if(room == 'L'){
                    map[i] = 1;
                }
                else{
                    map[i] = 2;
                }
                cArr[i] = Integer.parseInt(st.nextToken());
                int len = st.countTokens();
                for(int j = 0 ; j < len - 1 ; j ++){
                    edge[i].add(Integer.parseInt(st.nextToken()) - 1);
                }
            }
            sb.append(BFS(N));
        }
        System.out.println(sb);
    }
}
