package codingTest_032;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public String solution (String str1 , String str2) {
		
		String answer = "";
		
		HashMap<Character, Integer> map1 = new HashMap<>();
		HashMap<Character, Integer> map2 = new HashMap<>();
		
		
		for(char x : str1.toCharArray()) {
			map1.put(x, map1.getOrDefault(x,0)+1);
			
		}
		
		for(char t : str2.toCharArray()) {
			map2.put(t, map2.getOrDefault(t,0)+1);
			
		}
		
		int cnt = 0;
		for(char key : map1.keySet()) {
			if(map2.containsKey(key) == true) {
				if(map1.get(key) == map2.get(key)) {
					cnt++;
				}
				
			}
		}
		if(cnt == map1.size()) {
			answer = "YES";
		}
		else answer = "NO";
		
		if(map1.size() != map2.size()) {
			answer = "NO";
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
