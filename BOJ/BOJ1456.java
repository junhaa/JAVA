import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//BOJ #1456 거의 소수 
public class Main {
    static boolean[] ch;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Long A = Long.parseLong(st.nextToken());
        Long B = Long.parseLong(st.nextToken());
        int len = (int)Math.sqrt(B);
        ch = new boolean[len + 1];
        ch[1] = true;
        for(int i = 2 ; i <= Math.sqrt(len) ; i ++){
            for(int j = i * i ; j <= len ; j += i){
                ch[j] = true;
            }
        }
        int answer = 0;
        for(int i = 2 ; i <= len ; i ++){
            if(!ch[i]){
                long cur = i;
                while(cur <= (double)B / i){
                    if(cur >= (double)A / i){
                        answer ++;
                    }
                    cur *= i;
                }
            }
        }
        System.out.println(answer);
    }
}
