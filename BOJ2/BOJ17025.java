import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17025 Icy Perimeter
public class Main {
    static int N;
    static boolean[][] isBlob;
    static boolean[][] visited;
    static int[] dy = { -1, 0, 1, 0 }, dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxCheck = Integer.MIN_VALUE;
        int minBound = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        isBlob = new boolean[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < N ; j ++){
                if(input.charAt(j) == '#'){
                    isBlob[i][j] = true;
                }
            }
        }

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                if(!visited[i][j] && isBlob[i][j]){
                    int[] res = main.dfs(i, j);
                    if(maxCheck < res[0]){
                        maxCheck = res[0];
                        minBound = res[1];
                    }
                    else if(maxCheck == res[0] && minBound > res[1]){
                        minBound = res[1];
                    }
                }
            }
        }

        System.out.println(maxCheck + " " + minBound);
    }

    public int[] dfs(int cy, int cx){
        int boundCnt = 0;
        int checkCnt = 1;
        visited[cy][cx] = true;
        for(int i = 0 ; i < 4 ; i ++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(!checkBound(ny, nx) || !isBlob[ny][nx]) boundCnt ++;
            else if(!visited[ny][nx] && isBlob[ny][nx]){
                int[] ret = dfs(ny, nx);
                boundCnt += ret[1];
                checkCnt += ret[0];
            }
        }

        int[] result = new int[2];

        result[0] = checkCnt;
        result[1] = boundCnt;
        return result;
    }

    public boolean checkBound(int y, int x){
        return y >= 0 &&  y < N && x >= 0 && x < N;
    }
}
