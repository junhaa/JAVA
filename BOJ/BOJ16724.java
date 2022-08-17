import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16724 피리 부는 사나이
public class Main {
	static char map[][];
	static int ch[][];
	static boolean visited[][];
	static int num = 0;
	
	static int DFS(int x, int y) {
		visited[y][x] = true;
		int xx = 0;
		int yy = 0;
		switch (map[y][x]) {
		case 'U': {
			yy = y - 1;
			xx = x;
			break;
		}
		case 'R' :{
			yy = y;
			xx = x + 1;
			break;
		}
		case 'D' :{
			yy = y + 1;
			xx = x;
			break;
		}
		case 'L' :{
			yy = y;
			xx = x - 1;
			break;
		}
		}
		if(visited[yy][xx]) { 
			if(ch[yy][xx] == 0) { 
				num ++;
				ch[y][x] = num;
				return num;
			}
			else {
			ch[y][x] = ch[yy][xx];
			return ch[yy][xx];
			}
		}
		else return ch[y][x] = DFS(xx, yy);
	}

	
	static int solution(int N, int M) {
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < M ; k ++) {
				if(ch[i][k] == 0) DFS(k, i);
			}
		}
		return num;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		ch = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = str.charAt(k);
			}
		}
		System.out.println(T.solution(N, M));
	}
}
