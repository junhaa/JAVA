import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #10814 나이순 정렬
class Member implements Comparable<Member>{
	int age;
	String name;
	public Member(int age, String name) {
		this.age = age;
		this.name = name;
	}
	public int compareTo(Member o) {
		return this.age - o.age;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<Member> list = new ArrayList<>();
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		Collections.sort(list);
		for(int i = 0 ; i < N ; i ++) {
			sb.append(list.get(i).age + " " + list.get(i).name + "\n");
		}
		System.out.println(sb);
		
	}
}
