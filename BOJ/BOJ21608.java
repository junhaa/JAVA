import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #21608 상어 초등학교
public class Main {
    static int N;
    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        else return true;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[][] placed = new int[N][N];
        int[][][] score = new int[N][N][N * N + 1];
        int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
        HashSet<Integer>[] set = new HashSet[N * N + 1];
        HashSet<Integer>[] Rset = new HashSet[N * N + 1];
        int[] seq = new int[N * N];
        StringTokenizer st ;
        for(int i = 1 ; i <= N * N ; i ++){
            set[i] = new HashSet<>();
            Rset[i] = new HashSet<>();
        }

        for(int i = 0 ; i < N * N ; i ++){
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            seq[i] = cur;
            for(int j = 0 ; j < 4 ; j ++) {
                int like = Integer.parseInt(st.nextToken());
                set[like].add(cur);
                Rset[cur].add(like);
            }
        }

        for(int i = 0 ; i < N * N ; i ++){
            int cur = seq[i];
            int max = Integer.MIN_VALUE;
            for(int j = 0 ; j < N ; j ++){
                for(int k = 0 ; k < N ; k ++){
                    if(placed[j][k] != 0) continue;
                    int sum = 0;
                    for(int l = 0 ; l < 4 ; l ++){
                        int ny = j + dy[l];
                        int nx = k + dx[l];
                        if(!OOB(ny, nx) && placed[ny][nx] == 0){
                            sum ++;
                        }
                    }
                    score[j][k][cur] += sum;
                    max = Math.max(score[j][k][cur], max);
                }
            }
            boolean flag = false;
            for(int j = 0 ; j < N ; j ++){
                for(int k = 0 ; k < N ; k ++){
                    if(placed[j][k] != 0) continue;
                    if(score[j][k][cur] == max) {
                        placed[j][k] = cur;
                        for(int l = 0 ; l < 4 ; l ++){
                            int ny = j + dy[l];
                            int nx = k + dx[l];
                            if(!OOB(ny, nx)){
                                for(int like : set[cur]){
                                    score[ny][nx][like] += 5;
                                }
                            }
                        }
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
        }

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                int cur = placed[i][j];
                int sum = 0;
                for(int k= 0 ; k < 4 ; k ++){
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(!OOB(ny, nx) && Rset[cur].contains(placed[ny][nx])) sum ++;
                }
                if(sum > 0){
                    answer += Math.pow(10, sum - 1);
                }
            }
        }
        System.out.println(answer);
    }
}
