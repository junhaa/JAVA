import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #1348 주차장
class Car {
    int y, x, num;

    public Car(int y, int x, int num) {
        this.y = y;
        this.x = x;
        this.num = num;
    }
}

class Edge implements Comparable<Edge> {
    int des, cost;

    public Edge(int des, int cost) {
        this.des = des;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    static int R, C;
    static int[] aarr, barr, dx = { 0, 1, 0, -1 } , dy = { -1, 0, 1, 0 };
    static ArrayList<Edge>[] edges;
    static int cCnt = 0;
    static int pCnt = 0;
    static int[][] map;
    static boolean[] visited;

    static boolean DFS(int cur, int limit){
        visited[cur] = true;
        for(Edge next : edges[cur]){
            if(next.cost > limit) break;
            if(barr[next.des] == -1 || !visited[barr[next.des]] && DFS(barr[next.des], limit)){
                aarr[cur] = next.des;
                barr[next.des] = cur;
                return true;
            }
        }
        return false;
    }

    static int binarySearch(int max){
        int min = Integer.MAX_VALUE;
        int lt = 0;
        int rt = max;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            aarr = new int[cCnt];
            barr = new int[pCnt];
            Arrays.fill(aarr, -1);
            Arrays.fill(barr, -1);
            int answer = 0;
            for(int i = 0 ; i < cCnt ; i ++){
                if(aarr[i] == -1){
                    visited = new boolean[cCnt];
                    if(DFS(i, mid)) answer ++;
                }
            }
            if(answer == cCnt){
                min = Math.min(min, mid);
                rt = mid - 1;
            }
            else{
                lt = mid + 1;
            }
        }
        return min;
    }
    static boolean OOB(int y, int x){
        if(y >= 0 && y < R && x >= 0 && x < C) return false;
        return true;
    }
    static int BFS(Queue<Car> Q){
        int max = Integer.MIN_VALUE;
        boolean[][][] carVisited = new boolean[cCnt][R][C];
        for(int i = 0 ; i < cCnt ; i ++){
            Car cur = Q.poll();
            carVisited[cur.num][cur.y][cur.x] = true;
            Q.offer(cur);
        }

        int L = 0;
        while(!Q.isEmpty()) {
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                Car cur = Q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = cur.y + dy[j];
                    int nx = cur.x + dx[j];
                    if (!OOB(ny, nx) && !carVisited[cur.num][ny][nx] && map[ny][nx] != -2) {
                        if (map[ny][nx] >= 0) {
                            edges[cur.num].add(new Edge(map[ny][nx], L + 1));
                            max = Math.max(max, L + 1);
                        }
                        Q.offer(new Car(ny, nx, cur.num));
                        carVisited[cur.num][ny][nx] = true;
                    }
                }
            }
            L ++;
        }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Queue<Car> q = new LinkedList<>();
        map = new int[R][C];
        String input;
        // map 입력
        for (int i = 0; i < R; i++) {
            Arrays.fill(map[i], -1);
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                char cur = input.charAt(j);
                if (cur == 'X') {
                    map[i][j] = -2;
                } else if (cur == 'C') {
                    q.offer(new Car(i, j, cCnt++));
                } else if (cur == 'P') {
                    map[i][j] = pCnt++;
                }
            }
        }
        if(cCnt == 0){
            System.out.println(0);
            return;
        }
        edges = new ArrayList[cCnt];
        for(int i = 0 ; i < cCnt ; i ++){
            edges[i] = new ArrayList<>();
        }

        int max = BFS(q);

        for(int i = 0 ; i < cCnt; i ++){
            Collections.sort(edges[i]);
        }
        int res = binarySearch(max);
        if(res == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
    }
}
