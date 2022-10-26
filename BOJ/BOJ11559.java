import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #11559 Puyo Puyo
class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static char[][] map = new char[12][6];
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int num = 0;
	static Queue<Point> poQ = new LinkedList<>();
	
	
	static boolean boom; // 폭발이 발생했는지
	static boolean flag; // 폭발한 뿌요가 있는지
	static void drop() { // 빈칸 채우기
		for(int i = 0 ; i < 6 ; i ++) {
			Queue<Character> Q = new LinkedList<>();
			for(int k = 11 ; k >= 0 ; k --) {
				Q.add(map[k][i]);
			}
			for(int k = 0 ; k < 12 ; k ++) {
				char tmp = Q.poll();
				if(tmp == '.') continue;
				Q.offer(tmp);
			}
			int length = Q.size();
			for(int k = 11 ; k >= 0 ; k --) {
				if(k > 11 - length) map[k][i] = Q.poll();
				else map[k][i] = '.';
			}
		}
	}
	
	static void changeDFS(int y, int x, char color) {
		map[y][x] = '.';
		for(int i = 0 ; i < 4 ; i ++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			if(yy >= 0 && xx >= 0 && yy < 12 && xx < 6) {
				if(map[yy][xx] == color) {
					changeDFS(yy, xx, color);
				}
			}
		}
	}
	
	static void DFS(int y, int x, char color) { // DFS로 연결된 뿌요 확인
		visited[y][x] = true;
		if(++num == 4) {
			boom = true;
		}
		for(int i = 0 ; i < 4 ; i ++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			if(yy >= 0 && xx >= 0 && yy < 12 && xx < 6) {
				if(map[yy][xx] == color && !visited[yy][xx]) {
					DFS(yy, xx, color);
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0 ; i < 12 ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < 6 ; j ++) {
				map[i][j] = input.charAt(j);
			}
		}
		int answer = 0;
		while(true) {
			flag = false;
			visited = new boolean[12][6];
			for(int i = 11 ; i >= 0 ; i --) {
				for(int k = 0 ; k < 6 ; k ++) {
					if(map[i][k] != '.' && !visited[i][k]) {
						boom = false;
						num = 0;
						T.DFS(i, k, map[i][k]);
						if(boom) {
							T.changeDFS(i, k, map[i][k]);
							flag = true;
						}
					}
				}
			}
			if(!flag) break;
			answer ++;
			T.drop();
		}
		System.out.println(answer);
	}
}
