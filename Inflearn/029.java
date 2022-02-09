package codingTest_029;

import java.util.Scanner;

public class Main {

	public int result(int num1) {
		int answer = 0;
		int sum = 0;
		int tmp = 1;
		int tmp2 = 2;

		sum = tmp;

		while (true) {

			if (sum > num1) {
				sum -= tmp++;

			} else if (sum == num1) {
				answer++;
				sum -= tmp++;

			} else {
				sum += tmp2++;

			}

			if (tmp2 > (num1 + 3) / 2)
				break;
		}
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num1 = sc.nextInt();
		System.out.println(T.result(num1));

	}

}
