import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #15654 N과 M(5)
public class Main {
    static int N, M;
    static int[] arr, arr2;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void BT(int L, int last){
        if(L == M) {
            for(int i = 0 ; i < M ; i ++){
                sb.append(arr2[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 0 ; i < N ; i ++){
            if(!visited[i]) {
                arr2[L] = arr[i];
                visited[i] = true;
                BT(L + 1, i);
                visited[i] = false;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        arr2 = new int[M];
        visited = new boolean[N];
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        BT(0, -1);
        System.out.println(sb);
    }
}
