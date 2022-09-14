import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14890 경사로 
public class Main {
	static byte[][] map;
	
	static int solution(int N, int L) {
		int answer = 0;
		for(int i = 0; i < N ; i ++) {
			int height = -1;
			int cnt = 0;
			boolean down = false;
			boolean canAnswer = true;
			for(int k = 0 ; k < N ; k ++) {
				if(height == -1) {
					cnt ++;
					height = map[i][k];
				}
				else if (height - 1 == map[i][k]) {
					if(N - k < L || down) { 
						canAnswer = false; 
						break;
					}
					down = true;
					cnt = 1;
					if(cnt == L) {
						cnt = 0;
						down = false;
					}
					height = map[i][k];
				}
				else if (height + 1 == map[i][k]) {
					if(cnt < L || down) {
						canAnswer = false;
						break;
					}
					cnt = 1;
					height = map[i][k];
				}
				else if(height == map[i][k]) {
					cnt ++;
					if(down && cnt == L) {
						cnt = 0;
						down = false;
					}
				}
				else {
					canAnswer = false;
					break;
				}
			}
			if(canAnswer && !down) answer ++;
		}
		for(int i = 0; i < N ; i ++) {
			int height = -1;
			int cnt = 0;
			boolean down = false;
			boolean canAnswer = true;
			for(int k = 0 ; k < N ; k ++) {
				if(height == -1) {
					cnt ++;
					height = map[k][i];
				}
				else if (height - 1 == map[k][i]) {
					if(N - k < L || down) { 
						canAnswer = false; 
						break;
					}
					down = true;
					cnt = 1;
					if(cnt == L) {
						cnt = 0;
						down =false;
					}
					height = map[k][i];
				}
				else if (height + 1 == map[k][i]) {
					if(cnt < L || down) {
						canAnswer = false;
						break;
					}
					cnt = 1;
					height = map[k][i];
				}
				else if(height == map[k][i]) {
					cnt ++;
					if(down && cnt == L) {
						cnt = 0;
						down = false;
					}
				}
				else {
					canAnswer = false;
					break;
				}
			}
			if(canAnswer && !down) answer ++;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		map = new byte[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Byte.parseByte(st.nextToken());
			}
		}
		System.out.println(T.solution(N, L));
	}
}
