import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #1431 시리얼 번호 
class Serial implements Comparable<Serial>{
	int length, sum;
	String str;
	public Serial(int length, int sum, String str) {
		this.length = length;
		this.sum = sum;
		this.str = str;
	}
	@Override
	public int compareTo(Serial o) {
		if(this.length != o.length) return this.length - o.length;
		if(this.sum != o.sum) return this.sum - o.sum;
		return this.str.compareTo(o.str);
	}
	
}
public class Main {
	
	static ArrayList<Serial> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			String tmp = br.readLine();
			int sum = 0;
			for(int j = 0 ; j < tmp.length() ; j ++ ) {
				if(tmp.charAt(j) - '0' < 10) {
					sum += tmp.charAt(j) - '0';
				}
			}
			list.add(new Serial(tmp.length(), sum, tmp));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(Serial x : list) {
			sb.append(x.str + "\n");
		}
		System.out.println(sb);
	}
}
