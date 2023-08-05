import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #19539 사과나무
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = 0;
        int sum = 0;
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            int tmp = Integer.parseInt(st.nextToken());
            sum += tmp;
            a += tmp / 2;
        }
        if(sum % 3 == 0 && a >= sum / 3){
            System.out.println("YES");
        }
        else System.out.println("NO");
    }
}
