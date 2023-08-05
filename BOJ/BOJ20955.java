import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #20955 민서의 응급 수술 
public class Main {
    static int N, M;
    static int[] parent;

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa < fb){
            parent[fb] = fa;
        }
        else parent[fa] = fb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        int answer = 0;
        for(int i = 0 ; i < N ; i ++) {
            parent[i] = i;
        }
        for(int i = 0 ; i < M ; i ++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            if(find(start) == find(end)) {
                answer ++;
                continue;
            }
            else union(start, end);
        }
        for(int i = 0 ; i < N ; i ++){
            if(parent[i] == i) answer ++;
        }
        System.out.println(answer - 1);
    }
}
