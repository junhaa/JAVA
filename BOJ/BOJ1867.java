import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #1867 돌멩이 제거
public class Main {
    static ArrayList<Integer> adj[];
    static boolean[] visited;
    static int[] r, c;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : adj[cur]){
            if(c[next] == -1 || (!visited[c[next]] && DFS(c[next]))){
                r[cur] = next;
                c[next] = cur;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        r = new int[n];
        c = new int[k];
        for(int i = 0 ; i < n ; i ++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < k ; i ++){
            st = new StringTokenizer(br.readLine());
            adj[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
        }
        Arrays.fill(r, -1);
        Arrays.fill(c, -1);
        int answer = 0;
        for(int i = 0 ; i < n ; i ++){
            if(r[i] == -1){
                visited = new boolean[n];
                if(DFS(i)) answer ++;
            }
        }
        System.out.println(answer);
    }
}
