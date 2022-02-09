package codingTest_018;

import java.util.Scanner;

public class Main {

	public int result(int num) {
		int answer = 0;
		int[] ch = new int[num +1];
		for(int i = 2; i <= num; i++) {
			if(ch[i] == 0) {
				answer++;
				for(int j = i ; j <=num; j = j +i) ch[j] = 1;
			}
		}
		/*int answer = 1;
		int t = 0;

		if (num > 3)
			answer++;

		for (int i = 4; i <= num; i++) {
			for (int k = 2; k <= i / 2; k++) {
				if (i % k == 0) {
					t++;
				}

			}
			if (t == 0) {
				answer++;

			} else
				t = 0;

		}

		return answer;
		*/
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		System.out.println(T.result(num));

	}

}
