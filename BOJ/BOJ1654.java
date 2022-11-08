import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1654 랜선 자르기
public class Main {

	static ArrayList<Integer> list = new ArrayList<>();
	
	static int findSum(long length) {
		int sum = 0;
		for(int i = 0 ; i < list.size() ; i ++) {
			sum += list.get(i) / length;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < K ; i ++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list);
		long lt = 0, rt = Integer.MAX_VALUE;
		rt += 1;
		while(lt < rt) {
			long mid = (lt + rt) / 2;
			int res = findSum(mid);
			if(res < N) rt = mid;
			else lt = mid + 1;
		}
		System.out.println(lt - 1);
	}
}
