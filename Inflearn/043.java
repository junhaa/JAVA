package codingTest_043;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class AscendingInteger implements Comparator<Integer> {

		@Override
		public int compare(Integer a, Integer b) {
			return b.compareTo(a);
		}
	}

	public int solution(int num1, int num2, int[] arr) {

		int answer = 0;
		Queue<Integer> Q = new LinkedList<>();

		ArrayList<Integer> arrList = new ArrayList<>();

		for (int i = 0; i < num1; i++) {
			Q.offer(arr[i]);
		}

		for (int i = 0; i < num1; i++) {
			arrList.add(arr[i]);
		}

		Collections.sort(arrList, new AscendingInteger());

		int cnt = num2;
		while (true) {
			if (cnt == 0 && Q.peek() == arrList.get(0)) {
				answer++;

				break;

			}

			else if (Q.peek() == arrList.get(0)) {

				arrList.remove(0);
				Q.poll();
				answer++;
				cnt--;

			} else if (cnt == 0 && Q.peek() != arrList.get(0)) {
				cnt += Q.size() - 1;
				Q.offer(Q.poll());

			} else {
				Q.offer(Q.poll());
				cnt--;
				
			}
		}
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num1 = sc.nextInt();
		int num2 = sc.nextInt();

		int[] arr = new int[num1];

		for (int i = 0; i < num1; i++) {
			arr[i] = sc.nextInt();

		}
		System.out.println(T.solution(num1, num2, arr));
	}

}
