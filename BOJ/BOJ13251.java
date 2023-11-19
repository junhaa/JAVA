import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #13251 조약들 꺼내기
public class Main {
    static double mul(int cur, int K, int sum){
        double tmp = 1.0;
        for(int i = 0 ; i < K ; i ++){
            tmp *= ((double)(cur - i) / (double)(sum - i));
        }
        return tmp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 0 ; i < M ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        int K = Integer.parseInt(br.readLine());
        double ans = 0.0;
        for(int i = 0 ; i < M ; i ++){
            if(arr[i] >= K) ans += mul(arr[i], K, sum);
        }
        System.out.println(ans);
    }

}
