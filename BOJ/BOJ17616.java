import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #17616 등수 찾기
public class Main {
    static int N, M, X;
    static ArrayList<Integer>[] edge, redge;
    static boolean[] visited;
    static int DFS(int cur, boolean rev){
        visited[cur] = true;
        ArrayList<Integer> curEdge;
        int sum = 1;
        if(!rev) {
             curEdge = edge[cur];
        }
        else {
              curEdge = redge[cur];
        }
        for(int next : curEdge){
            if(!visited[next]){
                sum += DFS(next, rev);
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        edge = new ArrayList[N]; // 0-based
        redge = new ArrayList[N];
        visited = new boolean[N];
        for(int i = 0 ; i < N ; i ++){
            edge[i] = new ArrayList<>();
            redge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            edge[start].add(end);
            redge[end].add(start);
        }

        int up = DFS(X, true);
        visited = new boolean[N];
        int down = DFS(X, false);
        System.out.println(up + " " + (N - (down - 1)));

    }

}
