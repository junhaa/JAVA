import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16397 탈출
/*
    1. 버튼 A를 누르면 N이 1증가
    2. 버튼 B를 누르면 N에 2가 곱해진 뒤, 0이 아닌 가장 높은 자릿수의 숫자가 1감소 (단, N이 0이면 버튼 B를 눌러도 수가 변하지 X)
    3. N이 6자리가 넘어가는 순간 탈출에 실패 (N에 2를 곱한 기준, 50000)\
    4. T회 이내에 탈출 할 수 없으면 탈출 실패
 */

public class Main {
    static boolean visited[] = new boolean[100000];


    static int BFS(int N, int T, int G){
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(N);
        visited[N] = true;
        int L = 0;
        while(!Q.isEmpty()){
            if(L > T) return -1;
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                int tmp = Q.poll();
                if(tmp == G) return L;
                if(tmp == 99999 || visited[tmp + 1]);
                else {
                    Q.offer(tmp + 1);
                    visited[tmp + 1] = true;
                }
                if(tmp * 2 >= 100000 || tmp == 0) continue;
                int l = 0;
                int cur = tmp * 2;
                for(; l < 6 ; l ++){
                    if(cur / (int)Math.pow(10, l) == 0) break;
                }
                int next = cur - (int)Math.pow(10, l - 1);
                if(!visited[next]){
                    Q.offer(next);
                    visited[next] = true;
                }
            }
            L ++;
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int answer = BFS(N, T, G);
        if(answer == -1) System.out.println("ANG");
        else System.out.println(answer);

    }
}
