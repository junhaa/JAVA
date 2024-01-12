import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #9576 책 나눠주기
public class Main {
    static int N, M;
    static ArrayList<Integer>[] edge;
    static boolean[] visited;
    static int[] A, B;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(B[next] == -1 || !visited[B[next]] && DFS(B[next])){
                A[cur] = next;
                B[next] = cur;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            edge = new ArrayList[M];
            for(int i = 0 ; i < M ; i ++){
                edge[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                for(int j = start ; j <= end ; j ++){
                    edge[i].add(j);
                }
            }
            A = new int[M];
            B = new int[N];

            Arrays.fill(A, -1);
            Arrays.fill(B, -1);

            int answer = 0;

            for(int i = 0 ; i < M ; i ++){
                if(A[i] == -1){
                    visited = new boolean[M];
                    if(DFS(i)) answer ++;
                }
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
}
