import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #20310 타노스
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int len = input.length;
        int cnt0 = 0;
        int cnt1 = 0;
        for(int i = 0 ; i < len ; i ++ ){
            if(input[i] == '0'){
                cnt0 ++;
            }
            else cnt1 ++;
        }

        cnt0 /= 2;
        cnt1 /= 2;
        for(int i = 0 ; i < len ; i ++){
            if(input[i] == '1') {
                input[i] = 'x';
                if(--cnt1 == 0) break;
            }
        }
        for(int i = len - 1 ; i >= 0 ; i --){
            if(input[i] == '0'){
                input[i] = 'x';
                if(--cnt0 == 0) break;
            }
        }
        String answer = "";
        for(int i = 0 ; i < len ; i ++){
            if(input[i] != 'x') answer += input[i];
        }
        System.out.println(answer);
    }
}
