import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// BOJ #17828 문자열 화폐
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int cur = X - N;
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        if(cur < 0){
            System.out.println("!");
            return;
        }
        if(cur > 25 * N) {
            System.out.println("!");
            return;
        }
        while(cur >= 25){
            stack.push('Z');
            cur -= 25;
            N --;
        }
        if(N > 0) {
            stack.push((char) ('A' + cur));
            N--;
        }
        for(; N > 0 ; N --){
            stack.push('A');
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        System.out.println(sb);
    }
}
