import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ #15650 N°ú M (2)
public class Main {
	static boolean[] ch ;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void DFS(int num, int L, String curStr) {
		String cur = curStr;
		if(L == M) sb.append(cur).append('\n');
		else {
			ch[num] = true;
			for(int i = num ; i <= N ; i ++) {
				if(ch[i] == false) {
					DFS(i, L + 1, cur + i + " ");
				}
			}
			ch[num] = false;
		}
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ch = new boolean[N + 1];
		T.DFS(0, 0, "");
		bw.write(sb.toString());
		bw.flush();
	}
}
