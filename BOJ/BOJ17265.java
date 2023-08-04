import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #17265 나의 인생에는 수학과 함께 
class Point{
    int y, x, num, opr;
    public Point(int y, int x, int num, int opr){
        this.y = y;
        this.x = x;
        this.num = num;
        this.opr = opr;
    }
}
public class Main {
    static int[][] map, visitedMax, visitedMin;
    static int N;
    static int[] dx = { 1, 0 }, dy = { 0, 1 };

    static boolean OOB(int y , int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        else return true;
    }
    static void BFSMax(){
        Queue<Point> Q = new LinkedList<>();
        Q.offer(new Point(0, 0, map[0][0], -1));
        visitedMax[0][0] = map[0][0];
        while(!Q.isEmpty()){
            Point tmp = Q.poll();
            if(visitedMax[tmp.y][tmp.x] > tmp.num) continue;
            for(int i = 0 ; i < 2 ; i ++){
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                int nextNum = tmp.num;
                int nextOpr = -1;
                if(!OOB(ny, nx)){
                    if(tmp.opr != -1){
                        if(tmp.opr == 10){
                            nextNum += map[ny][nx];
                            nextOpr = -1;
                        }
                        else if(tmp.opr == 11){
                            nextNum -= map[ny][nx];
                            nextOpr = -1;
                        }
                        else {
                            nextNum *= map[ny][nx];
                            nextOpr = -1;
                        }
                    }
                    else{
                        nextOpr = map[ny][nx];
                    }
                    if(visitedMax[ny][nx] < nextNum){
                        Q.offer(new Point(ny, nx, nextNum, nextOpr));
                        visitedMax[ny][nx] = nextNum;
                    }
                }
            }
        }
    }

    static void BFSMin(){
        Queue<Point> Q = new LinkedList<>();
        Q.offer(new Point(0, 0, map[0][0], -1));
        visitedMin[0][0] = map[0][0];
        while(!Q.isEmpty()){
            Point tmp = Q.poll();
            if(visitedMin[tmp.y][tmp.x] < tmp.num) continue;
            for(int i = 0 ; i < 2 ; i ++){
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                int nextNum = tmp.num;
                int nextOpr = -1;
                if(!OOB(ny, nx)){
                    if(tmp.opr != -1){
                        if(tmp.opr == 10){
                            nextNum += map[ny][nx];
                            nextOpr = -1;
                        }
                        else if(tmp.opr == 11){
                            nextNum -= map[ny][nx];
                            nextOpr = -1;
                        }
                        else {
                            nextNum *= map[ny][nx];
                            nextOpr = -1;
                        }
                    }
                    else{
                        nextOpr = map[ny][nx];
                    }
                    if(visitedMin[ny][nx] > nextNum){
                        Q.offer(new Point(ny, nx, nextNum, nextOpr));
                        visitedMin[ny][nx] = nextNum;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visitedMax = new int[N][N];
        visitedMin = new int[N][N];
        for(int i = 0 ; i < N ; i ++) {
            Arrays.fill(visitedMin[i], 1000_000_000);
            Arrays.fill(visitedMax[i], -1000_000_000);
        }
        StringTokenizer st;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                String tmp = st.nextToken();
                if(tmp.equals("+")){
                    map[i][j] = 10;
                }
                else if(tmp.equals("-")){
                    map[i][j] = 11;
                }
                else if(tmp.equals("*")){
                    map[i][j] = 12;
                }
                else {
                    map[i][j] = Integer.parseInt(tmp);
                }
            }
        }
        BFSMax();
        BFSMin();
        System.out.println(visitedMax[N - 1][N - 1] + " " + visitedMin[N - 1][N - 1]);
    }
}
