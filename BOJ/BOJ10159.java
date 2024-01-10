import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10159 저울
public class Main {
    static int N, M;
    static int[][] map;

    static void getDis(){
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                for(int k = 0 ; k < N ; k ++){
                    if(map[j][i] != 0 && map[j][i] == map[i][k]){
                        map[j][k] = map[i][k];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1;
            map[b][a] = -1;
        }

        getDis();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N; i ++){
            int cnt = 0;
            for(int j = 0 ; j < N ; j ++){
                if(map[i][j] == 0) cnt ++;
            }
            sb.append((cnt - 1) + "\n");
        }
        System.out.println(sb);
    }
}
