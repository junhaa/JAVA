package codingTest_041;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public int solution(int num1, int num2) {
		int answer;
		int cnt = 1;
		Queue<Integer> Q = new LinkedList<>();
		for (int i = 1; i <= num1; i++) {
			Q.offer(i);
		}

		while (true) {

			if (Q.size() == 1) {
				break;
			}
			if (cnt % num2 == 0) {
				Q.poll();
			} else {

				Q.offer(Q.poll());
			}

			cnt++;
		}

		answer = Q.poll();
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num1 = sc.nextInt();
		int num2 = sc.nextInt();

		System.out.println(T.solution(num1, num2));

	}

}
