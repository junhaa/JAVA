import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1969 DNA
public class Main {
	static int[][] cnt;
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int num = 0;
		cnt = new int[4][M]; // A = 0 C = 1 G = 2 T = 3
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				 switch (input.charAt(k)) {
				case 'A': {
					cnt[0][k] ++;
					break;
				}
				
				case 'C': {
					cnt[1][k] ++;
					break;
				}
				
				case 'G': {
					cnt[2][k] ++;
					break;
				}
				
				case 'T': {
					cnt[3][k] ++;
					break;
				}
			}
		}
		}
		for(int i = 0 ; i < M ; i ++) {
			int max = MAX;
			int maxChar = -1;
			for(int k = 0 ; k < 4 ; k ++) {
				if(cnt[k][i] > max) {
					max = cnt[k][i];
					maxChar = k;
				}
			}
			switch (maxChar) {
			case 0: {
				sb.append("A");
				break;
			}
			case 1: {
				sb.append("C");
				break;
			}
			case 2: {
				sb.append("G");
				break;
			}
			case 3: {
				sb.append("T");
				break;
			}
			}
			num += N - max;
		}
		sb.append("\n" + num);
		System.out.println(sb);
	}
		
}
