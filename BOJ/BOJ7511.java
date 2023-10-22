import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #7511 소셜 네트워킹 어플리케이션
public class Main {
    static int[] parent;
    static int N;
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa < fb){
            parent[fa] = fb;
        }
        else{
            parent[fb]= fa;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= T ; i ++){
            if(i != 1) sb.append("\n");
            sb.append("Scenario " + i + ":\n");
            N = Integer.parseInt(br.readLine());
            parent = new int[N];
            for(int j = 0 ; j < N ; j ++){
                parent[j] = j;
            }
            int K = Integer.parseInt(br.readLine());
            for(int j = 0 ; j < K ; j ++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                union(start, end);
            }
            int m = Integer.parseInt(br.readLine());
            for(int j = 0 ; j < m ; j ++){
                st = new StringTokenizer(br.readLine());
                if(find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken()))) sb.append(1 + "\n");
                else sb.append(0 + "\n");
            }
        }
        System.out.println(sb);
    }
}
