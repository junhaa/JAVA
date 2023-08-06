import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #18428 감시 피하기 
class Point{
    int y, x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}
public class Main {
    static int N;
    static char[][] map;

    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static boolean OOB(int y, int x){
        return !(y >= 0 && y < N && x >= 0 && x < N);
    }
    static boolean canAvoid(ArrayList<Point> teacher){
        for(int i = 0 ; i < teacher.size() ; i ++){
            Point tmp = teacher.get(i);
            for(int j = 0 ; j < 4 ; j ++){
                int ny = tmp.y + dy[j];
                int nx = tmp.x + dx[j];
                while(true){
                    if(!OOB(ny, nx)){
                        if(map[ny][nx] == 'S') {
                            return false;
                        }
                        else if(map[ny][nx] == 'B'){
                            break;
                        }
                        ny += dy[j];
                        nx += dx[j];
                    }
                    else break;
                }
            }
        }
        return true;
    }


    static Point toPoint(int idx){
        return new Point(idx / N, idx % N);
    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        map = new char[N][N];
        ArrayList<Point> teacher = new ArrayList<>();
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'T'){
                    teacher.add(new Point(i , j));
                }
            }
        }

        Point p1, p2, p3;
        for(int i = 0 ; i < N * N ; i ++){
            p1 = toPoint(i);
            if(map[p1.y][p1.x] != 'X') continue;
            for(int j = i + 1 ; j < N * N ; j ++){
                p2 = toPoint(j);
                if(map[p2.y][p2.x] != 'X') continue;
                for(int k = j + 1 ; k < N * N ; k ++){
                    p3 = toPoint(k);
                    if(map[p3.y][p3.x] != 'X') continue;
                    map[p1.y][p1.x] = 'B';
                    map[p2.y][p2.x] = 'B';
                    map[p3.y][p3.x] = 'B';
                    if(canAvoid(teacher)){
                        System.out.println("YES");
                        return;
                    }
                    map[p1.y][p1.x] = 'X';
                    map[p2.y][p2.x] = 'X';
                    map[p3.y][p3.x] = 'X';
                }
            }
        }
        System.out.println("NO");
    }
}
