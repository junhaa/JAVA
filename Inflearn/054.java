package codingTest_054;

import java.util.Scanner;

public class Main {

	public int solution(int S, int E) {

		int answer = 0;
		int num;
		int num2;
		if (E > S) {
			num2 = E - S;
			answer = num2 /5;
			num	= num2 % 5;
			
			if (num == 4) {
				answer += 2;
			} else
				answer += num;
		} else {
			answer = S - E;
		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int S = sc.nextInt();
		int E = sc.nextInt();

		System.out.println(T.solution(S, E));

	}

}
