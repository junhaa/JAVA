import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #17103 골드바흐 파티션
public class Main {

	static boolean[] ch = new boolean[1000001];
	
	
	public static void main(String[] args) throws IOException{
		Main T  = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		ch[0] = true;
		ch[1] = true;
		for(int i = 2 ; i <= Math.sqrt(1000000) ; i ++) {
			for(int k = i * i ; k <= 1000000 ; k += i) {
				ch[k] = true;
			}
		}
		while(test -- > 0) {
			int answer = 0;
			int tmp = Integer.parseInt(br.readLine());
			for(int i = 0 ; i <= tmp / 2 ; i ++) {
				if(!ch[i] && !ch[tmp - i]) answer ++;
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
