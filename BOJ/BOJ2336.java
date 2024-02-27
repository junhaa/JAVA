import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #2336 굉장한 학생
class Student implements Comparable<Student> {
	int r1, r2, r3;

	@Override
	public int compareTo(Student o) {
		return this.r1 - o.r1;
	}
}

public class Main {

	static int size = 2;
	static int[] tree;

	static void init() {
		for (int i = 1; i < size; i++) {
			tree[i] = Integer.MAX_VALUE;
		}
	}

	static void update(int i, int val) {
		i += (size >> 1);
		tree[i] = val;
		while (i > 1) {
			i >>= 1;
			tree[i] = Math.min(tree[i << 1], tree[(i << 1) + 1]);
		}
	}

	static int getMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if (R < nodeL || nodeR < L)
			return Integer.MAX_VALUE;
		if (L <= nodeL && nodeR <= R)
			return tree[nodeNum];
		int mid = (nodeL + nodeR) >> 1;
		return Math.min(getMin(L, R, nodeNum << 1, nodeL, mid), getMin(L, R, (nodeNum << 1) + 1, mid + 1, nodeR));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Student[] arr = new Student[N];

		while (size < N << 1) {
			size <<= 1;
		}

		tree = new int[size];
		for (int i = 0; i < N; i++) {
			arr[i] = new Student();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { // 0 - based
			int cur = Integer.parseInt(st.nextToken()) - 1;
			arr[cur].r1 = i;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { // 0 - based
			int cur = Integer.parseInt(st.nextToken()) - 1;
			arr[cur].r2 = i;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { // 0 - based
			int cur = Integer.parseInt(st.nextToken()) - 1;
			arr[cur].r3 = i;
		}

		Arrays.sort(arr); // 첫 번째 등수를 기준으로 정렬
		init(); // tree 모두 MAX값으로 변경

		int answer = 0;

		for (int i = 0; i < N; i++) {
			Student cur = arr[i];
			if (getMin(0, cur.r2, 1, 0, size / 2 - 1)
				> cur.r3) { // 현재 학생보다 3번째 시험이 등수가 앞서는 학생이 없으면 answer +1, 첫번째 시험은 sort로, 두번째 시험은 노드 번호로, 세번째 시험은 구간의 최솟값으로
				answer++;
			}
			update(cur.r2, cur.r3); // 2번째 시험의 번호에 3번째 시험 등수로 업데이트
		}
		System.out.println(answer);
	}
}
