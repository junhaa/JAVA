import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ #13910 개업
public class Main {
    static int[] dp;
    static final int MAX = 1000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        dp = new int[N + 1];
        int[] arr = new int[M];
        HashSet<Integer> set = new HashSet<>();
        Arrays.fill(dp, MAX);
        for(int i = 0 ; i < M ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }
        for(int i = 0 ; i < M ; i ++){
            for(int j = i + 1 ; j < M ; j ++){
                if(arr[i] + arr[j] <= N) set.add(arr[i] + arr[j]);
            }
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for(int x : list) dp[x] = 1;
        for(int i = 1 ; i <= N ; i ++ ){
            for(int j = 0 ; j < list.size() ; j ++){
                int tmp = list.get(j);
                if(i - tmp < 1) break;
                dp[i] = Math.min(dp[i], dp[i - tmp] + dp[tmp]);
            }
        }
        if(dp[N] == MAX) System.out.println(-1);
        else System.out.println(dp[N]);
    }
}
