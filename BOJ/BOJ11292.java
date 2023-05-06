import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #11292 키 큰 사람
class Person implements Comparable<Person>{
	double height;
	String name;
	public Person(String name, double height) {
		this.name = name;
		this.height = height;
	}
	@Override
	public int compareTo(Person o) {
		if(o.height - this.height > 0) return 1;
		else if (o.height - this.height < 0) return -1;
		else return 0;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st ;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			ArrayList<Person> list = new ArrayList<>();
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Person(st.nextToken(), Double.parseDouble(st.nextToken())));
			}
			Collections.sort(list);
			double max = list.get(0).height;
			sb.append(list.get(0).name + " ");
			for(int i = 1 ; i < list.size() ; i ++) {
				if(list.get(i).height == max) {
					sb.append(list.get(i).name + " ");
				}
				else break;
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
