package codingTest_034;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public int solution(String str1, String str2) {

		int answer = 0;
		int len = str2.length();
		HashMap<Character, Integer> map = new HashMap<>();
		HashMap<Character, Integer> map2 = new HashMap<>();
		int cnt = 0;

		for (int i = 0; i < len; i++) {
			map2.put(str2.charAt(i), map2.getOrDefault(str2.charAt(i), 0) + 1);
		}

		for (int i = 0; i < len; i++) {
			map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
		}

		for (int i = 0; i < len; i++) {
			if (map2.get(str2.charAt(i)) != map.get(str2.charAt(i))) {
				cnt++;
			}
		}
		if (cnt == 0) {
			answer++;

		}
		cnt = 0;

		int lt = 0;
		for (int rt = len; rt < str1.length(); rt++) {

			map.put(str1.charAt(rt), map.getOrDefault(str1.charAt(rt), 0) + 1);

			map.put(str1.charAt(lt), map.get(str1.charAt(lt)) - 1);
			if (map.get(str1.charAt(lt)) == 0) {
				map.remove(str1.charAt(lt));
			}

			for (int i = 0; i < len; i++) {
				if (map2.get(str2.charAt(i)) != map.get(str2.charAt(i))) {
					cnt++;
				}
			}
			if (cnt == 0) {
				answer++;

			}
			cnt = 0;
			lt++;
		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		String str1 = sc.next();
		String str2 = sc.next();

		System.out.println(T.solution(str1, str2));

	}

}
