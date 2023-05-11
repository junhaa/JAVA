import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11637 인기 투표
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		int sum = 0, max = Integer.MIN_VALUE, tmp, idx = -1, cnt;
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			int len = Integer.parseInt(br.readLine());
			sum = 0;
			max = Integer.MIN_VALUE;
			cnt = 1;
			for(int i = 0 ; i < len ; i ++) {
				tmp = Integer.parseInt(br.readLine());
				sum += tmp;
				if(tmp > max) {
					max = tmp;
					idx = i + 1;
					cnt = 1;
				}
				else if(tmp == max) {
					cnt ++;
				}
			}
			if((double)max > (double)sum / 2 && cnt == 1) sb.append("majority winner " + idx + "\n");
			else if((double)max <= (double)sum / 2 && cnt == 1) sb.append("minority winner " + idx + "\n");
			else sb.append("no winner\n");
		}
		System.out.println(sb);
	}
}
