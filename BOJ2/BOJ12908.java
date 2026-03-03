import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #12908 텔레포트 3
class Point {
    int x, y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int distance(Point other){
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
public class Main {

    private static int TELEPORT_TIME = 10;
    private static int START_POINT_IDX = 0;
    private static int END_POINT_IDX = 1;
    private static int ALL_POINT_COUNTS = 8;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point[] points = new Point[ALL_POINT_COUNTS];

        StringTokenizer st = new StringTokenizer(br.readLine());
        Point s = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        points[START_POINT_IDX] = s;

        st = new StringTokenizer(br.readLine());
        Point e = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        points[END_POINT_IDX] = e;

        for(int i = 1 ; i <= 3 ; i ++){
            st = new StringTokenizer(br.readLine());
            Point p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Point p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            points[2 * i] = p1;
            points[2 * i + 1] = p2;
        }

        System.out.println(main.solve(points));
    }

    private long solve(Point[] points){
        long[][] time = new long[ALL_POINT_COUNTS][ALL_POINT_COUNTS];

        setBaseTime(time);
        setTeleportTime(time);
        setBasicMoveTime(time, points);
        calcMinTime(time);

        return time[START_POINT_IDX][END_POINT_IDX];
    }


    private void setBaseTime(long[][] time){
        for(int i = 0 ; i < ALL_POINT_COUNTS ; i ++){
            Arrays.fill(time[i], Integer.MAX_VALUE);
            time[i][i] = 0;
        }
    }

    private void setTeleportTime(long[][] time){
        for(int i = 1 ; i <= 3 ; i ++) {
            time[2 * i][2 * i + 1] = TELEPORT_TIME;
            time[2 * i + 1][2 * i] = TELEPORT_TIME;
        }
    }

    private void setBasicMoveTime(long[][] time, Point[] points){
        for(int i = 0 ; i < ALL_POINT_COUNTS ; i ++) {
            for(int j = 0 ; j < ALL_POINT_COUNTS ; j ++) {
                time[i][j] = Math.min(time[i][j], points[i].distance(points[j]));
            }
        }
    }

    private void calcMinTime(long[][] time){
        for(int via = 0 ; via < ALL_POINT_COUNTS ; via ++) {
            for(int from = 0 ; from < ALL_POINT_COUNTS ; from ++) {
                for(int to = 0 ; to < ALL_POINT_COUNTS ; to ++) {
                    time[from][to] = Math.min(time[from][to], time[from][via] + time[via][to]);
                }
            }
        }
    }
}
