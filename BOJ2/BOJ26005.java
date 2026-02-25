import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #26005 나뭇잎 학회
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 1){
            System.out.println(0);
            return;
        }
        System.out.println(N * N % 2 == 1 ? N * N / 2 + 1 : N * N / 2);
    }
}
