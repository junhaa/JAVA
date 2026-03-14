import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #35295 소수가 아닌 수 4
public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> numbers = new ArrayList<>();

        for(int i = 0 ; i < N ; i ++){
            int cur = Integer.parseInt(st.nextToken());
            if(cur == 1) continue;
            cnt ++;
            numbers.add(cur);

            if(cnt == 2) {
                break;
            }
        }

        if(cnt == 1){
            if(!main.isPrime(numbers.get(0))) {
                System.out.println("YES\n2\n1 " + numbers.get(0));
                return;
            }
            System.out.println("NO");
            return;
        }

        System.out.println("YES\n2\n" + numbers.get(0) + " " + numbers.get(1));
    }

    private boolean isPrime(int number){
        if(number == 1) return false;
        for(int i = 2 ; i <= (int)Math.sqrt(number); i ++) {
            if(number % i == 0) return false;
        }
        return true;
    }
}
