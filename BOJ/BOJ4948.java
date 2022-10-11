import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #4948 베르트랑 공준
public class Main {

	static boolean[] ch = new boolean[250001];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ch[1] = true;
		StringBuilder sb = new StringBuilder();
		for(int i = 2 ; i <= Math.sqrt(250000) ; i ++) {
			for(int k = i * 2 ; k <= 250000 ; k += i) {
				ch[k] = true;
			}
		}
		while(true) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp == 0) break;
			int cnt = 0;
			for(int i = tmp + 1 ; i <= tmp * 2 ; i ++) {
				if(!ch[i]) cnt ++;
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
}
