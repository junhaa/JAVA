import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #2787 흔한 수열 문제
public class Main {
    static int N;
    static int[] min, max, ma, mb, start, end;
    static boolean[] visited;
    static ArrayList<Integer>[] edge;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(mb[next] == -1 || !visited[mb[next]] && DFS(mb[next])){
                ma[cur] = next;
                mb[next] = cur;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        min = new int[N + 1]; // 1-based
        max = new int[N + 1];

        Arrays.fill(max, N);
        Arrays.fill(min, 1);
        edge = new ArrayList[N + 1];
        ma = new int[N + 1];
        mb = new int[N + 1];
        start = new int[N + 1];
        end = new int[N + 1];

        Arrays.fill(ma, -1);
        Arrays.fill(mb, -1);
        Arrays.fill(end, N);
        Arrays.fill(start, 1);
        for(int i = 1 ; i <= N ; i ++){
            edge[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int num, x, y, v;
            num = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if(start[v] > y || end[v] < x){
                System.out.println(-1);
                return;
            }
            else{
                start[v] = Math.max(start[v], x);
                end[v] = Math.min(end[v], y);
            }
            if(num == 1){ // max range;
                for(int j = x ;  j <= y ; j ++){
                    max[j] = Math.min(max[j], v);
                }
            }
            else {
                for(int j = x ;  j <= y ; j ++){
                    min[j] = Math.max(min[j], v);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = start[i]; j <= end[i]; j++) {
                edge[j].add(i);
            }
        }

        for(int i = 1 ; i <= N ; i ++){
            for(int j = min[i] ; j <= max[i] ; j ++){
                if(start[j] == Integer.MIN_VALUE)
                    edge[i].add(j);
                }
        }
        for(int i = 1 ; i <= N ; i ++){
            if(ma[i] == -1){
                visited = new boolean[N + 1];
                if(!DFS(i)){
                    System.out.println(-1);
                    return;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i ++){
            sb.append(ma[i] + " ");
        }
        System.out.println(sb);
    }
}

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #2787 흔한 수열 문제
public class Main {
    static int N;
    static int[] min, max, ma, mb, start, end;
    static boolean[] visited;
    static ArrayList<Integer>[] edge;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(mb[next] == -1 || !visited[mb[next]] && DFS(mb[next])){
                ma[cur] = next;
                mb[next] = cur;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        min = new int[N + 1]; // 1-based
        max = new int[N + 1];

        Arrays.fill(max, N);
        Arrays.fill(min, 1);
        edge = new ArrayList[N + 1];
        ma = new int[N + 1];
        mb = new int[N + 1];
        start = new int[N + 1];
        end = new int[N + 1];

        Arrays.fill(ma, -1);
        Arrays.fill(mb, -1);
        Arrays.fill(end, N);
        Arrays.fill(start, 1);
        for(int i = 1 ; i <= N ; i ++){
            edge[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int num, x, y, v;
            num = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if(start[v] > y || end[v] < x){
                System.out.println(-1);
                return;
            }
            else{
                start[v] = Math.max(start[v], x);
                end[v] = Math.min(end[v], y);
            }
            if(num == 1){ // max range;
                for(int j = x ;  j <= y ; j ++){
                    max[j] = Math.min(max[j], v);
                }
            }
            else {
                for(int j = x ;  j <= y ; j ++){
                    min[j] = Math.max(min[j], v);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = start[i]; j <= end[i]; j++) {
                edge[j].add(i);
            }
        }

        for(int i = 1 ; i <= N ; i ++){
            for(int j = min[i] ; j <= max[i] ; j ++){
                if(start[j] == Integer.MIN_VALUE)
                    edge[i].add(j);
                }
        }
        for(int i = 1 ; i <= N ; i ++){
            if(ma[i] == -1){
                visited = new boolean[N + 1];
                if(!DFS(i)){
                    System.out.println(-1);
                    return;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i ++){
            sb.append(ma[i] + " ");
        }
        System.out.println(sb);
    }
}
*/
