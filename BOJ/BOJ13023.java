import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #13023 ABCDE 
public class Main {
    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] edge;

    static int DFS(int cur, int L){
        visited[cur] = true;
        int max = 0;
        if(L == 4){
            return 1;
        }
        for(int next : edge[cur]){
            if(!visited[next]) max = Math.max(DFS(next, L + 1), max);
        }
        visited[cur] = false;
        return max;
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edge = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edge[start].add(end);
            edge[end].add(start);
        }
        for(int i = 0 ; i < N ; i ++){
            visited = new boolean[N];
            if(DFS(i, 0) == 1) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
