import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1379 강의실 2
class Lecture implements Comparable<Lecture>{
    int num, start, end;
    public Lecture(int num, int start, int end){
        this.num = num;
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Lecture O){
        if(this.start == O.start) return this.end - O.end;
        else return this.start - O.start;
    }
}
class Room implements Comparable<Room>{
    int end, num;
    public Room(int end, int num){
        this.end = end;
        this.num = num;
    }
    @Override
    public int compareTo(Room O){
        return this.end - O.end;
    }
}
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Room> pQ = new PriorityQueue<>();
        ArrayList<Lecture> list = new ArrayList<>();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] answer = new int[N];
        int maxR = 0;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            list.add(new Lecture(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        for(int i = 0 ; i < list.size() ; i ++){
            Lecture tmp = list.get(i);
            if(!pQ.isEmpty() && pQ.peek().end <= tmp.start){
                Room cur = pQ.poll();
                answer[tmp.num] = cur.num;
                pQ.offer(new Room(tmp.end, cur.num));
            }
            else{
                maxR ++;
                answer[tmp.num] = maxR;
                pQ.offer(new Room(tmp.end, maxR));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxR + "\n");
        for(int i = 0 ; i < N ; i ++){
            sb.append(answer[i] + "\n");
        }
        System.out.println(sb);
    }
}
    
