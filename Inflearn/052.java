
package codingTest_052;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public int solution(int N, int M, int[] arr) {
		int answer;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i];
		}
		int mid = (sum + arr[N - 1]) / 2;
		Queue<Integer> Q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			Q.add(arr[i]);
		}
		for (int i = 0; i < M; i++) {

			int arrSum = 0;
			for (int k = 0; k < N; k++) {
				if (Q.isEmpty()) {
					break;
				} else if (arrSum + Q.peek() > mid) {
					break;
				} else {
					arrSum += Q.poll();
				}

			}
		}
		int cnt = 0;
		if (Q.isEmpty()) {
			cnt = -1;
		} else
			cnt = 1;
		while (true) {
			Queue<Integer> Q2 = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				Q2.add(arr[i]);
			}
			for (int i = 0; i < M; i++) {
				int arrSum = 0;
				for (int k = 0; k < N; k++) {
					if (Q2.isEmpty()) {
						break;
					} else if (arrSum + Q2.peek() > mid) {
						break;
					} else
						arrSum += Q2.poll();
				}
			}
			if (cnt == -1 && !Q2.isEmpty()) {
				answer = mid + 1;
				break;
			} else if (cnt == 1 && Q2.isEmpty()) {
				answer = mid;
				break;
			} else
				mid += cnt;

		}
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(T.solution(N, M, arr));
	}

}
