import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1445 일요일 아침의 데이트
class Point{
    int y, x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}

class Move implements Comparable<Move>{
    Point p;
    int pt, pb;
    public Move(Point p, int pt, int pb){
        this.p = p;
        this.pt = pt;
        this.pb = pb;
    }
    @Override
    public int compareTo(Move o){
        if(this.pt == o.pt) return this.pb - o.pb;
        else return this.pt - o.pt;
    }
}
public class Main {
    static int N, M;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static char[][] map;
    static int[][] vpt, vpb;
    static Point start, end;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        else return true;
    }

    static boolean isPassBy(int y, int x){
        int ny, nx;
        for(int i = 0 ; i < 4 ; i ++){
            ny = y + dy[i];
            nx = x + dx[i];
            if(!OOB(ny, nx) && map[ny][nx] == 'g') return true;
        }
        return false;
    }


    static boolean dijkstra(){
        PriorityQueue<Move> pQ = new PriorityQueue<>();
        vpt[start.y][start.x] = 0;
        vpb[start.y][start.x] = 0;
        pQ.offer(new Move(start, 0, vpb[start.y][start.x]));
        while(!pQ.isEmpty()){
            Move tmp = pQ.poll();
            if(tmp.p.y == end.y && tmp.p.x == end.x) return true;
            if(tmp.pt > vpt[tmp.p.y][tmp.p.x]) continue;
            else if(tmp.pt == vpt[tmp.p.y][tmp.p.x] && tmp.pb > vpb[tmp.p.y][tmp.p.x]) continue;
            if(isPassBy(tmp.p.y, tmp.p.x) && map[tmp.p.y][tmp.p.x] == '.') tmp.pb ++;
            for(int i = 0 ; i < 4 ; i ++){
                int ny = tmp.p.y + dy[i];
                int nx = tmp.p.x + dx[i];
                if(OOB(ny, nx)) continue;
                int npt = tmp.pt;
                int npb = tmp.pb;
                if(map[ny][nx] == 'g') npt ++;
                if(npt > vpt[ny][nx] || (npt == vpt[ny][nx] && npb >= vpb[ny][nx])) continue;
                vpt[ny][nx] = npt;
                vpb[ny][nx] = npb;
                pQ.offer(new Move(new Point(ny, nx), npt, npb));
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        vpt = new int[N][M];
        vpb = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            Arrays.fill(vpt[i], Integer.MAX_VALUE);
            Arrays.fill(vpb[i], Integer.MAX_VALUE);
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                char tmp = input.charAt(j);
                map[i][j] = tmp;
                if(tmp == 'S'){
                    start = new Point(i, j);
                }
                else if(tmp == 'F'){
                    end = new Point(i, j);
                }
            }
        }
        dijkstra();
        System.out.println(vpt[end.y][end.x] + " " + vpb[end.y][end.x]);
    }
}
