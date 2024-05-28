package ma.org.licence.pfe.shared;

import java.security.SecureRandom;
import java.time.Instant;

public class UniqueIdGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static String generateCustomId() {
        long timestamp = Instant.now().toEpochMilli();
        int randomInt = random.nextInt(1000);
        return String.format("%d-%03d", timestamp, randomInt);
    }
}
