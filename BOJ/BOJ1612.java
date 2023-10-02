import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
// BOJ #1612 가지고 노는 1
public class Main {
    static int cnt = 1;
    static int appendOne(int cur, int N){
        while(N > cur){
            cur *= 10;
            cur += 1;
            cnt ++;
        }
        return cur;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        int cur = 1;
        while(true) {
            cur = appendOne(cur, N);
            if (set.contains(cur)) {
                System.out.println(-1);
                return;
            }
            set.add(cur);
            cur %= N;
            if(cur == 0) break;
        }
        System.out.println(cnt);
    }
}
