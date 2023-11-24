import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #10552 DOM
public class Main {
    static boolean[] visited;
    static int[] edges;

    static int cnt = 0;
    static int DFS(int cur){
        visited[cur] = true;
        if(edges[cur] == 0){
            return cur;
        }
        else if(visited[edges[cur]]) return -1;
        else {
            cnt ++;
            return DFS(edges[cur]);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        visited = new boolean[M + 1];
        edges = new int[M + 1];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(edges[b] == 0) {
                edges[b] = a;
            }
        }
        if(DFS(P) == -1){
            System.out.println(-1);
        }
        else System.out.println(cnt);
    }
}
