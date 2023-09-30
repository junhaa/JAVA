import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #1013 Contact 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = "(100+1+|01)+";
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T -- > 0) {
            String input = br.readLine();
            if(input.matches(pattern)) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
