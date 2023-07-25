import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #21278 호석이 두 마리 치
public class Main {
    static int[][] dis;
    static final int MAX = 1_000_000;
    static int N, M;

    static void floyd(){
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                for(int k = 0 ; k < N ; k ++){
                    if(dis[j][i] + dis[i][k] < dis[j][k]) {
                        dis[j][k] = dis[j][i] + dis[i][k];
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dis = new int[N][N];
        for(int i = 0 ; i < N ; i ++){
            Arrays.fill(dis[i], MAX);
            dis[i][i] = 0;
        }
        int start = 0, end = 0;
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            dis[start][end] = 1;
            dis[end][start] = 1;
        }
        int c1 = -1, c2 = -1;
        int minDis = Integer.MAX_VALUE;
        floyd();
        for(int i = 0 ; i < N ; i ++){
            for(int j = i + 1 ; j < N ; j ++){
                int sum = 0;
                for(int k = 0 ; k < N ; k ++){
                    if(k == i || k == j) continue;
                    int curDis = Math.min(dis[k][i], dis[k][j]);
                    sum += curDis * 2;
                }
                if(sum < minDis){
                    c1 = i;
                    c2 = j;
                    minDis = sum;
                }
            }
        }
        System.out.println( ++c1 + " " + ++c2 + " " + minDis);
    }
}
