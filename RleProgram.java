import java.util.Scanner;

public class RleProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option;
String rleInput = " ";
        // 1. Display welcome message
        System.out.println("Welcome to the RLE image encoder!");
        System.out.println("Displaying Spectrum Image:");
        // 2. Display color test with message
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);
        byte[] imageData = null;
        boolean menuP = true;
        byte[] loadData = null;
        while(true){
        System.out.println("RLE Menu"); // menu option
        System.out.println("--------");
        System.out.println("0. Exit");
        System.out.println("1. Load File");
        System.out.println("2. Load Test Image");
        System.out.println("3. Read RLE String");
        System.out.println("4. Read RLE Hex String");
        System.out.println("5. Read Data Hex String");
        System.out.println("6. Display Image");
        System.out.println("7. Display RLE String");
        System.out.println("8. Display Hex RLE Data");
        System.out.println("9. Display Hex Flat Data");
        // 3. Display the menu - Part A while loop or if-else claims
        // option 1, option 2, option 6
        System.out.print("Select a Menu Option: ");
        option = input.nextInt();

            String final2;
            String final1;
            String final3;
        switch (option) {
            case 0:
                System.exit(0);
                break;
            // 3.1 -option 1
            // ConsoleGfx.loadFile(userInput) and store return value into image array
            case 1:
                String fileT;
                System.out.print("Enter name of file to load: ");
                fileT = input.next();

                loadData = ConsoleGfx.loadFile(fileT);
                //3.6 option 6
                //display image inside of imageData array
                continue;
            case 3:
                System.out.println("Enter an RLE string to be decoded: ");
                rleInput= input.next();
                loadData = decodeRle(stringToRle(rleInput));
                continue;
            case 4:
                System.out.println("Enter the hex string holding RLE data: ");
                String hexS = input.next();
                loadData = decodeRle(stringToData(hexS));
                continue;
            case 5:

                System.out.println("Enter the hex string holding flat data:");
                String hexString = input.next();
                loadData = stringToData(hexString);
                continue;
            case 6:
                System.out.println("Displaying image...");
                ConsoleGfx.displayImage(loadData);
                continue;
            case 7:
                System.out.println("RLE representation:" + toRleString(encodeRle(loadData)));
                continue;
            case 9:
                System.out.println("Flat hex values: " + toHexString(loadData));

                continue;
            case 8:

                System.out.println(toRleString(encodeRle(loadData)));
                break;
            //3.2 option 2
            //store ConsoleGfx.testImage into the imageData array
            case 2:

                System.out.println("Test image data loaded.");
loadData = ConsoleGfx.testImage;
                continue;
        }
    }}


    //3.6 option 6
    //display image inside of imageData array

    //user first might enter 1->6 or 2->6

    //complete option 0, 4, 5, 7, 8

    // 4. Prompt for input


    public static int getDecodedLength(byte[] rleData) { // this is method 4
        int res1 = 0;
        for (int i = 0; i < rleData.length; i++) {
            if (i % 2 == 0) {
                res1 += rleData[i];
            }
        }
        return res1;
    }

    public static int countRuns(byte[] flatData) { // this is method 2
        int group = 1;
        int count = 1;
        for (int i = 0; i < flatData.length - 1; i++) {
            if (flatData[i] == flatData[i + 1]) {
                count++;
            }
            if ((flatData[i] != flatData[i + 1]) || (count == 15)) {
                group++;
                count = 1;
            }
        }
        return group;
    }

    public static byte[] encodeRle(byte[] flatData) { // this is method 3
        byte res[] = new byte[2 * countRuns(flatData)];
        int a = 0;
        int count = 1;
        int num = 0;
        // 555557812
        for(int j = 0; j < flatData.length-1; j++){
            num = flatData[j];

            if ((flatData[j] != flatData[j + 1]) || (count == 15)) {

                res[a++] = (byte)count;
                res[a++] = (byte) num;
                count = 1;
            }
            else {
                count++;
            }
        }
// for the final numbers
        res[a++] = (byte)count;
        res[a++] = flatData[flatData.length-1];
        return res;
    }

    public static byte[] decodeRle(byte[] rleData) { // this is method 5
        int index = 0;
        byte[] res = new byte[getDecodedLength(rleData)];
// loop for array rle data
        for (int i = 0; i < rleData.length; i += 2) {
            for (int j = 0; j < rleData[i]; j++) {
                res[index] = rleData[i+1];
                if (index == res.length-1) {
                    break;
                }
                index++;
            }

        }
        return res;
    }

    public static byte[] stringToData(String dataString) {// this is method 6
        int count = 0;
        byte res[] = new byte[dataString.length()];
        // loop for array res
        for (int i = 0; i < dataString.length(); i++) {
            if (Character.isDigit(dataString.charAt(i))) {
                res[i] = (byte) (dataString.charAt(i) - '0');

            }
            else{
            res[i] = (byte) (Character.toLowerCase(dataString.charAt(i)) - 'a' + 10);}
        }
        return res;
    }

    public static String toHexString(byte[] data){ // method 1
        String res = "";
        for(byte b : data){
            if(b<10){
                res = res + b;
            }
            else{
                res = res + (char)('a'+b-10);
            }
        }
        return res;
    }
    public static String toRleString(byte[]rleData){ // method 7
        //variable to store the result
         String res = "";
        for (int i = 0; i < rleData.length; i += 2) {
            res += rleData[i];
            if (rleData[i + 1] >= 0 && rleData[i + 1] <= 9) {
                res += (char) ('0' + rleData[i + 1]);
            } else {
                res += (char) ('a' + (rleData[i + 1] - 10));
            }
            if (i != rleData.length - 2) {
                res += ":";
            }
        }
        return res;
    }
    //
    public static byte[] stringToRle(String rleString){ // method 8
        String hexString = "";
        char a1;
        char b1;
        String c1;
        int c2;
        String[] array;
        array = rleString.split("\\:");
        byte[] arrayFinal;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() % 2 == 0) {
                a1 = array[i].charAt(0);
                b1 = array[i].charAt(1);
                hexString += a1;
                hexString += b1;
            }
            else {
                c1 = "" + array[i].charAt(0) + array[i].charAt(1);
                c2 = Integer.parseInt(c1);
                b1 = array[i].charAt(2);
                hexString+=Integer.toHexString(c2);
                hexString+= b1;
            }
        }
        arrayFinal = stringToData(hexString);
        return arrayFinal;
    }
}






