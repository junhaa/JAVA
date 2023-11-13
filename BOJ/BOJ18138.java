import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #18138 리유나는 세일러복을 좋아해
public class Main {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    static int[] arr1, arr2;

    static boolean canAttach(int a, int b) {
        if(((double) a / 2 <= (double) b ) && ((double) a * ((double) 3 / 4) >= (double)b) || ((double) a <= (double) b) && (((double) a * ((double) 5 / 4)) >= (double) b)){
            return true;
        }
        return false;
    }

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : adj[cur]){
            if (arr2[next] == -1 || !visited[arr2[next]] && DFS(arr2[next])) {
                arr1[cur] = next;
                arr2[next] = cur;
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
        arr1 = new int[N];
        arr2 = new int[M];
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (canAttach(arr1[i], arr2[j])) {
                    adj[i].add(j);
                }
            }
        }
        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);


        int answer = 0;
        for(int i = 0 ; i < N ; i ++){
            if(arr1[i] == -1){
                visited = new boolean[N];
                if(DFS(i)) answer ++;
            }
        }
        System.out.println(answer);
    }
}
