package utility;

import constant.ReferenceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Marcus LEOW
 */
public class NumberGenerator {

    //Will only be generated when first encoding. During decoding, there is no random offset.
    //Rather, the offset is determined by the first character in the encoded text
    private final int randomInteger;
    public int getRandomInteger() {
        return this.randomInteger;
    }

    public NumberGenerator() {
        this.randomInteger = randomBetweenZeroAnd(ReferenceTable.table.length);
    }

    private int randomBetweenZeroAnd(int max) {
        Random rand = new Random();

        //Generate random number between 0 and length - 1 of array passed in as max
        //Random number represents index in array
        return rand.nextInt(max);
    }

    public List<Integer> generateEncodedIndices(String plainText) {
        List<Integer> encodedIndices = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i++) {
            for (int j = 0; j < ReferenceTable.table.length; j++) {
                //Handling empty space by adding null character
                if (plainText.charAt(i) == ' ') {
                    encodedIndices.add(null);
                    break;
                }
                //Normal character in the reference table
                else if (ReferenceTable.table[j] == Character.toUpperCase(plainText.charAt(i))) {
                    int newIndex = j - randomInteger;
                    //If offsetIndex caused newIndex to be negative, circle round to the back of the table
                    if (newIndex < 0) {
                        newIndex += ReferenceTable.table.length;
                    }
                    encodedIndices.add(newIndex);
                    break;
                }
            }
        }
        return encodedIndices;
    }

    public List<Integer> generateDecodedIndices(String encodedText) {
        char offsetCharacter = encodedText.charAt(0);
        int offsetIndex = 0;
        for (int i = 0; i < ReferenceTable.table.length; i++) {
            if (ReferenceTable.table[i] == Character.toUpperCase(offsetCharacter)) {
                offsetIndex = i;
                break;
            }
        }

        String encodedTextContent = encodedText.substring(1);
        List<Integer> decodedIndices = new ArrayList<>();
        for (int i = 0; i < encodedTextContent.length(); i++) {
            for (int j = 0; j < ReferenceTable.table.length; j++) {
                if (encodedTextContent.charAt(i) == ' ') {
                    decodedIndices.add(null);
                    break;
                }
                else if (ReferenceTable.table[j] == Character.toUpperCase(encodedTextContent.charAt(i))) {
                    int newIndex = j + offsetIndex;
                    //if the new offset index is over the table's length, circle round to the front of the table
                    if (newIndex > ReferenceTable.table.length) {
                        newIndex -= ReferenceTable.table.length;
                    }
                    decodedIndices.add(newIndex);
                    break;
                }
            }
        }
        return decodedIndices;
    }
}
