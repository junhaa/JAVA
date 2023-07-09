import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #19237 어른 상어
/*
 * 모든 상어의 번호는 다르다.
 * 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있다.
 * 
 * 1. 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다. * 
 * 2. 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌린다. * 
 * 3. 냄새는 상어가 k번 이동하고 나면 사라진다. --> k초 이후에 모두 이동을 마친 후 사라진다.
 * 
 * 
 * 
 * 상어의 이동방향
 * 1. 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
 * 2. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다.
 * --> 자신의 냄새가 있는 칸이 여러 개인 경우 특정 우선순위에 따라 이동한다. (우선순위는 상어마다 다를 수 있다.)
 * 		현재 상어가 바라보고 있는 방향에 따라 또 다를 수 있다.
 * 3. 상어의 이동방향은 방금 이동한 방향이 보고 있는 방향이 된다.
 * 4. 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.
 * --> 한 칸에 여러마리의 상어가 있을 수 있음. 상어는 최대 400마리
 * 
 * 1번 상어만 격자에 남게 되기까지 걸리는 시간을 출력, 1000초가 넘어도 다른 상어가 격자에 남아 있으면 -1 출력
 * 
 * 
 * # 이동과 냄새 지속시간 순서 (이동 후 time ++)
 * # 상어 오름차순으로 이동
 * # 이동했는데 번호가 낮은 상어가 있으면 쫓겨남 (없을 경우에만 냄새 뿌리기)  
 * # 
 * 
 * 냄새 뿌리기 함수
 * 상어 이동 함수
 * 
 * 
 */
class Scent{
	int shark_num, time;
	public Scent(int shark_num, int time) {
		this.shark_num = shark_num;
		this.time = time;
	}
}

class Shark{
	int y, x, dir, num;
	int[][] priority; // 상어의 이동 우선순위 ( 상 하 좌 우 )
	public Shark(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
	}
}

public class Main {
	static int[][] map;
	static Scent[][] Smap;
	static int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
	static int N, M, K, time = 0, cnt; // 격자 크기, 상어의 수, 냄새 지속시간, 시간, 남은 상어수
	static Shark[] sharks; // 0-based
	
	
	static void spread_scent() {
		for(int i = 0 ; i < M ; i ++) {
			if(sharks[i] == null) continue;
			Shark tmp = sharks[i];
			Smap[tmp.y][tmp.x] = new Scent(i, time + K);
		}
	}
	
	static void move_sharks() {
		for(int i = 0 ; i < M ; i ++) {
			if(sharks[i] == null) continue;
			Shark tmp = sharks[i];
			boolean canMove = false;
			for(int j = 0 ; j < 4 ; j ++) { // 빈 칸이 있는 지
				int nextDir = tmp.priority[tmp.dir][j];
				int ny = tmp.y + dy[nextDir];
				int nx = tmp.x + dx[nextDir];
				if(check_boundary(ny, nx) && (Smap[ny][nx] == null || Smap[ny][nx].time <= time)) {
					canMove = true;
					if(map[tmp.y][tmp.x] == i) map[tmp.y][tmp.x] = -1;
					if(map[ny][nx] != -1 && map[ny][nx] < tmp.num) {
						remove_shark(i);
						break;
					}
					tmp.y = ny;
					tmp.x = nx;
					tmp.dir = nextDir;
					map[ny][nx] = tmp.num;
					break;
				}
			}
			if(canMove) continue;
			for(int j = 0 ; j < 4 ; j ++) { // 자신이 뿌린 냄새로 이동
				int nextDir = tmp.priority[tmp.dir][j];
				int ny = tmp.y + dy[nextDir];
				int nx = tmp.x + dx[nextDir];
				if(check_boundary(ny, nx) && Smap[ny][nx].shark_num == i) {
					if(map[tmp.y][tmp.x] == i) map[tmp.y][tmp.x] = -1;
					tmp.y = ny;
					tmp.x = nx;
					tmp.dir = nextDir;
					map[ny][nx] = tmp.num;
					break;
				}
			}
		}
	}
	
	static void remove_shark(int shark_num) {
		cnt --;
		sharks[shark_num] = null;
	}
	
	static boolean check_boundary(int y, int x) {
		if(x >= 0 && x < N && y >= 0 && y < N) return true;
		else return false;
	}
	
	static void solution() {
		spread_scent();
		while(time <= 1000) {
			if(cnt == 1) {
				System.out.println(time);
				return;
			}
			move_sharks();
			time ++;
			spread_scent();
		}
		System.out.println(-1);
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		Smap = new Scent[N][N];
		sharks = new Shark[M];
		cnt = M;
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(map[i], -1);
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp != 0) {
					Shark stmp = new Shark(i, j, tmp - 1); 
					map[i][j] = tmp - 1;
					sharks[tmp - 1]  = stmp;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
		}
		for(int i = 0 ; i < M ; i ++) {
			int[][] dirArr = new int[4][4];
			for(int j = 0 ; j < 4 ; j ++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < 4 ; k ++) {
					dirArr[j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
			sharks[i].priority = dirArr;
		}
		
		T.solution();
	}
}
