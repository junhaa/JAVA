import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10990 별 찍기 - 15
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(T.calcString(Integer.parseInt(br.readLine())));
    }

    private String calcString(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n - 1 ; i ++){
            sb.append(" ");
        }
        sb.append("*");

        for(int i = 1 ; i < n ; i ++){
            sb.append("\n");
            for(int j = 0 ; j < n - i - 1 ; j ++) {
                sb.append(" ");
            }
            sb.append("*");
            for(int j = 0 ; j < 2 * i - 1 ; j ++){
                sb.append(" ");
            }
            sb.append("*");
        }

        return sb.toString();
    }
}
