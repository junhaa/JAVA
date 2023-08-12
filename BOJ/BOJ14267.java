import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #14267 회사 문화 1
public class Main {
    static int[] sum;
    static ArrayList<Integer>[] edge;

    static void DFS(int bossScore, int num){
        sum[num] += bossScore;
        for(int next : edge[num]){
            DFS(sum[num], next);
        }
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        edge = new ArrayList[n];
        sum = new int[n];
        for(int i = 0 ; i < n ; i ++){
            edge[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for(int i = 1 ; i < n ; i ++){
            int boss = Integer.parseInt(st.nextToken()) - 1;
            edge[boss].add(i);
        }

        for(int i = 0 ; i < m ; i ++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int num = Integer.parseInt(st.nextToken());
            sum[p] += num;
        }
        DFS(0, 0);
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i ++){
            sb.append(sum[i] + " ");
        }
        System.out.println(sb);
    }
}
