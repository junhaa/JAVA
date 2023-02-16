import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11509 풍선 맞추기
public class Main {

	static int[] arrow = new int[1000001];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(arrow[tmp] == 0) {
				answer ++;
				if(tmp > 0) arrow[--tmp] ++;
			}
			else {
				arrow[tmp] --;
				if(tmp > 0) arrow[--tmp] ++;
			}
		}
		System.out.println(answer);
	}
}
