import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1244 스위치 켜고 끄기
public class Main {

	static int[] sw;
	
	static void change(int a, int b, int N) {
		if(a == 1) {
			for(int i = b ; i <= N ; i += b) sw[i]^= 1;
		}
		else{
			int i = 0;
			while(b - i > 0 && b + i <= N) {
				if(sw[b - i] != sw[b + i]) {
					break;
				}
				i ++;
			}
			i --;
			for(int j = b - i ; j <= b + i ; j ++) {
				sw[j] ^= 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sw = new int[N + 1]; // 1 - based
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			sw[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			T.change(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), N);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			if(i % 20 == 0) sb.append("\n");
			sb.append(sw[i + 1] + " ");
		}
		System.out.println(sb);
	}
}
