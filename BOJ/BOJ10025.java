import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #10025 게으른 백곰
class Ice implements Comparable<Ice>{
	int g, x; // g == 얼음의 양 x == 양동이의 좌표
	public Ice(int g, int x) {
		this.g = g;
		this.x = x;
	}
	@Override
	public int compareTo(Ice o) {
		return this.x - o.x;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Ice> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Ice(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int lt = 0, rt = 0;
		int sum = list.get(0).g;
		int max = sum;
		while(lt != list.size() - 1) {
			if(rt == list.size() - 1) {
				if(list.get(rt).x - list.get(lt).x <= 2 * K) {
					max = Math.max(sum, max);
					break;
				}
				else {
					sum -= list.get(lt).g;
					lt ++;
				}
			}
			else {
				if(list.get(rt).x - list.get(lt).x <= 2 * K) {
					max = Math.max(sum, max);
					rt ++;
					sum += list.get(rt).g;
				}
				else {
					sum -= list.get(lt).g;
					lt ++;
				}
			}
		}
		max = Math.max(sum, max);
		System.out.println(max);
	}
}
