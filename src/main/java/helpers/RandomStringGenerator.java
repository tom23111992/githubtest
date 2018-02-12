package helpers;

import static org.bitbucket.dollar.Dollar.$;

public class RandomStringGenerator {

    public static final String VALID_CHARACTERS = $('a', 'z').join();

    private RandomStringGenerator() {
    }

    public static String randomString(int length) {
        return $(VALID_CHARACTERS).shuffle().slice(length).toString();
    }
}

