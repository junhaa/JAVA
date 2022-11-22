import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #9426 중앙값 측정
public class Main {

	static int[] arr;
	static int[] tree;
	static int size = 2;
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum >= size / 2) {
			return nodeNum - size / 2;
		}
		if(tree[nodeNum * 2] < num) {
			return findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
		else {
			return findNum(num, nodeNum * 2);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		while(true) {
			if(size >= 65535) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(tree[1] != K) {
				T.update(arr[i], 1);
				if(tree[1] == K) {
					answer += findNum((K + 1) / 2, 1);
				}
			}
			else {
				T.update(arr[i], 1);
				T.update(arr[i - K], -1);
				answer += findNum((K + 1) / 2, 1);
			}
		}
		System.out.println(answer);
	}
}
