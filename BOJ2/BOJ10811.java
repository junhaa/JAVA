import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #10811 바구니 뒤집기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i ++){
            arr[i] = i + 1;
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            Stack<Integer> stack = new Stack<>();
            int start = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int end = Integer.parseInt(st.nextToken()) - 1; // 0-based
            for(int j = start ; j <= end ; j ++){
                stack.push(arr[j]);
            }

            for(int j = start ; j <= end ; j ++){
                arr[j] = stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N ; i ++){
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }
}
