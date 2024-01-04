import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #16113 시그널
public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    static int N;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < 5 && x >= 0 && x < N / 5) return false;
        return true;
    }

    static int getNumber(int sum, int x){
        if(sum == 5) return 1;
        else if(sum == 13) return 8;
        else if(sum == 7) return 7;
        else if(sum == 9) return 4;
        else if(sum == 12){
            if(map[2][x + 1] != '#') return 0;
            else{
                if(map[1][x + 2] == '#') return 9;
                else return 6;
            }
        }
        else if(sum == 11){
            if(map[3][x] == '#') return 2;
            else{
                if(map[1][x + 2] == '#') return 3;
                else return 5;
            }
        }
        return -1;
    }

    static int DFS(int y, int x){
        int sum = 1;
        visited[y][x] = true;

        for(int i = 0 ; i < 4 ; i ++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!OOB(ny, nx) && map[ny][nx] == '#' && !visited[ny][nx]){
                sum += DFS(ny, nx);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[5][N / 5];
        visited = new boolean[5][N / 5];

        String input = br.readLine();
        for(int i = 0 ; i < 5 ; i ++){
            for(int j = 0 ; j < N / 5 ; j ++){
                map[i][j] = input.charAt(N / 5 * i + j);
            }
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N / 5 ; i ++){
            if(map[0][i] == '#' && !visited[0][i]){
                sb.append(getNumber(DFS(0, i), i));
            }
        }

        System.out.println(sb);

    }
}
