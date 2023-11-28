import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
// BOJ #17481 최애 정하기
public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] narr, marr;
    static ArrayList<Integer>[] edge;
    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(marr[next] == -1 || !visited[marr[next]] && DFS(marr[next])){
                narr[cur] = next;
                marr[next] = cur;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        narr = new int[N];
        marr = new int[M];

        Arrays.fill(narr, -1);
        Arrays.fill(marr, -1);

        edge = new ArrayList[N];

        int midx = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < M ; i ++){
            String cur = br.readLine();
            map.put(cur, midx ++);
        }

        for(int i = 0 ; i < N ; i ++){
            edge[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < len ; j ++){
                String tmp = st.nextToken();
                edge[i].add(map.get(tmp));
            }
        }

        int answer = 0;

        for(int i = 0 ; i < N ; i ++){
            if(narr[i] == -1){
                visited = new boolean[N];
                if(DFS(i)) answer ++;
            }
        }
        if(N == answer) {
            System.out.println("YES");
        }
        else{
            System.out.println("NO\n" + answer);
        }
    }
}
