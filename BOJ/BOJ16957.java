import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point implements Comparable<Point>{
    int y, x, num;
    public Point(int y, int x, int num){
        this.y = y;
        this.x = x;
        this.num = num;
    }
    @Override
    public int compareTo(Point o){
        return o.num - this.num;
    }
}
// BOJ #16957 체스판 위의 공
public class Main{
    static int R, C;
    static int[][] map;
    static int[] balls, dx = { 0, 1, 1, 1, 0, -1, -1, -1 }, dy = { -1, -1, 0, 1, 1, 1, 0, -1,};
    static HashMap<Integer, Integer> Coord = new HashMap<>();
    static boolean OOB(int y, int x){
        if(y >= 0 && y < R && x >= 0 && x < C) return false;
        else return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Point> blist = new ArrayList<>();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        balls = new int[R * C];
        for(int i = 0 ; i < R ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < C ; j ++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                list.add(map[i][j]);
            }
        }
        Collections.sort(list);
        for(int i = 0 ; i < list.size() ; i ++){
            Coord.put(list.get(i), i);
        }
        Arrays.fill(balls, 1);
        for(int i = 0 ; i < R ; i ++){
            for(int j = 0 ; j < C ; j ++){
                blist.add(new Point(i, j, Coord.get(map[i][j])));
            }
        }
        Collections.sort(blist);
        for(Point cur : blist){
            int min = map[cur.y][cur.x];
            int dir = -1;
            for(int i = 0 ; i < 8 ; i ++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (!OOB(ny, nx) && map[ny][nx] < min) {
                    min = map[ny][nx];
                    dir = i;
                }
            }
            if(dir == -1) continue;
            balls[Coord.get(map[cur.y + dy[dir]][cur.x + dx[dir]])] += balls[cur.num];
            balls[cur.num] = 0;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < R ; i ++){
            for(int j = 0 ; j < C ; j ++){
                sb.append(balls[Coord.get(map[i][j])] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
