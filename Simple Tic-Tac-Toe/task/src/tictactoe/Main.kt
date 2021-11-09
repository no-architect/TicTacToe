package tictactoe

fun main() {
    // write your code here
    val curState = readLine()!!
    if (curState.length == 9) {
        print("---------\n| ")
        for (cur in 0..curState.lastIndex){
            print(curState[cur] + " ")
            if (((cur + 1) % 3 == 0) && (cur > 0) && (cur < 8)){
                print("|\n| ")
            }
        }
        println("|\n---------")
    } else println("no correct")
}