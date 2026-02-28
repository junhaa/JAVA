import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #4307 개미
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < T ; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int maxTime = Integer.MIN_VALUE;
            int minTime = Integer.MIN_VALUE;
            for(int j = 0 ; j < n ; j ++){
                int position = Integer.parseInt(br.readLine());
                minTime = Math.max(minTime, Math.min(position, l - position));
                maxTime = Math.max(maxTime, position);
                maxTime = Math.max(maxTime, l - position);
            }
            sb.append(minTime).append(" ").append(maxTime).append("\n");
        }
        System.out.println(sb);
    }
}
