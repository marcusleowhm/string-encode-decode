package service;

import constant.ReferenceTable;
import utility.NumberGenerator;

import java.util.List;

/**
 * @author Marcus LEOW
 */
public class StringService {

    private final NumberGenerator generator;

    public StringService(NumberGenerator generator) {
        this.generator = generator;
    }

    public String encode(String plainText) {
        //Determine the indices for the encoded text
        List<Integer> encodedIndices = generator.generateEncodedIndices(plainText);
        return buildStringWithOffset(generator.getRandomInteger(), encodedIndices);
    }

    public String decode(String encodedText) {
        List<Integer> decodedIndices = generator.generateDecodedIndices(encodedText);
        return buildStringWithoutOffset(decodedIndices);
    }

    /***
     * Build string for encoding
     * @param offset int representing index of the offset to append to the front of the result
     * @param indices an Array of int representing the indices of character after offset
     * @return String contain encoded text
     */
    public String buildStringWithOffset(int offset, List<Integer> indices) {
        return String.valueOf(ReferenceTable.table[offset]).concat(buildString(indices));
    }

    /***
     * Build string for decoding
     * @param indices
     * @return
     */
    public String buildStringWithoutOffset(List<Integer> indices) {
        return buildString(indices);
    }

    public String buildString(List<Integer> indices) {
        StringBuilder builder = new StringBuilder();
        for (Integer index : indices) {
            if (index == null) {
                builder.append(" ");
            } else {
                builder.append(ReferenceTable.table[index]);
            }
        }
        return builder.toString();
    }

}
