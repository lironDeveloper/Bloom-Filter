import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter structure size");
        int structureSize = scanner.nextInt();
        System.out.println("Enter hash functions count");
        int hashFunctionsCount = scanner.nextInt();
        scanner.close();
        Structure s = new Structure(structureSize, hashFunctionsCount);
        s.testStructure();
    }
}
