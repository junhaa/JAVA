import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #15558 점프 게임
class Box{
    int idx, num;
    public Box(int idx, int num){
        this.idx = idx;
        this.num = num;
    }
}
public class Main {
    static int[][] row;
    static boolean[][] visited;

    static int BFS(int N, int K){
        Queue<Box> Q = new LinkedList<>();
        Q.offer(new Box(0 ,0));
        visited[0][0] = true;
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                Box tmp = Q.poll();
                // i + 1
                int nidx = tmp.idx + 1;
                if(nidx >= N) return 1;
                if(row[tmp.num][nidx] == 1 && !visited[tmp.num][nidx]) {
                    Q.offer(new Box(nidx, tmp.num));
                    visited[tmp.num][nidx] = true;
                }
                // i + k
                nidx = tmp.idx + K;
                if(nidx >= N) return 1;
                if(row[1 - tmp.num][nidx] == 1 && !visited[1 - tmp.num][nidx]){
                    Q.offer(new Box(nidx, 1 - tmp.num));
                    visited[1 - tmp.num][nidx] = true;
                }

                // i - 1
                nidx = tmp.idx - 1;
                if(nidx >= 0 && nidx > L && row[tmp.num][nidx] == 1 && !visited[tmp.num][nidx]){
                    Q.offer(new Box(nidx, tmp.num));
                    visited[tmp.num][nidx] = true;
                }
            }
            L ++;
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        row = new int[2][N];
        visited = new boolean[2][N];
        String input1 = br.readLine();
        String input2 = br.readLine();

        for(int i = 0 ; i < N ; i ++){
            row[0][i] = input1.charAt(i) - '0';
            row[1][i] = input2.charAt(i) - '0';
        }
        System.out.println(BFS(N, K));
    }
}
