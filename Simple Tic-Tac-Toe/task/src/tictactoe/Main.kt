package tictactoe

// function find "XXX" on horizontal/vertical/diagonal
fun winsX( str: String): Boolean {

    if (Regex("(XXX.{6})|(...XXX...)|(.{6}XXX)").containsMatchIn(str) ||
        Regex("X..X..X").containsMatchIn(str) ||
        Regex("(X...X...X)|(..X.X.X..)").containsMatchIn(str)) {
        return true
    }
    return false
}

// function find "OOO" on horizontal/vertical/diagonal
fun winsO( str: String): Boolean {

    if (Regex("(OOO.{6})|(...OOO...)|(.{6}OOO)").containsMatchIn(str) ||
        Regex("O..O..O").containsMatchIn(str) ||
        Regex("(O...O...O)|(..O.O.O..)").containsMatchIn(str)) {
        return true
    }
    return false
}

// function return result current state
fun resStr( str: String): String {
    val countX = Regex("X").findAll(str).count()
    val countO = Regex("O").findAll(str).count()
    val countE = Regex("_").findAll(str).count()
    if (kotlin.math.abs(countX - countO) > 1) { return "Impossible " }
    if (winsO(str) && winsX(str)) { return "Impossible" }
    if (winsX(str)) { return "X wins" }
    if (winsO(str)) { return "O wins" }
    if (countE == 0) { return "Draw" }
    return "Game not finished"
}

val pr = "| X X X |"

// function print Cell in format
fun printCell(str1: String) {
    val str = str1.replace("_", " ")
    if (str.length == 9) {
        print("---------\n| ")
        for (cur in 0..str.lastIndex){
            print(str[cur] + " ")
            if (((cur + 1) % 3 == 0) && (cur > 0) && (cur < 8)){
                print("|\n| ")
            }
        }
        println("|\n---------")
    } else println("no correct")
}

// function conver coordinat to index
fun convertCoordinatToIndex(cell: String): Int {
    return (cell.replace(" ", "").toInt() / 10 -1) * 3 + cell.replace(" ", "").toInt() % 10 -1
}

// function return result verification can be changed cell.xy in cells
fun canBeChangedCell(cells: String, xy: String): Boolean {
    val inCell = xy.replace(" ", "")
    // verification input non-numeric two symbols
    if (inCell.length != 2 && inCell.toIntOrNull() == null) { println("You should enter numbers!"); return false }
    if (Regex("[1-3]").findAll(inCell).count() != 2 || inCell.length != 2) { println("Coordinates should be from 1 to 3!"); return false }
    if (cells[convertCoordinatToIndex(inCell)] != '_') {
        println("This cell is occupied! Choose another one!")
        return false }
    return true
}

//function return cells after changed
fun changeCell(cells: String, cell: String): String {
    val inCell = cell.replace(" ", "")
    if (canBeChangedCell(cells, inCell)) {
        return cells.replaceRange(convertCoordinatToIndex(inCell), convertCoordinatToIndex(inCell) + 1, "X")
    }
    return cells
}

fun changeCellXO(cells: String, cell: String, xnoty: Boolean): String {
    val inCell = cell.replace(" ", "")
    if (canBeChangedCell(cells, inCell)) {
        return cells.replaceRange(convertCoordinatToIndex(inCell), convertCoordinatToIndex(inCell) + 1, if (xnoty) "X" else "O")
    }
    return cells
}

fun main() {
//    print("Enter cells:")
    var curState = "_________"
    var symbolXnotO = true
    printCell(curState)
    print("Enter the coordinates:")
    var curCell = readLine()!!

    while (resStr(curState) == "Game not finished") {
        while (!canBeChangedCell(curState, curCell)) {
            print("Enter the coordinates:")
            curCell = readLine()!!
        }
        curState = changeCellXO(curState, curCell, symbolXnotO)
        symbolXnotO = !symbolXnotO
        printCell(curState)
    }

    println(resStr(curState))

}