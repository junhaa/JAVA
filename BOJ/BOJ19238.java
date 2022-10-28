import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #19238 스타트 택시
class Point{
	int x, y;
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}

class Passenger implements Comparable<Passenger>{
	Point pas;
	Point des;
	int dis;
	public Passenger(Point pas, Point des) {
		this.pas = pas;
		this.des = des;
		dis = -1;
	}
	public void setDis(int dis) {
		this.dis = dis;
	}
	@Override
	public int compareTo(Passenger o) {
		if(this.dis == o.dis) {
			if(this.pas.y == o.pas.y) {
				return this.pas.x - o.pas.x;
			}
			else return this.pas.y - o.pas.y;
		}
		else return this.dis - o.dis;
	}
	
}

public class Main {
	static int fuel;
	static int[][] map;
	static int[][] pas;
	static Point taxi;
	static ArrayList<Point> list = new ArrayList<>();
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int D = -1, idx = 0;
	static Point getDistance(int N) { 
		boolean[][] visited = new boolean[N][N];
		Queue<Point> Q = new LinkedList<>();
		Q.offer(taxi);
		visited[taxi.y][taxi.x] = true;
		Point res = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		int L = 0;
		boolean flag = false;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(pas[tmp.y][tmp.x] != 0) {
					flag = true;
					if(res.y == tmp.y && res.x > tmp.x) {
						idx = pas[tmp.y][tmp.x] - 1;
						res.x = tmp.x;
					}
					else if(res.y > tmp.y) {
						idx = pas[tmp.y][tmp.x] - 1;
						res.y = tmp.y;
						res.x = tmp.x;
					}
				}
				for(int k = 0 ; k < 4 ; k ++) {
					int xx = tmp.x + dx[k];
					int yy = tmp.y + dy[k];
					if(xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx] && map[yy][xx] == 0) {
 						visited[yy][xx] = true;
 						Q.offer(new Point(yy, xx));
 					}
				}
			}
			if(flag) { 
				D = L;
				pas[res.y][res.x] = 0;
				return res;
			}
			L++;
		}
		return null;
		
 		/*
		for(int i = 0 ; i < list.size() ; i ++) {
 			Point des = list.get(i).pas;
 			boolean[][] visited = new boolean[N][N];
 			Queue<Point> Q = new LinkedList<>();
 			Q.offer(taxi);
 			visited[taxi.y][taxi.x] = true;
 			int L = 0;
 			while(!Q.isEmpty()) {
 				int length = Q.size();
 				for(int j = 0 ; j < length ; j ++) {
	 				Point tmp = Q.poll();
	 				if(tmp.x == des.x && tmp.y == des.y) {
	 					list.get(i).setDis(L);
	 					break;
	 				}
	 				for(int k = 0 ; k < 4 ; k ++) {
	 					int xx = tmp.x + dx[k];
	 					int yy = tmp.y + dy[k];
	 					if(xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx] && map[yy][xx] == 0) {
	 						visited[yy][xx] = true;
	 						Q.offer(new Point(yy, xx));
	 					}
	 				}
 				}
 				L ++;
 			}
		}
		*/
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		pas = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			Point tmp1 = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			Point tmp2 = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			pas[tmp1.y][tmp1.x] = i + 1;
			list.add(tmp2);
		}
		for(int l = 0 ; l < M; l ++) {
			D = -1;
			idx = 0;
			Point low = getDistance(N);
			if(low == null) {
				System.out.println("-1");
				return;
			}
			if(fuel < D) {
				System.out.println("-1");
				return;
			}
			fuel -= D;
			taxi.x = low.x;
			taxi.y = low.y;
			int L = 0;
			Queue<Point> Q = new LinkedList<>();
			Q.offer(taxi);
			boolean[][] visited = new boolean[N][N];
			visited[taxi.y][taxi.x] = true;
			boolean flag = false;
			while(!Q.isEmpty()) {
				int length = Q.size();
				for(int i = 0 ; i < length ; i ++) {
					Point cur = Q.poll();
					if(cur.x == list.get(idx).x && cur.y == list.get(idx).y) {
						if(fuel < L) {
							System.out.println("-1");
							return;
						}
						fuel -= L;
						fuel += L * 2;
						taxi.x = cur.x;
						taxi.y = cur.y;
						flag = true;
						break;
					}
					for(int j = 0 ; j < 4 ; j ++) {
						int xx = cur.x + dx[j];
						int yy = cur.y + dy[j];
						if(xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx] && map[yy][xx] == 0) {
							Q.offer(new Point(yy, xx));
							visited[yy][xx] = true;
						}
					}
				}
				if(flag) { 
					break;
				}
				L ++;
			}
			if(!flag) {
				System.out.println("-1");
				return;
			}
		}
		System.out.println(fuel);
	}
}
