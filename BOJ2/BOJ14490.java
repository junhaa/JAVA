import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #14490 백대열
public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(T.solve(br.readLine()));
    }

    private String solve(String input){
        String[] split = input.split(":");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);

        int gcd = gcd(a, b);
        return a / gcd + ":" + b / gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
