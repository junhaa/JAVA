import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #15271 친구 팰린드롬 2
public class Main {
    static int N, M;
    static ArrayList<Integer>[] edge;
    static int[] na, nb;
    static boolean[] visited;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(nb[next] == -1 || !visited[nb[next]] && DFS(nb[next])){
                na[cur] = next;
                nb[next] = cur;
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
        int nalen = N / 2;
        int nblen = N / 2;
        if(N % 2 == 1) nalen ++;
        na = new int[nalen];
        nb = new int[nblen];

        Arrays.fill(na, -1);
        Arrays.fill(nb, -1);

        edge = new ArrayList[nalen];
        for(int i = 0 ; i < nalen ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(u % 2 == v % 2) continue;
            if(u % 2 == 0){
                int tmp = v;
                v = u;
                u = tmp;
            }
            int aidx = u / 2;
            int bidx = v / 2 - 1;
            edge[aidx].add(bidx);
        }
        int answer = 0;
        for(int i = 0 ; i < nalen ; i ++){
            if(na[i] == -1){
                visited = new boolean[nalen];
                if(DFS(i)) answer ++;
            }
        }
        answer *= 2;
        for(int i = 0 ; i < nalen ; i ++){
            if(na[i] == -1){
                answer ++;
                break;
            }
        }
        System.out.println(answer);
    }
}
