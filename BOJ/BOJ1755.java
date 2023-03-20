import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1755 숫자놀이
class Number implements Comparable<Number>{
	int num;
	String str= "";
	public Number(String input) {
		this.num = Integer.parseInt(input);
		int len = input.length();
		for(int i = 0 ; i < len ; i ++) {
			switch (input.charAt(i) - '0') {
			case 0: {
				str += "zero ";
				break;
			}
			case 1 : {
				str += "one ";
				break;
			}
			case 2 : {
				str += "two ";
				break;
			}
			case 3 : {
				str += "three ";
				break;
			}
			case 4 : {
				str += "four ";
				break;
			}
			case 5 : {
				str += "five ";
				break;
			}
			case 6 : {
				str += "six ";
				break;
			}
			case 7 : {
				str += "seven ";
				break;
			}
			case 8 : {
				str += "eight ";
				break;
			}
			case 9 : {
				str += "nine ";
				break;
			}
			}
		}
	}
	@Override
	public int compareTo(Number o) {
		return this.str.compareTo(o.str);
	}
}
public class Main {
	
	static ArrayList<Number> list = new ArrayList<Number>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		for(int i = M ; i <= N ; i ++) {
			list.add(new Number(String.valueOf(i)));
		}
		Collections.sort(list);
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for(Number x : list) {
			sb.append(x.num + " ");
			if(++cnt % 10 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}
