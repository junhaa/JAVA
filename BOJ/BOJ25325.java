import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

// BOJ #25325 학생 인기도 측정
class Student implements Comparable<Student>{
	String name;
	int cnt;
	public Student(String name, int cnt) {
		this.name = name;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Student o) {
		if(o.cnt == this.cnt) {
			return this.name.compareTo(o.name);
		}
		else return o.cnt - this.cnt;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Integer> map = new TreeMap<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			map.put(st.nextToken(), 0);
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int len = st.countTokens();
			for(int j = 0 ; j < len ; j ++) {
				String tmp = st.nextToken();
				map.put(tmp, map.get(tmp) + 1);
			}
		}
		StringBuilder sb = new StringBuilder();
		ArrayList<Student> list = new ArrayList<>();
		for(String x : map.keySet()) {
			list.add(new Student(x, map.get(x)));
		}
		Collections.sort(list);
		for(Student s : list) {
			sb.append(s.name + " " + s.cnt + "\n"); 
		}
		System.out.println(sb);
	}

}
