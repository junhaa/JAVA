import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #5676 음주 코딩
public class Main {
	static int[] arr;
	static int size;

	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = arr[i * 2] * arr[i * 2 + 1];
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i] = val;
		while(i > 1) {
			i /= 2;
			arr[i] = arr[i * 2] * arr[i * 2 + 1];
		}
	}
	
	static int findMul(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 1;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findMul(L, R, nodeNum * 2, nodeL, mid) * findMul(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		String a = "";
		int b ,c;
		while(true) {
			String input = br.readLine();
			if(input == null) break;
			st = new StringTokenizer(input);
			int N = Integer.parseInt(st.nextToken()); // 수열의 크기
			int K = Integer.parseInt(st.nextToken()); // 게임의 라운드 수 
			size = 2;
			while(true) {
				if(size >= N) {
					size *= 2;
					break;
				}
				size *= 2;
			}
			arr = new int[size];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp > 0) arr[size / 2 + i] = 1;
				else if(tmp < 0) arr[size / 2 + i] = -1;
				else arr[size / 2 + i] = 0;
			}
			T.construct();
			for(int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				a = st.nextToken();
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				if(a.equals("P")) { // 곱셈 명령
					int num = findMul(b - 1, c - 1, 1, 0, size / 2 - 1);
					if(num == 1) {
						sb.append("+");
					}
					else if(num == -1) {
						sb.append("-");
					}
					else sb.append("0");
				}
				else {
					if(c > 0) {
						T.update(b - 1, 1);
					}
					else if(c < 0) {
						T.update(b - 1, -1);
					}
					else {
						T.update(b - 1, 0);
					}
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
