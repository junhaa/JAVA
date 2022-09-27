import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17408 수열과 쿼리 24
class Node{
	int max1;
	int max2;
	public Node(int num1, int num2) {
		if(num1 > num2) {
			max1 = num1;
			max2 = num2;
		}
		else {
			max1 = num2;
			max2 = num1;
		}
	}
}
public class Main {
	static Node[] maxArr;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			if(maxArr[i * 2].max1 > maxArr[i * 2 + 1].max1) {
				if(maxArr[i * 2].max2 > maxArr[i * 2 + 1].max1) {
					maxArr[i] = new Node(maxArr[i * 2].max1, maxArr[i * 2].max2);
				}
				else {
					maxArr[i] = new Node(maxArr[i * 2].max1, maxArr[i * 2 + 1].max1);
				}
			}
			else if(maxArr[i * 2].max1 == maxArr[i * 2 + 1].max1) maxArr[i] = new Node(maxArr[i * 2].max1, maxArr[i * 2 + 1].max1);
			else {
				if(maxArr[i * 2 + 1].max2 > maxArr[i * 2].max1) {
					maxArr[i] = new Node(maxArr[i * 2 + 1].max1, maxArr[i * 2 + 1].max2);
				}
				else{
					maxArr[i] = new Node(maxArr[i * 2 + 1].max1, maxArr[i * 2].max1);
				}
			}
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		maxArr[i] = new Node(val, 0);
		while(i > 1) {
			i /= 2;
			if(maxArr[i * 2].max1 > maxArr[i * 2 + 1].max1) {
				if(maxArr[i * 2].max2 > maxArr[i * 2 + 1].max1) {
					maxArr[i] = new Node(maxArr[i * 2].max1, maxArr[i * 2].max2);
				}
				else {
					maxArr[i] = new Node(maxArr[i * 2].max1, maxArr[i * 2 + 1].max1);
				}
			}
			else if(maxArr[i * 2].max1 == maxArr[i * 2 + 1].max1) maxArr[i] = new Node(maxArr[i * 2].max1, maxArr[i * 2 + 1].max1);
			else {
				if(maxArr[i * 2 + 1].max2 > maxArr[i * 2].max1) {
					maxArr[i] = new Node(maxArr[i * 2 + 1].max1, maxArr[i * 2 + 1].max2);
				}
				else{
					maxArr[i] = new Node(maxArr[i * 2 + 1].max1, maxArr[i * 2].max1);
				}
			}
		}
	}
	
	static Node findMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return null;
		if(L <= nodeL && nodeR <= R) return maxArr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		Node a = findMax(L, R, nodeNum * 2, nodeL, mid);
		Node b = findMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		if(a == null) {
			return b;
		}
		else if(b == null){
			return a;
		}
		else {
			if(a.max1 > b.max1) {
				if(a.max2 > b.max1) {
					return new Node(a.max1, a.max2);
				}
				else {
					return new Node(a.max1, b.max1);
				}
			}
			else if(a.max1 == b.max1) return new Node(a.max1, b.max1);
			else {
				if(b.max2 > a.max1) {
					return new Node(b.max1, b.max2);
				}
				else{
					return new Node(b.max1, a.max1);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int M, a = 0, b = 0, c = 0;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		maxArr = new Node[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
			int num = Integer.parseInt(st.nextToken());
			maxArr[size / 2 + i] = new Node(num, 0);
			}
			else {
				maxArr[size / 2 + i] = new Node(0, 0);
			}
		}
		T.construct();
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				T.update(b - 1, c);
			}
			else {
				Node tmp = T.findMax(b - 1, c - 1, 1, 0, size / 2 - 1);
				sb.append(tmp.max1 + tmp.max2 + "\n");
			}
		}
		System.out.println(sb);
	}
}
