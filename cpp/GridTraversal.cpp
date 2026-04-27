#include <vector>
#include <queue>
#include <iostream>
using namespace std;

class GridTraversal
{
private:
    class Cell
    {
    public:
        int row;
        int col;
        int dist;
        Cell(int row, int col, int dist)
        {
            this->row = row;
            this->col = col;
            this->dist = dist;
        }
    };
    int INF = 2147483647;
    bool check(int row, int col, vector<vector<int>> &grid)
    {
        return row >= 0 && col >= 0 && row < grid.size() && col < grid[0].size() && grid[row][col] == INF;
    }
    void gridBfs(vector<vector<int>> &grid, queue<Cell> &q)
    {
        vector<int> dirX = {-1, 1, 0, 0};
        vector<int> dirY = {0, 0, -1, 1};
        while (!q.empty())
        {
            Cell cell = q.front();
            q.pop();
            int row = cell.row, col = cell.col, dist = cell.dist;
            for (int i = 0; i < 4; i++)
            {
                int nrow = row + dirX[i];
                int ncol = col + dirY[i];
                if (check(nrow, ncol, grid))
                {
                    grid[nrow][ncol] = 1 + dist;
                    q.push(Cell(nrow, ncol, 1 + dist));
                }
            }
        }
        return;
    }

public:
    void islandsAndTreasure(vector<vector<int>> &grid)
    {
        queue<Cell> q;
        for (int i = 0; i < grid.size(); i++)
        {
            for (int j = 0; j < grid[i].size(); j++)
            {
                if (grid[i][j] == 0)
                {
                    q.push(Cell(i, j, 0));
                }
            }
        }
        gridBfs(grid, q);
        return;
    }
};
int main()
{
    GridTraversal gt;
    int INF = 2147483647;
    vector<vector<int>> grid = {
        {INF, -1, 0, INF},
        {INF, INF, INF, -1},
        {INF, -1, INF, -1},
        {0, -1, INF, INF}};
    cout << "Input grid:\n";
    for (auto &row : grid)
    {
        for (auto &val : row)
        {
            if (val == INF)
                cout << "INF ";
            else
                cout << val << " ";
        }
        cout << endl;
    }
    gt.islandsAndTreasure(grid);
    cout << "\nOutput grid:\n";
    for (auto &row : grid)
    {
        for (auto &val : row)
        {
            if (val == INF)
                cout << "INF ";
            else
                cout << val << " ";
        }
        cout << endl;
    }
    return 0;
}
// mkdir -p build
// g++ GridTraversal.cpp -o build/GridTraversal
// ./build/GridTraversal