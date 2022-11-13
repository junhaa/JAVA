import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #2465 줄 세우기
public class Main {

	static int[] arr, tree;
	static int size = 2;
	static HashMap<Integer, Integer> map1 = new HashMap<>();
	static HashMap<Integer, Integer> map2 = new HashMap<>();// 좌표 압축
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum >= size / 2) {
			return nodeNum - size / 2;
		}
		if(num > tree[nodeNum * 2]) {
			return findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
		else return findNum(num, nodeNum * 2);
	}
	
	static void update(int i) {
		i += size / 2;
		tree[i] --;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int[] answer = new int[N];
		int[] length = new int[N];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(size >= 100001) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < N ; i ++) {
			length[i] = Integer.parseInt(br.readLine());
			//tree[Integer.parseInt(br.readLine()) + size / 2] ++;
		}
		Arrays.sort(length);
		int k = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(!map1.containsKey(length[i])) {
				map1.put(length[i], k++);
				map2.put(k - 1, length[i]);
			}
			tree[map1.get(length[i]) + size / 2] ++;
		}
		T.construct();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = N - 1 ; i >= 0 ; i --) {
			int idx = findNum(arr[i] + 1, 1);
			answer[i] = map2.get(idx);
			T.update(idx);
		}
		for(int i = 0 ; i < N ; i ++) {
			sb.append(answer[i] + "\n");
		}
		System.out.println(sb);
	}
}
