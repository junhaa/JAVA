import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1103 °ÔÀÓ
public class Main {

	static byte board[][];
	static int count[][]; // È½¼ö dp
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] ch;
	static int startX, startY, N, M;
	
	static int DFS(int x, int y) {
		ch[y][x] = true;
		if(board[y][x] == 0) {  
			ch[y][x] = false;
			return count[y][x] = 0;
		}
		if(count[y][x] != 0) { 
			ch[y][x] = false;
			return count[y][x];
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < 4 ; i ++) {
			int num = board[y][x];
			int xx = x + dx[i] * num; 
			int yy = y + dy[i] * num;
			if(xx >= 0 && yy >= 0 && xx < M && yy < N) {
				if(ch[yy][xx]) { 
					ch[y][x] = false;
					return count[y][x] = 100000000;
				}
				int get = DFS(xx, yy) + 1;
				if(get >= 100000000) {
					ch[y][x] = false;
					return count[y][x] = 100000000;
				}
				max = Math.max(max, get);
			}
			else {
				max = Math.max(max, 1);
			}
		}
		ch[y][x] = false;
		return count[y][x] = max;
	}

	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new byte[N][M];
		count = new int[N][M];
		ch = new boolean[N][M];
		for(int i = 0; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				if(Character.isAlphabetic(str.charAt(k))) board[i][k] = 0;
				else {
					board[i][k] = Byte.parseByte(String.valueOf(str.charAt(k)));
				}
			}
		}
		int answer = T.DFS(0, 0);
		if(answer >= 100000000) System.out.println(-1);
		else System.out.println(answer);
	}
}
