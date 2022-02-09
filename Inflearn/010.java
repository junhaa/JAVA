package codingTest_010;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public String result(String str, char ch) {

		String answer = "";

		int i = 300, k = 300;

		int len = str.length();

		int arr[] = new int[len];

		int arr2[] = new int[len];

		for (int r = 0; r < len; r++) {
			if (str.charAt(r) == ch) {
				i = 0;
			}
			arr[r] = i;
			i++;
		}

		for (int t = len - 1; t >= 0; t--) {
			if (str.charAt(t) == ch) {
				k = 0;
			}

			arr2[t] = k;
			k++;
		}

		for (int q = 0; q < len; q++) {
			if (arr[q] < arr2[q]) {
				answer += arr[q];
			} else
				answer += arr2[q];
				answer += " ";
		}

		return answer;

	}

	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		char ch = sc.next().charAt(0);

		System.out.println(T.result(str, ch));

	}

}
