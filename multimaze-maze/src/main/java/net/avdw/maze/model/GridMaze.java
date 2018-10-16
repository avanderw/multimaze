package net.avdw.maze.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Singleton
public class GridMaze implements IMaze {
    private Integer rowCount;
    private Integer colCount;
    private List<Cell> cells;

    @Inject
    private GridMaze(@Named("maze-row-count") Integer defaultRowCount, @Named("maze-col-count") Integer defaultColCount) {
        this.rowCount = defaultRowCount;
        this.colCount = defaultColCount;
    }

    private void rowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    private void colCount(Integer colCount) {
        this.colCount = colCount;
    }

    public void build() {
        cells = new ArrayList();
        IntStream.range(0, rowCount).forEach(row ->
                IntStream.range(0, colCount).forEach(col ->
                        cells.add(new Cell(row, col))
                )
        );

        IntStream.range(0, rowCount).forEach(row ->
                IntStream.range(0, colCount).forEach(col -> {
                    Optional<Cell> currCell = cells.stream()
                            .filter(cell -> cell.row == row)
                            .filter(cell -> cell.col == col)
                            .findAny();
                    Optional<Cell> southCell = cells.stream()
                            .filter(cell -> cell.row == row + 1)
                            .filter(cell -> cell.col == col)
                            .findAny();
                    Optional<Cell> eastCell = cells.stream()
                            .filter(cell -> cell.row == row)
                            .filter(cell -> cell.col == col + 1)
                            .findAny();

                    currCell.ifPresent(cell->{
                        southCell.ifPresent(south-> cell.linkNeighbour(Direction.SOUTH, south));
                        eastCell.ifPresent(east-> cell.linkNeighbour(Direction.EAST, east));
                    });
                })
        );
    }

    @Override
    public List<Cell> cells() {
        return cells;
    }

    @Override
    public Integer rowCount() {
        return rowCount;
    }

    @Override
    public Integer colCount() {
        return colCount;
    }

    @Override
    public String toString() {
        return String.format("{cells=%s}", cells);
    }
}
