import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16139 인간-컴퓨터 상호작용 
public class Main {

	static int[][] cnt = new int[26][200001];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		for(int i = 0 ; i < S.length() ; i ++) {
			int cur = S.charAt(i) - 'a';
			for(int j = 0 ; j < 26 ; j ++) {
				cnt[j][i + 1] = cnt[j][i];
				if(cur == j) {
					cnt[j][i + 1] ++;
				}
			}
		}
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0) - 'a';
			int lt = Integer.parseInt(st.nextToken()) + 1;
			int rt = Integer.parseInt(st.nextToken()) + 1;
			sb.append(cnt[a][rt] - cnt[a][lt - 1]).append('\n');
		}
		System.out.println(sb);
	}
}
