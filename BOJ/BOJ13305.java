import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #13305 주유소 
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N], dis = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N - 1 ; i ++){
            dis[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            cost[i] = Integer.parseInt(st.nextToken());
        }
        long curCost = Integer.MAX_VALUE;
        long answer = 0;
        for(int i = 0 ; i < N - 1 ; i ++){
            curCost = Math.min(curCost, cost[i]);
            answer += curCost * dis[i];
        }
        System.out.println(answer);
    }
}
