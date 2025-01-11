import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #32759 준근이와 마법 공방
public class Main {
    private static final int MOD = (int)1e9 + 7;
    private static int[] fiboDp;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> stones = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i ++){
            stones.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(stones, Collections.reverseOrder());

        int s1 = stones.get(0);
        int s2 = stones.get(1);

        if(s1 < 0 && s2 < 0){
            System.out.println(MOD + s1 + s2);
            return;
        }

        while(s1 * s2 < 0) {
            s2 = s1 + s2;
            N--;
            if(N == 0){
                System.out.println((s2 + MOD) % MOD);
                return;
            }
        }

        if(s1 * s2 == 0 && s2 < 0){
            System.out.println(MOD + s2);
            return;
        }

        fibo();

        int sum1 = (int)(((long)fiboDp[N] * s1) % MOD);
        int sum2 = (int)(((long)fiboDp[N - 1] * s2) % MOD);

        int result = (sum1 + sum2) % MOD;
        if(result < 0){
            System.out.println(MOD + result);
        }
        else{
            System.out.println(result);
        }

    }

    private static void fibo(){
        fiboDp = new int[N + 1];
        fiboDp[0] = 1;
        fiboDp[1] = 1;

        for(int i = 2 ; i <= N ; i ++){
            fiboDp[i] = (fiboDp[i - 1] + fiboDp[i - 2]) % MOD;
        }
    }
}
