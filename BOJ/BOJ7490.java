import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #7490 0 만들기
public class Main {

	static int[] arr;
	static int cur;
	static StringBuilder sb = new StringBuilder();
	static void recursive(int idx) { // 0 + 1 - 2 " "
		if(idx == cur - 1) {
			int tmp = 1;
			int sum = 0;
			int u = 0;
			for(int i = 1 ; i < cur ; i ++) {
				if(arr[i - 1] == 2) {
					tmp *= 10;
					tmp += i + 1;
				}
				else if(arr[i - 1] == 1) {
					if(u == 0) sum += tmp;
					else sum -= tmp;
					u = 1;
					tmp = i + 1;
				}
				else {
					if(u == 0) sum += tmp;
					else sum -= tmp;
					u = 0;
					tmp = i + 1;
				}
			}
			if(u == 0) sum += tmp;
			else sum -= tmp;
			if(sum == 0) {
				sb.append(1);
				for(int i = 0 ; i < cur - 1 ; i ++) {
					if(arr[i] == 0) sb.append("+").append(i + 2);
					else if(arr[i] == 1) sb.append("-").append(i + 2);
					else sb.append(" ").append(i + 2);
				}
				sb.append("\n");
			}
			return;
		}
		arr[idx] = 2;
		recursive(idx + 1);
		arr[idx] = 0;
		recursive(idx + 1);
		arr[idx] = 1;
		recursive(idx + 1);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			cur = Integer.parseInt(br.readLine());
			arr = new int[cur - 1];
			T.recursive(0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
