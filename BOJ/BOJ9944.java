import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #9944 NxM 보드 완주하기
class Point{
    int y, x, cnt;
    public Point(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M, cNum = 1, bCnt;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static char[][] copyMap(char[][] lastMap){
        char[][] tmpMap = new char[N][M];
        for(int i = 0 ; i < N ; i ++) System.arraycopy(lastMap[i], 0, tmpMap[i], 0, M);
        return tmpMap;
    }
    static boolean OOB(int ny, int nx){
        if(ny >= 0 && ny < N && nx >= 0 && nx < M) return false;
        else return true;
    }

    static Point fillColor(char[][] tmpMap, int dir, int y, int x, int cnt){
        tmpMap[y][x] = '-';
        cnt ++;
        while(!OOB(y + dy[dir], x + dx[dir]) && tmpMap[y + dy[dir]][x + dx[dir]] == '.'){
            y += dy[dir];
            x += dx[dir];
            tmpMap[y][x] = '-';
            cnt ++;
        }
        return new Point(y, x, cnt);
    }
    static int backTracking(Point cur, char[][] lastMap, int L){
        if(cur.cnt == bCnt){
            return L;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < 4 ; i ++){
            int ny = cur.y + dy[i];
            int nx = cur.x + dx[i];
            if(!OOB(ny, nx) && lastMap[ny][nx] == '.'){
                char[][] tmpMap = copyMap(lastMap);
                Point nextP = fillColor(tmpMap, i, ny, nx, cur.cnt);
                min = Math.min(backTracking(nextP, tmpMap, L + 1), min);
            }
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input;
        while((input = br.readLine()) != null){
            st = new StringTokenizer(input);
            sb.append("Case " + cNum++ + ": ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            char[][] map = new char[N][M];
            String cur;
            bCnt = 0;
            for(int i = 0 ; i < N ; i ++){
                cur = br.readLine();
                for(int j = 0 ; j < M ; j ++){
                    map[i][j] = cur.charAt(j);
                    if(map[i][j] == '.') bCnt ++;
                }
            }
            int min = Integer.MAX_VALUE;
            for(int i = 0 ; i < N ; i ++){
                for(int j = 0 ; j < M ; j ++){
                    if(map[i][j] == '.'){
                        map[i][j] = '-';
                        min = Math.min(min, backTracking(new Point(i, j, 1), map, 0));
                        map[i][j] = '.';
                    }
                }
            }
            if(min == Integer.MAX_VALUE) sb.append(-1 + "\n");
            else sb.append(min + "\n");
        }
        System.out.println(sb);
    }
}
