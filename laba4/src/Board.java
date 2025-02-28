import Figures.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Board {

    private char colorGame;

    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    public char getColorGame() {
        return colorGame;
    }

    public static boolean gameState = false; // Флаг состояния игры: true - поставлен мат, false - игра продолжается

    private Figure[][] fields = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public void init() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'), new Bishop("B", 'w'),
                new Queen("Q", 'w'), new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'), new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'),
        };


        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b')
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'), new Bishop("B", 'b'),
                new Queen("Q", 'b'), new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'), new Rook("R", 'b')
        };
    }

    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    public void print_board() {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1; row--) {
            System.out.print(row);
            for (int col = 0; col < 8; col++) {
                System.out.print("|" + getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++) {
            System.out.print("    " + col);
        }
    }

    public boolean canMoveOnBoard(Figure[][] field, Figure figure, int row, int col, int row1, int col1) {
        if (figure == null) return false;

        // Фигура не может ходить сама на себя
        if (row == row1 && col == col1) return false;

        // Проверяем, может ли фигура вообще двигаться
        if (!figure.canMove(row, col, row1, col1) && !figure.canAttack(row, col, row1, col1)) {
            return false;
        }

        // Проверяем, не мешают ли другие фигуры на пути
        int[] pathCells = figure.getPathCells(row, col, row1, col1);
        for (int i = 0; i < pathCells.length / 2; i++) {
            if (field[pathCells[2 * i]][pathCells[2 * i + 1]] != null) {
                return false; // Есть препятствие
            }
        }

        // Проверяем, не стоит ли на целевой клетке фигура своего цвета
        if (field[row1][col1] != null && field[row1][col1].getColor() == figure.getColor()) {
            return false;
        }

        // Симулируем ход
        Figure[][] fieldCopy = deepCopyField(field);
        fieldCopy[row][col] = null;
        fieldCopy[row1][col1] = figure;

        // Проверяем, не будет ли шаха после хода
        return !isCheck(fieldCopy, figure.getColor());
    }

    private Figure[][] deepCopyField(Figure[][] field) {
        Figure[][] copy = new Figure[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy[i][j] = field[i][j]; // Поверхностное копирование объектов
            }
        }
        return copy;
    }

    public boolean isCheck(Figure[][] field, char color) {
        int kingX = -1, kingY = -1;

        // Находим короля
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = field[i][j];
                if (figure instanceof King && figure.getColor() == color) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }

        if (kingX == -1 || kingY == -1) {
            throw new IllegalStateException("Король не найден на доске");
        }

        // Проверяем, есть ли фигуры противника, которые могут атаковать короля
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = field[i][j];
                if (figure != null && figure.getColor() != color) {
                    if (figure.canAttack(i, j, kingX, kingY)) {
                        return true; // Король под атакой
                    }
                }
            }
        }
        return false;
    }

    public boolean isMate() {
        char color = getColorGame();

        if (!isCheck(this.fields, color)) {
            return false; // Если нет шаха, то и мата быть не может
        }

        int kingX = -1, kingY = -1;

        // Находим короля
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = this.fields[i][j];
                if (figure instanceof King && figure.getColor() == color) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }

        if (kingX == -1 || kingY == -1) {
            throw new IllegalStateException("Король не найден на доске");
        }

        // Проверяем, может ли король куда-то уйти
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int newX = kingX + dx;
                int newY = kingY + dy;
                if (isValidPosition(newX, newY) && canMoveOnBoard(this.fields, this.fields[kingX][kingY], kingX, kingY, newX, newY)) {
                    return false; // Король может уйти из-под шаха
                }
            }
        }

        // Проверяем, может ли другая фигура защитить короля
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = this.fields[i][j];
                if (figure != null && figure.getColor() == color && !(figure instanceof King)) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            if (canMoveOnBoard(this.fields, figure, i, j, x, y)) {
                                return false; // Есть спасительный ход
                            }
                        }
                    }
                }
            }
        }

        gameState = true; // Устанавливаем флаг окончания игры
        return true; // Мата избежать нельзя
    }

    // Проверка, находится ли позиция на доске
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public boolean move_figure(int row, int col, int row1, int col1) {
        if (gameState) return false; // Игра уже окончена

        Figure figure = this.fields[row][col];
        if (figure == null) return false; // Нет фигуры в этой клетке

        if (!isValidPosition(row1, col1) || !isValidPosition(row, col)) return false;

        if (canMoveOnBoard(this.fields, figure, row, col, row1, col1)) {
            this.fields[row1][col1] = figure;
            this.fields[row][col] = null;

            // Проверяем, привел ли ход к шаху или мату
            if (isMate()) {
                System.out.println("Мат! Игра окончена.");
            }
            return true;
        }

        return false;
    }
}
