package com.example.a475game;

public class Index2D {

    public int row, col;

    public Index2D(int r, int c)
    {
        row = r; col = c;
    }
    public boolean isAdjacentTo(Index2D other)
    {
        int dRow = Math.abs(row - other.row);
        int dCol = Math.abs(col - other.col);
        return (dRow == 1 && dCol == 0) ||
                (dRow == 0 && dCol == 1);
    }
}