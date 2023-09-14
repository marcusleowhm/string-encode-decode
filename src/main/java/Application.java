import service.StringService;
import utility.NumberGenerator;

/**
 * @author Marcus LEOW
 */
public class Application {

    public static void main(String[] args) {

        NumberGenerator generator = new NumberGenerator();
        StringService service = new StringService(generator);

        String encodedText = service.encode("HELLO WORLD");
        System.out.printf("Offset character: %s\n", encodedText.charAt(0));
        System.out.printf("Encoded text: %s\n", encodedText);

        String decodedString = service.decode(encodedText);
        System.out.printf("Decoded text: %s\n", decodedString);
    }
}
