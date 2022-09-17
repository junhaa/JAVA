import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2290 LCD test
public class Main {

	static int[][] number = {{}, { 1, 0, 1, 1, 0, 1, 1, 1, 1, 1 } , { 3, 2, 2, 2, 3, 1, 1, 2, 3, 3 } , { 0, 0, 1, 1, 1, 1, 1, 0, 1, 1 },
			{ 3, 2, 1, 2, 2, 2, 3, 2, 3, 2} , { 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}
	};
	
	static void solution(int s, String n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= 5 ; i ++) {
			if(i % 2 == 1) {
				for(int k = 0 ; k < n.length() ; k ++) {
					switch (number[i][n.charAt(k) -'0']) {
					case 0: {
						sb.append(" ");
						for(int j = 0 ; j < s ; j ++) {
							sb.append(" ");
						}
						sb.append("  ");
						break;
					}
					case 1: {
						sb.append(" ");
						for(int j = 0 ; j < s ; j ++) {
							sb.append("-");
						}
						sb.append("  ");
						break;
					}
					}
				}
				sb.append("\n");
			}
			else {
				for(int l = 0; l < s ; l ++) {
				for(int k = 0 ; k < n.length() ; k ++) {
						switch (number[i][n.charAt(k) -'0']) {
						case 1: {
							sb.append("|");
							for(int j = 0 ; j < s ; j ++) {
								sb.append(" ");
							}
							sb.append("  ");
							break;
						}
						case 2: {
							sb.append(" ");
							for(int j = 0 ; j < s ; j ++) {
								sb.append(" ");
							}
							sb.append("| ");
							break;
						}
						case 3: {
							sb.append("|");
							for(int j = 0 ; j < s ; j ++) {
								sb.append(" ");
							}
							sb.append("| ");
							break;
						}
						}
					}
					sb.append("\n");
				}
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		String n = st.nextToken();
		T.solution(s, n);
	}
}
