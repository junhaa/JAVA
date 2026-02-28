import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #25947 선물할인
public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int[] cost = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < n ; i ++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(T.solve(n, b, a, cost));
    }

    private int solve(int n, int b, int a, int[] cost){
        Arrays.sort(cost);
        long[] psumCost = new long[n + 1];
        for(int i = 1 ; i <= n ; i ++){
            psumCost[i] = psumCost[i - 1] + cost[i - 1];
        }

        return calcMaxPurchase(psumCost, n, b, a);
    }

    private int calcMaxPurchase(long[] psumCost, int n, int b, int a){
        int max = Integer.MIN_VALUE;

        for(int endIdx = 0 ; endIdx <= n ; endIdx ++){
            long sum;
            if(endIdx <= a){
                sum = psumCost[endIdx] / 2;
            }
            else{
                sum = psumCost[endIdx] - (psumCost[endIdx] - psumCost[endIdx - a]) / 2;
            }

            if(sum <= b){
                max = Math.max(max, endIdx);
            }
        }

        return max;
    }
}
