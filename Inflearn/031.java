package codingTest_031;

import java.util.*;

public class Main {

	public char result(int student, String vote) {

		char answer = ' ';
		HashMap<Character, Integer> map = new HashMap<>();

		for (char x : vote.toCharArray()) {
			map.put(x, map.getOrDefault(x, 0) + 1); // getOrDefault -- > 값이 없으면 원하는 값을 반환
													// containsKey() Key 값이 존재하는지 boolean 으로 반환 / size() Key값이 몇개 있는지
													// remove() Key 삭제 ==> key가 가지고 있던 값 반환
		}
		int max = Integer.MIN_VALUE;
		for (char key : map.keySet()) {
			if (map.get(key) > max) {

				max = map.get(key);
				answer = key;
			}
		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int student = sc.nextInt();
		String vote = sc.next();

		System.out.println(T.result(student, vote));

	}
}
