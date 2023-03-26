import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11945 뜨거운 붕어빵
public class Main {

	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		String input;
		for(int i = 0 ; i < N ; i ++) {
			input = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			for(int j = M - 1 ; j >= 0 ; j --) {
				sb.append(map[i][j]); 
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
