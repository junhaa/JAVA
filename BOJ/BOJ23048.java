import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #23048 자연수 색칠하기
public class Main {

	static int[] ch;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(1 + "\n" + 1);
			return;
		}
		ch = new int[N + 1];
		int c = 0;
		ch[1] = ++c;
		int num = 2;		
		for(int i = 2 ; i <= N ; i ++) {
			if(ch[i] == 0) {
				ch[i] = ++c;
				for(int k = i ; k <= N ; k += i) {
					if(ch[k] == 0) {
						ch[k] = c;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(c + "\n");
		for(int i = 1 ; i <= N ; i ++) {
			sb.append(ch[i] + " ");
		}
		System.out.println(sb);
	}
}
