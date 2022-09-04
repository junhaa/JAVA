import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2458 Å° ¼ø¼­
public class Main {

	static int[][] d;
	
	static int floyeWarshall(int N) {
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				for(int j = 0 ; j < N ; j ++) {
					if(d[k][j] != 10000000) continue;
					else {
						if(d[k][i] == 1 && d[i][j] == 1) { 
							d[k][j] = 1;
							d[j][k] = -1;
						}
					}
				}
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			boolean flag = false;
			for(int k = 0 ; k < N ; k ++) {
				if(d[i][k] == 10000000) {
					flag = true;
					break;
				}
			}
			if(!flag) answer ++;
		}
		return answer;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		d = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(i == k) d[i][k] = 0;
				else d[i][k] = 10000000;
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			d[start][end] = 1;
			d[end][start] = -1;
		}
		System.out.println(T.floyeWarshall(N));
	}
}
