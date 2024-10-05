import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
// BOJ #2141 우체국 
class City implements Comparable<City> {
	int x, a;

	public City(int x, int a) {
		this.x = x;
		this.a = a;
	}

	@Override
	public int compareTo(City city) {
		return this.x - city.x;
	}
}
// BOJ #2141 우체국
public class Main {
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<City> cityList = new ArrayList<>();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		long cnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			cnt += a;
			cityList.add(new City(x, a));
		}

		Collections.sort(cityList);
		System.out.println(main.getMid(cnt, cityList));
	}

	private int getMid(long cnt, List<City> cityList){
		if(cnt % 2 == 1) cnt ++;
		long curSum = 0;
		for(int i = 0 ; i < cityList.size() ; i ++){
			curSum += cityList.get(i).a;
			if(curSum >= cnt / 2) return cityList.get(i).x;
		}
		return -1;
	}
}
