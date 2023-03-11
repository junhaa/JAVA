import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2224 명제 증명
public class Main {

	static boolean[][] ch = new boolean[52][52]; // 대문자 이후 소문자
	
	static int getIdx(char x) {
		if(x - 'a' >= 0) {
			return x - 'a' + 26;
		}
		else {
			return x - 'A';
		}
	}
	
	static char getCh(int idx) {
		if(idx >= 26) {
			return (char) (idx - 26 + 'a');
		}
		else {
			return (char) (idx + 'A');
		}
	}
	
	static void floydWarshall(){ 
		for(int i = 0 ; i < 52 ; i ++) {
			for(int j = 0 ; j < 52 ; j ++) {
				for(int k = 0 ; k < 52 ; k ++) {
					if(ch[j][i] && ch[i][k]) ch[j][k] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = T.getIdx(st.nextToken().charAt(0));
			st.nextToken();
			int end = T.getIdx(st.nextToken().charAt(0));
			ch[start][end] = true;
		}
		T.floydWarshall();
		int answer = 0;
		for(int i = 0 ; i < 52 ; i ++) {
			for(int j = 0 ; j < 52 ; j ++) {
				if(i != j && ch[i][j]) {
					answer ++;
					sb.append(T.getCh(i) + " => " + T.getCh(j) + "\n");
				}
			}
		}
		System.out.println(answer);
		System.out.println(sb);
	}
}	
