import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #16920 확장 게임
class Point{
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int N, M, P, blank = 0;

    static int[][] map;

    static Queue<Point>[] Q;

    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 }, len;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }

    static boolean isAllQueueEmpty(Queue<Point>[] Q){
        for(int i = 0 ; i < P ; i ++){
            if(!Q[i].isEmpty()) return false;
        }
        return true;
    }

    static int moveBFS(int num){
        Queue<Point> curQ = Q[num];
        int L = 0;
        while(!curQ.isEmpty()) {
            int curLen = curQ.size();
            for(int j = 0 ; j < curLen ; j ++) {
                Point tmp = curQ.poll();
                for (int i = 0 ; i < 4; i ++) {
                    int ny = tmp.y + dy[i];
                    int nx = tmp.x + dx[i];
                    if (!OOB(ny, nx) && map[ny][nx] == 0) {
                        map[ny][nx] = num + 1;
                        curQ.offer(new Point(ny, nx));
                    }
                }
            }
            if(++L == len[num]){
                break;
            }
        }
        if(curQ.isEmpty()){
            return -1;
        }
        return num;
    }
    static void BFS(){
        Queue<Integer> moveQ = new LinkedList<>();
        for(int i = 0 ; i < P ; i ++){
            moveQ.offer(i);
        }

        while(!moveQ.isEmpty()){
            int ret = moveBFS(moveQ.poll());
            if(ret != -1) moveQ.offer(ret);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        P = Integer.parseInt(st.nextToken());
        len = new int[P];
        Q = new Queue[P];
        for(int i = 0 ; i < P ; i ++){
            Q[i] = new LinkedList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < P ; i ++){
            len[i] = Integer.parseInt(st.nextToken());
        }
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                char cur = input.charAt(j);
                if (Character.isDigit(cur)) {
                    Q[cur - '0' - 1].add(new Point(i, j));
                    map[i][j] = cur - '0';
                } else if (cur == '#') {
                    map[i][j] = -1;
                }
                else{
                    blank ++;
                }
            }
        }

        BFS();
        int[] answer = new int[P];
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] > 0){
                    answer[map[i][j] - 1] ++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < P ; i ++){
            sb.append(answer[i] + " ");
        }
        System.out.println(sb);
    }
}
