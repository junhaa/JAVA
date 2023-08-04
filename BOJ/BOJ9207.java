import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #9207 페그 솔리테어 
class Point{
    int y, x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}
public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };
    static char[][] map;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < 5 && x >= 0 && x < 9) return false;
        else return true;
    }

    static Point canMove(int dir, int y, int x, char[][] lastMap){
        y += dy[dir];
        x += dx[dir];
        if(!OOB(y, x) && lastMap[y][x] == 'o'){
            y += dy[dir];
            x += dx[dir];
            if(!OOB(y, x) && lastMap[y][x] == '.') return new Point(y, x);
        }
        return null;
    }
    static char[][] copyMap(char[][] lastMap){
        char[][] tmpMap = new char[5][9];
        for(int i = 0 ; i < 5 ; i ++) System.arraycopy(lastMap[i], 0, tmpMap[i], 0, 9);
        return tmpMap;
    }
    static void delete(char[][] tmpMap, int dir, Point p){
        dir += 2;
        dir %= 4;
        while(true) {
            int ny = p.y + dy[dir];
            int nx = p.x + dx[dir];
            if(tmpMap[ny][nx] == 'o') {
                tmpMap[ny][nx] = '.';
                break;
            }
        }
    }


    static int backTracking(char[][] lastMap, int L){
        int max = L;
        for(int i = 0 ; i < 5 ; i ++){
            for(int j = 0 ; j < 9 ; j ++){
                if(lastMap[i][j] == 'o'){
                    for(int k = 0 ; k < 4 ; k ++){
                        Point next = canMove(k, i, j, lastMap);
                        if(next != null){
                            char[][] tmpMap = copyMap(lastMap);
                            tmpMap[i][j] = '.';
                            delete(tmpMap, k, next);
                            tmpMap[next.y][next.x] = 'o';
                            max = Math.max(max, backTracking(tmpMap, L + 1));
                        }
                    }
                }
            }
        }
        return max;
    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < N ; k ++){
            map = new char[5][9];
            String input;
            int cnt = 0;
            for(int i = 0 ; i < 5 ; i ++){
                input = br.readLine();
                for(int j = 0 ; j < 9 ; j ++){
                    map[i][j] = input.charAt(j);
                    if(map[i][j] == 'o') cnt ++;
                }
            }
            if(k < N - 1) br.readLine();
            int result = backTracking(map, 0);
            sb.append(cnt - result + " " + result + "\n");
        }
        System.out.println(sb);
    }
}
