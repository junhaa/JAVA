import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2023 신기한 소수
public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    static boolean isPrime(int num){
        for(int i = 2 ; i <= Math.sqrt(num) ; i ++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    static void back_tracking(int L, int num){
        if(L == N) {
            sb.append(num + "\n");
            return;
        }
        for(int i = 0 ; i < 5 ; i ++){
            int next = num * 10 + (i * 2 + 1);
            if(isPrime(next)){
                back_tracking(L + 1, next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        back_tracking(1, 2);
        back_tracking(1, 3);
        back_tracking(1, 5);
        back_tracking(1, 7);
        System.out.println(sb);
    }
}
