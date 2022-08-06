import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #17472 다리 만들기 2
class Point{
	int x;
	int y;
	int d; // 방향
	public Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
class Edge implements Comparable<Edge>{
	int end;
	int cost;
	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static int[][] map ;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int N, M, landNum = 2; 
	static ArrayList<ArrayList<Point>> list = new ArrayList<>();
	static ArrayList<ArrayList<Edge>> MST = new ArrayList<>();
	
	static void DFS(int x, int y) {
		map[y][x] = landNum;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if( yy >= 0 && yy < N && xx >= 0 && xx < M ) { 
				if(map[yy][xx] == 1) {
					DFS(xx,yy);
				}
				else if(map[yy][xx] == 0) {
					list.get(landNum).add(new Point(x,y,i)); // 섬의 가장자리 지점 저장
				}
			}
		}
	}
	
	static int solution() {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		int answer = 0;
		list.add(new ArrayList<>()); // 0
		list.add(new ArrayList<>()); // 1
		MST.add(new ArrayList<>());
		MST.add(new ArrayList<>());
		
		for(int i = 0 ; i < N ; i ++) { // 섬 분류
			for(int k = 0 ; k < M ; k ++) {
				if(map[i][k] == 1) {
					list.add(new ArrayList<>());
					MST.add(new ArrayList<>());
					DFS(k,i);
					landNum ++;
				}
			}
		}
		for(int i = 2; i < landNum ; i++) {
			for(Point x : list.get(i)) {
				int length = 0;
				int dir = x.d;
				int xx = x.x + dx[dir];
				int yy = x.y + dy[dir];
				while(true) {
					if(map[yy][xx] != 0) { // 섬을 만나면
						if(length != 1) MST.get(i).add(new Edge(map[yy][xx], length));
						break;
					}
					else {
						length ++;
						xx = xx + dx[dir];
						yy = yy + dy[dir];
						if(xx >= M || xx < 0 || yy >= N || yy < 0) break;
					}
				}
			}
		}
		// MST
		boolean[] ch = new boolean[landNum];
		pQ.offer(new Edge(2, 0));
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(ch[tmp.end]) continue;
			answer += tmp.cost;
			ch[tmp.end] = true;
			for(Edge x : MST.get(tmp.end)) {
				pQ.offer(x);
			}
		}
		for(int i = 2 ; i < landNum ; i ++) {
			if(ch[i] == false) return -1;
		}
		return answer;
		
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution());
	}
}
