class Solution {
  public boolean exist(char[][] board, String word) {

  }
}
// think of the 2D board as a graph
// indices as edges, numbers as vertices
// we perform a DFS on the 2D board representation of the graph
// trying to determine if a specific kind of path exists
// 
// as we traverse from one letter to the next
// we mark the letter as being processed
// when we reach a dead-end - meaning have not matched every character in teh string
// we mark a cell in the 2d grid as black
// and backtrack
// this can be a recursive process of returning false
// indicating there is no such path found
// and that we should continue to traverse in a DFS manner
// until we either reach the end of the string
// or we exhaust all options

// time: 0(VE) - DFS

