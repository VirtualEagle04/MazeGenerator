# *Depth-First Search<sub>[^1]</sub> Maze Generation Algorithm*
Maze Generator using a **DFS** algoritm with a **_backtracker_**<sub>[^2]</sub> implementation.
Application generates a Java GUI window with the generated maze.

_Pseudocode used:_<sub>[^3]</sub>
  
 > **Recursive implementation:**
  
  The depth-first search algorithm of maze generation is frequently implemented
  This can be described with a following recursive routine:
  1. Given a current cell as a parameter
  2. Mark the current cell as visited
  3. While the current cell has any unvisited neighbour cells
        1. Choose one of the unvisited neighbours
        2. Remove the wall between the current cell and the chosen cell
        3. Invoke the routine recursively for the chosen cell which is invoked once for any initial cell in the area.



[^1]: https://en.wikipedia.org/wiki/Depth-first_search
[^2]: https://en.wikipedia.org/wiki/Backtracking
[^3]: https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search

