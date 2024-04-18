// BOJ #2847 게임을 만든 동준이

import Foundation

let N : Int = Int(readLine()!)!

var arr = [Int](repeating:0, count:N)

for i in 0...N-1{
    arr[i] = Int(readLine()!)!
}

var max : Int = arr[N - 1]
var answer: Int = 0

for i in 1...N - 1{
    let cur : Int = arr[N - 1 - i]
    if(cur > max - 1){
        answer += cur - max + 1;
        arr[N - 1 - i] = max - 1;
    }
    max = arr[N - 1 - i];
}

print(answer)
