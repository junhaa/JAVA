import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #4179 불!
class Point{
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {
    static int R, C;
    static char[][] map;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static boolean OOB(int y, int x){
        if(y >= 0 && y < R && x >= 0 && x < C) return false;
        return true;
    }

    static int BFS(Queue<Point> jQ, Queue<Point> fQ){
        int L = 0;
        while(!jQ.isEmpty()){
            int flen = fQ.size();
            for(int i = 0 ; i < flen ; i ++){
                Point ftmp = fQ.poll();
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = ftmp.y + dy[j];
                    int nx = ftmp.x + dx[j];
                    if(!OOB(ny, nx) && map[ny][nx] == '.'){
                        fQ.offer(new Point(ny, nx));
                        map[ny][nx] = 'F';
                    }
                }
            }
            int jlen = jQ.size();
            for(int i = 0 ; i < jlen ; i ++){
                Point jtmp = jQ.poll();
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = jtmp.y + dy[j];
                    int nx = jtmp.x + dx[j];
                    if(OOB(ny, nx)) return L + 1;
                    else if(map[ny][nx] == '.' && !visited[ny][nx]){
                        jQ.offer(new Point(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
            L ++;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        Queue<Point> jQ = new LinkedList<>();
        Queue<Point> fQ = new LinkedList<>();
        for(int i = 0 ; i < R ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < C ; j ++){
                char tmp = input.charAt(j);
                if(tmp == 'J'){
                    jQ.offer(new Point(i, j));
                    visited[i][j] = true;
                    tmp = '.';
                }
                else if(tmp == 'F'){
                    fQ.offer(new Point(i, j));
                }
                map[i][j] = tmp;
            }
        }
        int answer = BFS(jQ, fQ);
        if(answer == -1){
            System.out.println("IMPOSSIBLE");
        }
        else System.out.println(answer);
    }
}
