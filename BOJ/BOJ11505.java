import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11505 구간 곱 구하기
public class Main {
	static int[] arr;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = (int)((long)arr[i * 2] * arr[i * 2 + 1] % 1000000007) ; 
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i] = val;
		while(i > 1) {
			i /= 2;
			arr[i] = (int)((long)arr[i * 2] * arr[i * 2 + 1] % 1000000007) ;
		}
	}
	
	static int findMul(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 1;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return (int)((long)findMul(L, R, nodeNum * 2, nodeL, mid) * findMul(L, R, nodeNum * 2 + 1, mid + 1, nodeR) % 1000000007);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수
		int a,b,c;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new int[size];
		for(int i = 0 ; i < N ; i ++) {
			arr[size / 2 + i] = Integer.parseInt(br.readLine());
		}
		T.construct();
		for(int i = 0 ; i < M + K ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				T.update(b - 1, c);
			}
			else {
				sb.append(T.findMul(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
