import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #19236 청소년 상어
class Fish implements Comparable<Fish>{
	int size;
	int x, y;
	int dir;
	
	public Fish(int x, int y, int size, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.size = size;
	}
	@Override
	public int compareTo(Fish o) {
		return this.size - o.size;
	}
}
public class Main {

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int answer = Integer.MIN_VALUE;
	
	
	static void moveFish(Fish[][] map) {
		PriorityQueue<Fish> pQ = new PriorityQueue<>();
		for(int i = 0 ; i < 4 ; i ++) {
			for(int k = 0 ; k < 4 ; k ++) {
				if(map[i][k] != null && map[i][k].size < 1000) {
					pQ.offer(map[i][k]);
				}
			}
		}
		int length = pQ.size();
		for(int i = 0 ; i < length ; i ++) {
			Fish cur = pQ.poll();
			for(int k = 0 ; k < 8 ; k ++) {
				int xx = cur.x + dx[(cur.dir + k) % 8];
				int yy = cur.y + dy[(cur.dir + k) % 8];
				if(yy < 4 && xx < 4 && yy >= 0 && xx >= 0) { 
					if(map[yy][xx] == null) { // 비어있으면 물고기 위치 변경
						map[cur.y][cur.x] = null;
						cur.x = xx;
						cur.y = yy;
						map[yy][xx] = cur;
						cur.dir = (cur.dir + k) % 8;
						break;
					}
					else if(map[yy][xx].size < 1000) { // 물고기가 있으면 둘의 위치 변경
					Fish tmp = map[yy][xx];
					map[cur.y][cur.x] = tmp;
					map[yy][xx] = cur;
					tmp.y = cur.y;
					tmp.x = cur.x;
					cur.x = xx;
					cur.y = yy;
					cur.dir = (cur.dir + k) % 8;
					break;
					}
				}
			}
		}
	}
	
	static void DFS(Fish[][] map) {
		moveFish(map);
		int[][] tmp2 = new int[4][4];
		for(int i = 0 ; i < 4 ; i ++) {
			for(int k = 0 ; k < 4 ; k ++) {
				if(map[i][k] == null) continue;
				tmp2[i][k] = map[i][k].size;
			}
		}
		Fish shark = null; 
		// 상어의 위치 탐색
		for(int i = 0 ; i < 4 ; i ++) {
			boolean flag = false;
			for(int k = 0 ; k < 4 ; k ++) {
				if(map[i][k] != null && map[i][k].size > 1000) {
					shark = map[i][k];
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		boolean canEat = false;
		for(int i = 1 ; i <= 3 ; i ++) {
			int xx = shark.x + dx[shark.dir] * i;
			int yy = shark.y + dy[shark.dir] * i;
			if(xx < 4 && xx >= 0 && yy < 4 && yy >= 0) {
				/*
				if(map[yy][xx] == null) {
					Fish[][] nextMap = new Fish[4][4];
					for(int k = 0 ; k < 4 ; k ++) {
						for(int j = 0 ; j < 4 ; j ++) {
							Fish tmp = map[k][j];
							if(tmp == null) continue;
							nextMap[k][j] = new Fish(tmp.x, tmp.y, tmp.size, tmp.dir);
						}
					}
					Fish nextShark = nextMap[shark.y][shark.x];
					nextShark.x = xx;
					nextShark.y = yy;
					nextMap[shark.y][shark.x] = null;
					nextMap[yy][xx] = nextShark;
					DFS(nextMap);
				}
				*/
				if(map[yy][xx] != null && map[yy][xx].size < 1000) {
					canEat = true;
					Fish[][] nextMap = new Fish[4][4];
					for(int k = 0 ; k < 4 ; k ++) {
						for(int j = 0 ; j < 4 ; j ++) {
							Fish tmp = map[k][j];
							if(tmp == null) continue;
							nextMap[k][j] = new Fish(tmp.x, tmp.y, tmp.size, tmp.dir);
						}
					}
					Fish nextShark = nextMap[shark.y][shark.x];
					Fish eat = nextMap[yy][xx];
					nextShark.size += eat.size;
					nextShark.x = eat.x;
					nextShark.y = eat.y;
					nextShark.dir = eat.dir;
					nextMap[yy][xx] = nextShark;
					nextMap[shark.y][shark.x] = null;
					DFS(nextMap);
				}
			}
		}
		if(!canEat) {
			answer = Math.max(shark.size - 1000, answer);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] map = new Fish[4][4];
		StringTokenizer st;
		for(int i = 0 ; i < 4 ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < 4 ; k ++) {
				map[i][k] = new Fish(k, i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
			}
		}
		map[0][0].size += 1000; // 0,0 을 상어로 변경
		T.DFS(map);
		System.out.println(answer);
	}
}
