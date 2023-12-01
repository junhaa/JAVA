import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #17402 시간 끌기
public class Main {
    static int N, M;
    static ArrayList<Integer>[] edge;
    static boolean[] visited;
    static int[] aarr, barr;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(barr[next] == -1 || !visited[barr[next]] && DFS(barr[next])){
                aarr[cur] = next;
                barr[next] = cur;
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
        int K = Integer.parseInt(st.nextToken());
        edge = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < K ; i ++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            edge[x].add(y);
        }

        aarr = new int[N];
        barr = new int[M];

        Arrays.fill(aarr, -1);
        Arrays.fill(barr, -1);

        int answer = 0;

        for(int i = 0 ; i < N ; i ++){
            if(aarr[i] == -1){
                visited = new boolean[N];
                if(DFS(i)) answer ++;
            }
        }

        System.out.println(N + M - answer);
    }
}
