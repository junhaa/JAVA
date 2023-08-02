import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// BOJ #17425 약수의 합
public class Main {
    static long prefix[] = new long[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i = 2 ; i <= 1000000 ; i ++){
            prefix[i] = i + 1;
        }
        prefix[1] = 1;
        for(int i = 2 ; i <= Math.sqrt(1000000) ; i ++){
            for(int j = i * i ; j <= 1000000 ; j += i){
                prefix[j] += j / i;
                prefix[j] += i;
                if(Math.sqrt(j) == i) prefix[j] -= i;
            }
        }
        for(int i = 2 ; i <= 1000000 ; i ++){
            prefix[i] = prefix[i - 1] + prefix[i];
        }
        for(int i = 0 ; i < T ; i ++){
            sb.append(prefix[Integer.parseInt(br.readLine())] + "\n");
        }
        System.out.println(sb);
    }
}
