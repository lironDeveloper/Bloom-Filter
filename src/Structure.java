import java.util.BitSet;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.google.common.hash.*;

public class Structure {
    private int structureSize;
    private int hashFunctionsAmount;
    private BitSet bitArray;

    public Structure(int structureSize, int hashFunctionsAmount) {
        this.setStructureSize(structureSize);
        this.setHashFunctionsAmount(hashFunctionsAmount);
        this.bitArray = new BitSet(structureSize);
        initStructure();
    }

    private void initStructure(){
        try{
            File inputFile = new File("input.txt");
            Scanner fileReader = new Scanner(inputFile);
            while (fileReader.hasNextLine()) {
                String numbersLine = fileReader.nextLine();
                String[] numbers = numbersLine.split("\\s*,\\s*");
                for(int i = 0; i < numbers.length; i++) {
                    for(int seed = 1; seed <= this.getHashFucntionsAmount(); seed++){ 
                        HashFunction hf = Hashing.murmur3_32(seed);
                        int hash = hf.hashInt(Integer.parseInt(numbers[i])).asInt();
                        this.bitArray.set(Math.abs(hash) % this.getStructureSize()); 
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testStructure(){
        try {
            File testFile = new File("testFile.txt");
            Scanner fileReader = new Scanner(testFile);
            while (fileReader.hasNextLine()) {
                String numbersLine = fileReader.nextLine();
                String[] numbers = numbersLine.split("\\s*,\\s*");
                for(int i = 0; i < numbers.length; i++) {
                    if(isNumberInStructure(Integer.parseInt((numbers[i])))) {
                        System.out.println("The number " + numbers[i] + " is part of the structure!");
                    } else {
                        System.out.println("The number " + numbers[i] + " is not part of the structure!");
                    }  
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStructureSize(int structureSize) {
        this.structureSize = structureSize;
    }

    private void setHashFunctionsAmount(int hashFunctionsAmount) {
        this.hashFunctionsAmount = hashFunctionsAmount;
    }

    private int getStructureSize(){
        return this.structureSize;
    }

    private int getHashFucntionsAmount(){
        return this.hashFunctionsAmount;
    }

    private boolean isNumberInStructure(int num) {
        for(int seed = 1; seed <= this.getHashFucntionsAmount(); seed++){            
            HashFunction hf = Hashing.murmur3_32(seed);
            int hash = hf.hashInt(num).asInt();
            if(!this.bitArray.get(Math.abs(hash) % this.getStructureSize()))
                return false;
        }
        return true;
    }
}
