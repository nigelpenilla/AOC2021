import java.io.*;

public class DayTwo {

    public static void main(String[] args) throws Exception {
        DayTwo dayOne = new DayTwo();
        File input = new File("day2-input.txt");
        //int partOne = dayOne.partOne(input);
        int partTwo = dayOne.partTwo(input);
        System.out.println(partTwo);
    }

    public int partTwo(File input) throws Exception {
        int horizontal = 0, vertical = 0, aim = 0;

        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();

        while(line != null) {
            String[] splitLine = line.split(" ");
            String direction = splitLine[0];
            int val = Integer.parseInt(splitLine[1]);

            if(direction.equals("forward")) {
                horizontal += val;
                vertical += (aim * val);
            }
            else if(direction.equals("up")) aim -= val;
            else if(direction.equals("down")) aim += val;
            line = br.readLine();
        }
        br.close();
        return horizontal * vertical;
    }

    public int partOne(File input) throws Exception {
        int horizontal = 0, vertical = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();

        while(line != null) {
            String[] splitLine = line.split(" ");
            String direction = splitLine[0];
            int val = Integer.parseInt(splitLine[1]);

            if(direction.equals("forward")) horizontal += val;
            else if(direction.equals("up")) vertical -= val;
            else if(direction.equals("down")) vertical += val;
            line = br.readLine();
        }
        br.close();
        
        return horizontal * vertical;
    }
}