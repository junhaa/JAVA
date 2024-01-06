import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #24483 알고리즘 수업 - 깊이 우선 탐색 5
public class Main {
    static int N, M, num;
    static ArrayList<Integer>[] edge;
    static boolean[] visited;
    static long DFS(int depth, int cur){
        num ++;
        long sum = (long)depth * (long)num;
        visited[cur] = true;
        for(int next : edge[cur]){
            if(!visited[next]) {
                sum += DFS(depth + 1, next);
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        edge = new ArrayList[N + 1];
        for(int i = 1 ; i <= N ; i ++){
            edge[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }

        for(int i = 1 ; i <= N ; i ++){
            Collections.sort(edge[i]);
        }

        System.out.println(DFS(0, R));
    }
}
