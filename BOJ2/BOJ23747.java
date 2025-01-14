import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #23747 와드
class Point{
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {
    private static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 }; // U, R, D, L
    private static int R, C;
    private static int[][] visited; // 0 = false, 1 = true, 2 = ward, 3 = wardCheck
    private static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new int[R][C];
        map = new char[R][C];

        for(int i = 0 ; i < R ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < C ; j ++){
                char c = input.charAt(j);
                map[i][j] = c;
            }
        }
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken()) - 1;
        String command = br.readLine();

        Point lastPoint = move(y, x, command);
        checkMapWards();
        addLastPointSight(lastPoint);
        System.out.println(printMap());

    }

    private static void addLastPointSight(Point lastPoint){
        visited[lastPoint.y][lastPoint.x] = 3;
        for(int i = 0 ; i < 4 ; i ++){
            int ny = lastPoint.y + dy[i];
            int nx = lastPoint.x + dx[i];
            if(isInBound(ny, nx)){
                visited[ny][nx] = 3;
            }
        }
    }

    private static String printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < R ; i ++){
            for(int j = 0 ; j < C ; j ++){
                sb.append(visited[i][j] == 3 ? '.' : '#');
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static void checkMapWards(){
        for(int i = 0 ; i < R; i ++){
            for(int j = 0 ; j < C ; j ++){
                if(visited[i][j] == 2){
                    checkWardBFS(i, j, map[i][j]);
                }
            }
        }
    }

    private static void checkWardDFS(int y, int x, char c){
        visited[y][x] = 3;
        for(int i = 0 ; i < 4 ; i ++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(visited[ny][nx] == 3){
                ny = ny;
            }
            if(isInBound(ny, nx) && map[ny][nx] == c && visited[ny][nx] != 3){
                checkWardDFS(ny, nx, c);
            }
        }
    }

    private static void checkWardBFS(int y, int x, char c){
        Queue<Point> Q = new LinkedList<>();
        Q.add(new Point(y, x));
        visited[y][x] = 3;
        while(!Q.isEmpty()){
            Point cur = Q.poll();
            for(int i = 0 ; i < 4 ; i ++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if(isInBound(ny, nx) && map[ny][nx] == c && visited[ny][nx] != 3){
                    Q.add(new Point(ny, nx));
                    visited[ny][nx] = 3;
                }
            }
        }
    }

    private static boolean isInBound(int y, int x){
        return y >= 0 && y < R && x >= 0 && x < C;
    }

    private static Point move(int y, int x, String command){
        visited[y][x] = 1;
        for(char moveC : command.toCharArray()){
            int midx = getMoveIdx(moveC);
            if(midx == -1){
                visited[y][x] = 2;
                continue;
            }
            y = y + dy[midx];
            x = x + dx[midx];
        }
        return new Point(y, x);
    }

    private static int getMoveIdx(char moveC){
        switch (moveC){
            case 'W':
                return -1;
            case 'U':
                return 0;
            case 'R':
                return 1;
            case 'D':
                return 2;
            case 'L':
                return 3;
        }
        return -2;
    }
}
