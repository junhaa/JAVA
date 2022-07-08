import java.util.Scanner;

// BOJ #4999 ¾Æ!
public class Main {
	public String solution(String str1, String str2) {
		if(str1.length() < str2.length()) return "no";
		else return "go";
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		String str2 = sc.next();
		System.out.println(T.solution(str1, str2));
	}
}
