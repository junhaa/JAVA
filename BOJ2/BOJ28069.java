import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #28069 김밥천국의 계단
public class Main {
    static boolean[] visited = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        int L = 0 ;
        while(!q.isEmpty() && L <= K){
            int len = q.size();
            for(int i = 0 ; i < len ; i ++) {
                int cur = q.poll();
                if (cur == N) {
                    System.out.println("minigimbob");
                    return;
                }
                int next = cur + 1;

                if (next <= 1000000 && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
                next = cur + (cur / 2);
                if (next <= 1000000 && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
            L ++;
        }
        System.out.println("water");
    }
}
