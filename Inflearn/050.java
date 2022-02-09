package codingTest_050;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Point implements Comparable<Point> {
	public int x,y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	@Override
	public int compareTo(Point o) {
		if(this.x == o.x) return this.y - o.y;
		else return this.x - o.x;
	}
	
	
}

public class Main {

	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		ArrayList<Point> arr = new ArrayList<>();
		
		for(int i = 0 ; i < num ; i ++) {
			int x = sc.nextInt();
			int y= sc.nextInt();
			arr.add(new Point(x,y));
		}
		
		Collections.sort(arr);
		for(Point o : arr)System.out.println(o.x + " " + o.y);
		
	}
}