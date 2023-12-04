import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #16959 체스판 여행 1
class Chess{
    int num, y, x, piece;

    public Chess(int piece, int num, int y, int x) {
        this.num = num; // 현재까지 이동 완료한 발판 번호
        this.y = y;
        this.x = x;
        this.piece = piece; // 0 = 나이트, 1 = 비숍, 2 = 룩
    }
}

public class Main {
    static int[][] map;
    static int [][][] visited;
    static int[] kdy = { -2, -1, 1, 2, 2, 1, -1, -2 }, kdx = { 1, 2, 2, 1, -1, -2, -2, -1 }, bdy = { -1, 1, 1, -1 }, bdx = { 1, 1, -1, -1 }, rdy = { -1, 0, 1, 0 }, rdx = { 0, 1, 0, -1 };
    static int N;
    static Queue<Chess> Q = new LinkedList<>();

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        return true;
    }

    static void move(Chess cur){
        if(cur.piece == 0){
            for(int i = 0 ; i < 8 ; i ++){
                int ny = cur.y + kdy[i];
                int nx = cur.x + kdx[i];
                if(!OOB(ny, nx) && visited[cur.piece][ny][nx] < cur.num){
                    int nnum = cur.num;
                    if(map[ny][nx] == cur.num + 1) nnum ++;
                    visited[cur.piece][ny][nx] = nnum;
                    Q.offer(new Chess(cur.piece, nnum, ny, nx));
                }
            }
        }
        else if(cur.piece == 1){
            for(int i = 1 ; i < N ; i ++){
                for(int j = 0 ; j < 4 ; j ++) {
                    int ny = cur.y + bdy[j] * i;
                    int nx = cur.x + bdx[j] * i;
                    if(!OOB(ny, nx) && visited[cur.piece][ny][nx] < cur.num){
                        int nnum = cur.num;
                        if(map[ny][nx] == cur.num + 1) nnum ++;
                        visited[cur.piece][ny][nx] = nnum;
                        Q.offer(new Chess(cur.piece, nnum, ny, nx));
                    }
                }
            }
        }
        else{
            for(int i = 1 ; i < N ; i ++){
                for(int j = 0 ; j < 4 ; j ++) {
                    int ny = cur.y + rdy[j] * i;
                    int nx = cur.x + rdx[j] * i;
                    if(!OOB(ny, nx) && visited[cur.piece][ny][nx] < cur.num){
                        int nnum = cur.num;
                        if(map[ny][nx] == cur.num + 1) nnum ++;
                        visited[cur.piece][ny][nx] = nnum;
                        Q.offer(new Chess(cur.piece, nnum, ny, nx));
                    }
                }
            }
        }
    }

    static void change(Chess cur){
        for(int i = 0 ; i < 3 ; i ++){
            if(i == cur.piece) continue;
            if(visited[i][cur.y][cur.x] < cur.num){
                visited[i][cur.y][cur.x] = cur.num;
                Q.offer(new Chess(i, cur.num, cur.y, cur.x));
            }
        }
    }
    public static int BFS(){
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                Chess cur = Q.poll();
                if(cur.num == N * N) return L;
                change(cur);
                move(cur);
            }
            L ++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        visited = new int[3][N][N];

        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    for(int k = 0 ; k < 3 ; k ++) {
                        Q.offer(new Chess(k, 1, i, j));
                        visited[k][i][j] = 1;
                    }
                }
            }
        }
        System.out.println(BFS());
    }
}
