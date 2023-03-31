import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
// BOJ #27922 현대모비스 입사 프로젝트
class Lec{
	int a, b, c;
	public Lec(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		ArrayList<Lec> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Lec(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list, new Comparator<Lec>() {
			@Override
			public int compare(Lec l1, Lec l2) {
				return (l2.a + l2.b) - (l1.a + l1.b);
			}
		});
		int tmp = 0;
		for(int i = 0 ; i < K ; i ++) {
			tmp += list.get(i).a + list.get(i).b;
		}
		max = Math.max(max, tmp);
		
		Collections.sort(list, new Comparator<Lec>() {
			@Override
			public int compare(Lec l1, Lec l2) {
				return (l2.b + l2.c) - (l1.b + l1.c);
			}
		});
		tmp = 0;
		for(int i = 0 ; i < K ; i ++) {
			tmp += list.get(i).b + list.get(i).c;
 		}
		max = Math.max(max, tmp);
		
		Collections.sort(list, new Comparator<Lec>() {
			@Override
			public int compare(Lec l1, Lec l2) {
				return (l2.a + l2.c) - (l1.a + l1.c);
			}
		});
		tmp = 0;
		for(int i = 0 ; i < K ; i ++) {
			tmp += list.get(i).a + list.get(i).c;
		}
		max = Math.max(max, tmp);
		
		System.out.println(max);
	}
}
