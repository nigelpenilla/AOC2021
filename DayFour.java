import java.io.File;
import java.util.HashMap;
import java.util.*;
import java.io.*;

public class DayFour {
    static ArrayList<Board> boardList;
    static int[] select;
    public static void main(String[] args) throws Exception {
        boardList = new ArrayList<>();
        
        File input = new File("day4-input.txt");
        buildInput(input);
    
        //int partOne = partOne();
        //System.out.println(partOne);

        int partTwo = partTwo();
        System.out.println(partTwo);
    }

    public static int partTwo() {
        for(int num: select) {
            ArrayList<Board> tempBoardList = new ArrayList<>(boardList);

            for(Board board: boardList) {
                if(board.map.containsKey(num)) {
                    int[] pair = board.map.get(num);
                    int row = pair[0];
                    int col = pair[1];
                    board.rowCount[row]++;
                    board.colCount[col]++;
                    board.map.remove(num);
                    if(board.rowCount[row] == 5 || board.colCount[col] == 5) {
                        if(tempBoardList.size() > 1) tempBoardList.remove(board);
                        else return sumWinner(board) * num;
                    }
                }
            }
            boardList = new ArrayList<>(tempBoardList);
        }
        return 0;
    }

    public static int partOne() {
        for(int num: select) {
            for(Board board: boardList) {
                if(board.map.containsKey(num)) {
                    int[] pair = board.map.get(num);
                    int row = pair[0];
                    int col = pair[1];
                    board.rowCount[row]++;
                    board.colCount[col]++;
                    board.map.remove(num);
                    if(board.rowCount[row] == 5 || board.colCount[col] == 5) {
                        return sumWinner(board) * num;
                    }
                }
            }
        }
        return 0;
    }

    public static int sumWinner(Board board) {
        int sum = 0;
        for(int key: board.map.keySet()) {
            sum += key;
        }
        return sum;
    }

    public static void buildInput(File input) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();

        String[] nums = line.split(",");
        select = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            select[i] = (Integer.parseInt(nums[i]));
        }
        line = br.readLine();
        line = br.readLine();

        int[][] currBoard = new int[5][5];
        int row = 0;
        while(line != null) {
            if(line.length() == 0) {
                Board board = new Board(currBoard);
                boardList.add(board);
                currBoard = new int[5][5];
                row = 0;
            }
            else {
                String[] currRow = line.trim().split("\\s+");
                for(int i = 0; i < currRow.length; i++) {
                    currBoard[row][i] = Integer.parseInt(currRow[i]);
                }
                row++;
            }
            line = br.readLine();
        }
        Board board = new Board(currBoard);
        boardList.add(board);
        br.close();
    }
}

class Board {
    HashMap<Integer, int[]> map;
    int[] rowCount;
    int[] colCount;

    public Board(int[][] flatBoard) {
        this.map = new HashMap<>();
        this.rowCount = new int[5];
        this.colCount = new int[5];

        for(int i = 0; i < flatBoard.length; i++) {
            for(int j = 0; j < flatBoard[i].length; j++) {
                map.put(flatBoard[i][j], new int[]{i, j});
            }
        }
        
    }
}
