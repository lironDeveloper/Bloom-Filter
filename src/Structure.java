import java.util.BitSet;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.google.common.hash.*;

/*
    Class - Structure
    Properties - structureSize(integer - the size of the bitArray),
                 hashFunctionsAmount(integer - the number of hash functions that create the structure),
                 bitArray (BitSet - the bit array that holds the data which element is in the structure)
*/
public class Structure {
    private int structureSize;
    private int hashFunctionsAmount;
    private BitSet bitArray;

    /*
        Builder - sets the properties and calls the initStructure() that inits the structure from the input file.
    */
    public Structure(int structureSize, int hashFunctionsAmount) {
        this.setStructureSize(structureSize);
        this.setHashFunctionsAmount(hashFunctionsAmount);
        this.bitArray = new BitSet(structureSize);
        initStructure();
    }

    /*
        Input - null
        Output - null
        Algorithm - The function scannes an input file(Text file with numbers comma separated) and for each number,
                    it creates k hash functions according to hashFunctionsAmount property, and k times hashes the number.
                    For each result of each hash function, the function creates the absolute number of it and modulu by the bitArray size
                    At the end we set to true the index we calculated in the bitArray.
        Time complexity - O(n*k) while n is hash functions amount the program used to create the structure, and k is the number of the numbers
                         inside the input file.
    */
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

    /*
        Input - null
        Output - null
        Algorithm - The function scannes a test file(Text file with numbers comma separated) and for each number there it tests wheteher the number
                    is in the structure or not by calling isNumberInStructure() function. According to the result, it prints if the number is inside
                    the structure.
        Time complexity - O(n*k) while n is hash functions amount the program used to create the structure, and k is the number of the numbers
                         inside the test file.
    */
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

    /*
        Setter of structureSize property
    */
    private void setStructureSize(int structureSize) {
        this.structureSize = structureSize;
    }

    /*
        Setter of hashFunctionsAmount property
    */
    private void setHashFunctionsAmount(int hashFunctionsAmount) {
        this.hashFunctionsAmount = hashFunctionsAmount;
    }

    /*
        Getter of structureSize property
    */
    private int getStructureSize(){
        return this.structureSize;
    }

    /*
        Getter of hashFunctionsAmount property
    */
    private int getHashFucntionsAmount(){
        return this.hashFunctionsAmount;
    }

    /*
        Input - number(Integer)
        Output - Whether the number is in the filet bloom(boolean)
        Algorithm - Foreach hush function we used to initialize the structure, the function hashes the number,
                    and checks if the right place in the bit array is 1.
                    If all the cells are one - the number is probably in the structure, othorwise it is definitely not there.
        Time complexity - O(n) while n is hash functions amount the program used to create the structure.
    */
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
