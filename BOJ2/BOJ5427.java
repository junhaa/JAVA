import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #5427 불
class Point{
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {
    private static int w, h;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    private static Point startPoint;
    private static Queue<Point> fireQueue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new int[h][w];
            fireQueue = new LinkedList<>();
            for(int i = 0 ; i < h ; i ++){
                String input = br.readLine();
                for(int j = 0 ; j < w ; j ++){
                    char curChar = input.charAt(j);
                    if(curChar == '.'){
                        visited[i][j] = Integer.MAX_VALUE;
                        continue;
                    }
                    if(curChar == '@'){
                        visited[i][j] = Integer.MAX_VALUE;
                        startPoint = new Point(i, j);
                        continue;
                    }
                    if(curChar == '*'){
                        fireQueue.add(new Point(i, j));
                        continue;
                    }
                }
            }

            moveFire();
            int result = escape();
            if(result == -1){
                sb.append("IMPOSSIBLE").append("\n");
            }
            else{
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int escape(){
        Queue<Point> q = new LinkedList<>();
        q.add(startPoint);
        int L = 1;
        while(!q.isEmpty()){
            int len = q.size();
            for(int i = 0 ; i < len ; i ++){
                Point cur = q.poll();
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = cur.y + dy[j];
                    int nx = cur.x + dx[j];
                    if(!isInBound(ny, nx)){
                        return L;
                    }
                    if(visited[ny][nx] > L){
                        q.add(new Point(ny, nx));
                        visited[ny][nx] = 0;
                    }
                }
            }
            L ++;
        }
        return -1;
    }

    private static void moveFire(){
        int L = 1;
        while(!fireQueue.isEmpty()){
            int len = fireQueue.size();
            for(int i = 0 ; i < len ; i ++){
                Point cur = fireQueue.poll();
                for(int j = 0 ; j < 4;  j ++){
                    int ny = cur.y + dy[j];
                    int nx = cur.x + dx[j];
                    if(isInBound(ny, nx) && visited[ny][nx] > L){
                        fireQueue.add(new Point(ny, nx));
                        visited[ny][nx] = L;
                    }
                }
            }
            L ++;
        }
    }

    private static boolean isInBound(int y, int x){
        return y >= 0 && y < h && x >= 0 && x < w;
    }
}
