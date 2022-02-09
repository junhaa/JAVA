package codingTest_036;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public String solution(String str) {

		String answer = "YES";

		HashMap<Character, Integer> map = new HashMap<>();

		char[] charArr = new char[str.length()];

		for (int i = 0; i < str.length(); i++) {
			charArr[i] = str.charAt(i);
		}

		if (charArr[0] != '(' || charArr[str.length() - 1] != ')') {
			return "NO";
		}

		for (char x : charArr) {

			map.put(x, map.getOrDefault(x, 0) + 1);

			if (map.get('(') < map.getOrDefault(')', 0)) {
				return "NO";
			}
		}

		if (map.get('(') != map.get(')')) {
			return "NO";
		}
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		System.out.println(T.solution(str));
	}
}
