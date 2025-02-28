package Figures;


public class Queen extends Figure{
    public Queen(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if((row == row1 && col !=col1) ||(row != row1 && col ==col1) || (Math.abs(row - row1) == Math.abs(col-col1)) ){
            return true;
        }

        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return this.canMove(row, col, row1, col1);
    }

    @Override
    public int[] getPathCells(int row, int col, int row1, int col1) {
        int n = Math.max(Math.abs(row1 - row), Math.abs(col1 - col)) - 1; // Количество клеток между началом и концом
        int[] cells = new int[2 * n];

        if (row == row1) { // Горизонтальное перемещение
            int step = (col1 - col) / Math.abs(col1 - col);
            for (int i = 0; i < n; i++) {
                cells[2 * i] = row; // Строка остается той же
                cells[2 * i + 1] = col + (i + 1) * step;
            }
        } else if (col == col1) { // Вертикальное перемещение
            int step = (row1 - row) / Math.abs(row1 - row);
            for (int i = 0; i < n; i++) {
                cells[2 * i] = row + (i + 1) * step;
                cells[2 * i + 1] = col; // Колонка остается той же
            }
        } else { // Диагональное перемещение
            int rowStep = (row1 - row) / Math.abs(row1 - row);
            int colStep = (col1 - col) / Math.abs(col1 - col);
            for (int i = 0; i < n; i++) {
                cells[2 * i] = row + (i + 1) * rowStep;
                cells[2 * i + 1] = col + (i + 1) * colStep;
            }
        }

        return cells;
    }
}
