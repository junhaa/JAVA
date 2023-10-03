import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2580 스도쿠
class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int map[][] = new int[9][9];

    static Point findNext(Point p) {
        if(map[p.y][p.x] == 0) return p;
        int j = p.x;
        for (int i = p.y; i < 9; i++) {
            for (; j < 9; j++) {
                if (map[i][j] == 0) {
                    return new Point(i, j);
                }
            }
            j = 0;
        }
        return null;
    }

    static void sudoku(Point p) {
        for (int k = 1; k <= 9; k++) {
            if (check(p, k)) {
                map[p.y][p.x] = k;
                Point next = findNext(p);
                if(next == null){
                    System.out.println(printMap());
                    System.exit(0);
                }
                sudoku(next);
            }
        }
        map[p.y][p.x] = 0;
    }

    static boolean check(Point p, int val) {
        // 가로 체크
        for(int i = 0 ; i < 9 ; i ++){
            if(i == p.x) continue;
            if(map[p.y][i] == val) return false;
        }

        // 세로 체크
        for(int i = 0 ; i < 9 ; i ++){
            if(i == p.y) continue;
            if(map[i][p.x] == val) return false;
        }

        // 사각형 체크
        int ny = (p.y / 3) * 3;
        int nx = (p.x / 3) * 3;
        for(int i = ny ; i < ny + 3 ; i ++){
            for(int j = nx ; j < nx + 3 ; j ++){
                if(map[i][j] == val) return false;
            }
        }
        return true;
    }

    static StringBuilder printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 9 ; i ++){
            for(int j = 0 ; j < 9 ; j ++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Point p = findNext(new Point(0, 0));
        if(p == null){
            StringBuilder sb = printMap();
            System.out.println(sb);
        }
        sudoku(p);
    }
}
