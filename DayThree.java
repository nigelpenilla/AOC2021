import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DayThree {
    public static void main(String[] args) throws Exception {
        File input = new File("day3-input.txt");
        
        //int partOne = partOne(input);
        //System.out.println(partOne);

        int partTwo = partTwo(input);
        System.out.println(partTwo);
    }

    public static int partTwo(File input) throws Exception {
        int[][] getGamEp = getGammaEpsilon(input);
        int[] gamma = getGamEp[0];
        int[] ep = getGamEp[1];

        List<char[]> gamList = new ArrayList<>();
        List<char[]> epList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();

        while(line != null) {
            if(line.charAt(0) - 48 == gamma[0]) gamList.add(line.toCharArray());
            else if(line.charAt(0) - 48 == ep[0]) epList.add(line.toCharArray());
            line = br.readLine();
        }
        br.close();

        for(int i = 1; i < gamma.length; i++) {
            if(gamList.size() > 1) {
                int mostCommon = getMostCommon(gamList, i);
                List<char[]> tempGamList = new ArrayList<>();
                for(int j = 0; j < gamList.size(); j++) {
                    char[] curr = gamList.get(j);
                    if(curr[i] - 48 == mostCommon) tempGamList.add(curr);
                }
                gamList = tempGamList;
            }
            if(epList.size() > 1) {
                int leastCommon = getLeastCommon(epList, i);
                List<char[]> tempEpList = new ArrayList<>();
                for(int j = 0; j < epList.size(); j++) {
                    char[] curr = epList.get(j);
                    if(curr[i] - 48 == leastCommon) tempEpList.add(curr);
                }
                epList = tempEpList;
            }
        }

        int oxygen = binaryToInt(gamList.get(0));
        int carbonDiox = binaryToInt(epList.get(0));
        return oxygen * carbonDiox;
    }

    public static int partOne(File input) throws Exception {
        

        int[][] getGamEp = getGammaEpsilon(input);
        int[] gamma = getGamEp[0];
        int[] ep = getGamEp[1];
        
        int gammaInt = binaryToInt(gamma);
        int epInt = binaryToInt(ep);
        return gammaInt * epInt;
    }

    public static int[][] getGammaEpsilon(File input) throws Exception{

        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();
        int length = line.length();
        int[][] count = new int[length][2];

        while(line != null) {
            for(int i = 0; i < line.length(); i++) {
                count[i][line.charAt(i) - 48]++;
            }
            line = br.readLine();
        }
        //br.close();

        int[] gamma = new int[count.length];
        int[] ep = new int[count.length];
        for(int i = 0; i < count.length; i++) {
            int zeros = count[i][0];
            int ones = count[i][1];

            if(zeros > ones) {
                gamma[i] = 0;
                ep[i] = 1;
            }
            else {
                gamma[i] = 1;
                ep[i] = 0;
            }
        }
        return new int[][]{gamma, ep};
    }
    public static int binaryToInt(int[] binary) {
        int conversion = 0;
        int mult = 1;
        for(int i = binary.length - 1; i >= 0; i--) {
            conversion += (mult * binary[i]);
            mult = mult * 2;
        }
        return conversion;
    }

    public static int binaryToInt(char[] binary) {
        int conversion = 0;
        int mult = 1;
        for(int i = binary.length - 1; i >= 0; i--) {
            conversion += (mult * (binary[i] - 48));
            mult = mult * 2;
        }
        return conversion;
    }

    public static int getMostCommon(List<char[]> list, int index) {
        int zeros = 0;
        int ones = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i)[index] == '0') zeros++;
            else ones++;
        }

        if(zeros > ones) return 0;
        else return 1;
    }

    public static int getLeastCommon(List<char[]> list, int index) {
        int zeros = 0;
        int ones = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i)[index] == '0') zeros++;
            else ones++;
        }

        if(zeros > ones) return 1;
        else return 0;
    }
}
