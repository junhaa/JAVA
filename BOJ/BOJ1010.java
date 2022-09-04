import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ #1010 다리 놓기
public class Main {
	static int[][] combi = new int[31][31];
	
	static int recursive(int n, int r) {
		if(combi[n][r] != 0) return combi[n][r];
		if(r == 0 || n == r) return combi[n][r] = 1;
		else return combi[n][r] = recursive(n - 1, r) + recursive(n - 1, r - 1);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			bw.write(recursive(M, N) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
