import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOj 4153 직각삼각형
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			int[] len = new int[3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 3 ; i ++) {
				len[i] = Integer.parseInt(st.nextToken());
			}
			if(len[0] == 0) break;
			Arrays.sort(len);
			if(len[0] * len[0] + len[1] * len[1] == len[2] * len[2]) {
				sb.append("right" + "\n");
			}
			else sb.append("wrong" + "\n");
		}
		System.out.println(sb);
	}
}
