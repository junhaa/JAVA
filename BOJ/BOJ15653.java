import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #15653 구슬 탈출 4
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Ball{
	Point red;
	Point blue;
	public Ball(Point red, Point blue) {
		this.red = red;
		this.blue = blue;
	}
}
public class Main {
	static int[][] map;
	static int N, M, Ox, Oy;
	static Queue<Ball> Q = new LinkedList<>();
	static boolean visited[][][][];
	
	public static int solution(Point red, Point blue) {
		Q.offer(new Ball(red, blue));
		visited[red.y][red.x][blue.y][blue.x] = true;
		int L = 1;
		boolean B = false; // red 가 먼저 true blue 가 먼저 false
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int j = 0 ; j < length ; j ++) {
			Ball tmp = Q.poll();
				for(int i = 0 ; i < 4 ; i ++) {
					int flag = 0; // 0 기존 1 빨간공 2 파란공
					switch (i) {
					case 0: { // UP
						Point tmp1 = null, tmp2 = null, tmpRed = null, tmpBlue = null;
						if(tmp.red.y < tmp.blue.y) {
							B = true;
							tmp1 = tmp.red;
							tmp2 = tmp.blue;
						}
						else {
							B =false;
							tmp1 = tmp.blue;
							tmp2 = tmp.red;
						}
						int x1 = tmp1.x;
						int y1 = tmp1.y;
						while(true) {
							if(map[y1][x1] == 1) {
								if(B) tmpRed = new Point(x1,++y1);
								else tmpBlue = new Point(x1,++y1);
								break;
							}
							else if(map[y1][x1] == 2) { 
								if(B) { 
									flag = 1;
									break;
								}
								else {
									flag = 2;
									break;
								}
							}
							else y1 --;
						}
						if(flag == 0) map[y1][x1] = 1;
						int x2 = tmp2.x;
						int y2 = tmp2.y;
						while(true) {
							if(map[y2][x2] == 1) {
								if(B) tmpBlue = new Point(x2,++y2);
								else tmpRed = new Point(x2,++y2);
								break;
							}
							else if(map[y2][x2] == 2) { 
								if(B) { 
									flag = 2;
									break;  
								}
								else {
									if(flag == 2) break;
									else return L;
								}
							}
							else y2 --;
						}
						if(map[y1][x1] == 1) map[y1][x1] = 0;
						if(flag == 1) return L;
						if(tmpRed != null && tmpBlue != null) { 
							if(tmpRed.x != tmp.red.x || tmpRed.y != tmp.red.y || tmpBlue.x != tmp.blue.x || tmpBlue.y != tmp.blue.y) { 
								if(!visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x]) {
									visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x] = true;
									Q.offer(new Ball(tmpRed, tmpBlue));
								}
							}
						}
						break;
					}
					case 1: { // RIGHT
						Point tmp1 = null, tmp2 = null, tmpRed = null, tmpBlue = null;
						if(tmp.red.x > tmp.blue.x) {
							B = true;
							tmp1 = tmp.red;
							tmp2 = tmp.blue;
						}
						else {
							B =false;
							tmp1 = tmp.blue;
							tmp2 = tmp.red;
						}
						int x1 = tmp1.x;
						int y1 = tmp1.y;
						while(true) {
							if(map[y1][x1] == 1) {
								if(B) tmpRed = new Point(--x1,y1);
								else tmpBlue = new Point(--x1,y1);
								break;
							}
							else if(map[y1][x1] == 2) { 
								if(B) { 
									flag = 1;
									break;
								}
								else {
									flag = 2;
									break;
								}
							}
							else x1 ++;
						}
						if(flag == 0) map[y1][x1] = 1;
						int x2 = tmp2.x;
						int y2 = tmp2.y;
						while(true) {
							if(map[y2][x2] == 1) {
								if(B) tmpBlue = new Point(--x2,y2);
								else tmpRed = new Point(--x2,y2);
								break;
							}
							else if(map[y2][x2] == 2) { 
								if(B) { 
									flag = 2;
									break;  
								}
								else {
									if(flag == 2) break;
									else return L;
								}
							}
							else x2 ++;
						}
						if(map[y1][x1] == 1) map[y1][x1] = 0;
						if(flag == 1) return L;
						if(tmpRed != null && tmpBlue != null) {
							if(tmpRed.x != tmp.red.x || tmpRed.y != tmp.red.y || tmpBlue.x != tmp.blue.x || tmpBlue.y != tmp.blue.y) {
								if(!visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x]) {
									visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x] = true;
									Q.offer(new Ball(tmpRed, tmpBlue));
								}
							}
						}
						break;
					}
					case 2: { // DOWN
						Point tmp1 = null, tmp2 = null, tmpRed = null, tmpBlue = null;
						if(tmp.red.y > tmp.blue.y) {
							B = true;
							tmp1 = tmp.red;
							tmp2 = tmp.blue;
						}
						else {
							B = false;
							tmp1 = tmp.blue;
							tmp2 = tmp.red;
						}
						int x1 = tmp1.x;
						int y1 = tmp1.y;
						while(true) {
							if(map[y1][x1] == 1) {
								if(B) tmpRed = new Point(x1,--y1);
								else tmpBlue = new Point(x1,--y1);
								break;
							}
							else if(map[y1][x1] == 2) { 
								if(B) { 
									flag = 1;
									break;
								}
								else {
									flag = 2;
									break;
								}
							}
							else y1 ++;
						}
						if(flag == 0) map[y1][x1] = 1;
						int x2 = tmp2.x;
						int y2 = tmp2.y;
						while(true) {
							if(map[y2][x2] == 1) {
								if(B) tmpBlue = new Point(x2,--y2);
								else tmpRed = new Point(x2,--y2);
								break;
							}
							else if(map[y2][x2] == 2) { 
								if(B) { 
									flag = 2;
									break;  
								}
								else {
									if(flag == 2) break;
									else return L;
								}
							}
							else y2 ++;
						}
						if(map[y1][x1] == 1) map[y1][x1] = 0;
						if(flag == 1) return L;
						if(tmpRed != null && tmpBlue != null) {
							if(tmpRed.x != tmp.red.x || tmpRed.y != tmp.red.y || tmpBlue.x != tmp.blue.x || tmpBlue.y != tmp.blue.y) { 
								if(!visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x]) {
									visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x] = true;
									Q.offer(new Ball(tmpRed, tmpBlue));
								}
							}
						}
						break;
					}
					case 3: { // LEFT
						Point tmp1 = null, tmp2 = null, tmpRed = null, tmpBlue = null;
						if(tmp.red.x < tmp.blue.x) {
							B = true;
							tmp1 = tmp.red;
							tmp2 = tmp.blue;
						}
						else {
							B =false;
							tmp1 = tmp.blue;
							tmp2 = tmp.red;
						}
						int x1 = tmp1.x;
						int y1 = tmp1.y;
						while(true) {
							if(map[y1][x1] == 1) {
								if(B) tmpRed = new Point(++x1,y1);
								else tmpBlue = new Point(++x1,y1);
								break;
							}
							else if(map[y1][x1] == 2) { 
								if(B) { 
									flag = 1;
									break;
								}
								else {
									flag = 2;
									break;
								}
							}
							else x1 --;
						}
						if(flag == 0) map[y1][x1] = 1;
						int x2 = tmp2.x;
						int y2 = tmp2.y;
						while(true) {
							if(map[y2][x2] == 1) {
								if(B) tmpBlue = new Point(++x2,y2);
								else tmpRed = new Point(++x2,y2);
								break;
							}
							else if(map[y2][x2] == 2) { 
								if(B) {
									flag = 2;
									break;  
								}
								else {
									if(flag == 2) break;
									else return L;
								}
							}
							else x2 --;
						}
						if(map[y1][x1] == 1) map[y1][x1] = 0;
						if(flag == 1) return L;
						if(tmpRed != null && tmpBlue != null) {
							if(tmpRed.x != tmp.red.x || tmpRed.y != tmp.red.y || tmpBlue.x != tmp.blue.x || tmpBlue.y != tmp.blue.y) { 
								if(!visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x]) {
									visited[tmpRed.y][tmpRed.x][tmpBlue.y][tmpBlue.x] = true;
									Q.offer(new Ball(tmpRed, tmpBlue));
								}
							}
						}
						break;
					}
					}
				}
		}
			L++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][N][M]; // Red Blue
		Point red = null, blue = null;
		for(int i = 0 ; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				if(str.charAt(k) == '#') map[i][k] = 1;
				else if(str.charAt(k) == 'B') blue = new Point(k,i);
				else if(str.charAt(k) == 'R') red = new Point(k,i);
				else if(str.charAt(k) == 'O') map[i][k] = 2; // O == 2
			}
		}
		System.out.println(T.solution(red, blue));
	}
}
