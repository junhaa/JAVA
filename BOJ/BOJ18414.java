import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #18414 X に最も近い値 (The Nearest Value)
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        if(X >= L && X <= R) System.out.println(X);
        else{
            if(Math.abs(X - L) < Math.abs(X - R)){
                System.out.println(L);
            }
            else System.out.println(R);
        }
    }
}
