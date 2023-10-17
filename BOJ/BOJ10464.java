import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #10464 XOR
public class Main {

    static int getXOR(int num){
        int rem = num % 4;
        if(rem == 0){
            return num;
        }
        else if(rem == 1){
            return 1;
        }
        else if(rem == 2){
            return 1 ^ num;
        }
        else {
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            sb.append(getXOR(S - 1) ^ getXOR(F)).append("\n");
        }
        System.out.println(sb);
    }
}
