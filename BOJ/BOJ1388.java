import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1388 바닥 장식
public class Main {
	static int[][] map ;
	static int N, M;
	public static int solution() {
		int[][] ch = new int[N][M];
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < M ; k ++) {
				if(ch[i][k] == 0) {
					answer++;
					if(map[i][k] == 1) {
						ch[i][k] = 1;
						for(int j = 1 ; j + k < M ; j ++) {
							if(map[i][j+k] == 1) ch[i][j+k] = 1;
							else break;
						}
					}
					else if (map[i][k] == 2) {
						ch[i][k] = 1;
						for(int j = 1 ; j + i < N ; j ++) {
							if(map[i +j][k] == 2) ch[i+j][k] = 1; 
							else break;
						}
					}
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				if(str.charAt(k) == '-') map[i][k] = 1;
				else map[i][k] = 2;
			}
		}
		System.out.println(T.solution());
	}
}
