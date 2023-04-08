import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList; // ArrayList 사용시 2% 틀렸습니다(tc)?
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #17822 원판 돌리기
public class Main {

	static int N, M;
	static int[][] cir;
	//static ArrayList<Integer>[] circle;
	static HashSet<Integer>[] delete;
	static boolean turn(int x, int d, int k) {
		for(int i = x - 1 ; i < N ; i += x) {
			int[] tmp = new int[M];
			if(d == 0) { // 시계 방향
				for(int j = 0 ; j < M ; j ++) {
					//circle[i].add(0, circle[i].get(M - 1));
					//circle[i].remove(M);
					tmp[(j + k) % M] = cir[i][j];
				}
			}
			else { // 반시계 방향
				for(int j = 0 ; j < M ; j ++) {
//					circle[i].add(circle[i].get(0));
//					circle[i].remove(0);
					tmp[(M - k + j) % M] = cir[i][j];
				}
			}
			cir[i] = tmp;
		}
		boolean flag = false; // 인접한 수가 있는지
		int sum = 0;
		int cnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(cir[i][j] != 0) {
					cnt ++;
					sum += cir[i][j];
					int rt = j + 1;
					if(j == M - 1) rt = 0;
					if(cir[i][j] == cir[i][rt]) {
						flag = true;
						delete[i].add(rt);
						delete[i].add(j);
					}
					if(i < N - 1 && cir[i][j] == cir[i + 1][j]) {
						flag = true;
						delete[i].add(j);
						delete[i + 1].add(j);
					}
				}
			}
		}
		if(!flag) {
			if(cnt == 0) return false;
			double avg = (double)sum / (double)cnt;
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < M ; j ++) {
					if(cir[i][j] != 0) {
						if(cir[i][j] > avg) {
							cir[i][j] --;
						}
						else if(cir[i][j] < avg) {
							cir[i][j] ++;
						}
					}
				}
			}
		}
		else {
			for(int i = 0 ; i < N ; i ++) {
				for(int cur : delete[i]) {
					cir[i][cur] = 0;
				}
				delete[i] = new HashSet<>();
			}
		}
		return true;
	}
	
	static int getSum() {
		int sum = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				sum += cir[i][j];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int tc = Integer.parseInt(st.nextToken());
		//circle = new ArrayList[N];
		delete = new HashSet[N];
		cir = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			//circle[i] = new ArrayList<>();
			delete[i] = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				//circle[i].add(Integer.parseInt(st.nextToken()));
				cir[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < tc ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(!T.turn(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(T.getSum());
	}
}
