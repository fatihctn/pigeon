package com.fatihctn.pigeon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.function.Function;

public class BCryptUtil {
    private static final Logger logger = LoggerFactory.getLogger(BCryptUtil.class);

    private final int logRounds;

    public BCryptUtil(int logRounds) {
        this.logRounds = logRounds;
        logger.debug("BCryptUtil defined.");
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(this.logRounds));
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunction) {
        if (!verifyHash(password, hash)) {
            return false;
        }

        int rounds = this.getRounds(hash);
        if (rounds != logRounds) {
            logger.debug("Updating password from {} rounds to {}", rounds, logRounds);
            String newHash = hash(password);
            return updateFunction.apply(newHash);
        }
        return true;
    }

    private int getRounds(String salt) {
        char minor = (char) 0;
        int offset = 0;

        if (salt.charAt(0) != '$' ||  salt.charAt(1) != '2') {
            throw new IllegalArgumentException("Invalid salt version");
        }

        if (salt.charAt(0) == '$') {
            offset = 3;
        } else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$') {
                throw new IllegalArgumentException("Invalid salt revision");
            }
            offset = 4;
        }

        if (salt.charAt(offset + 2) > '$') {
            throw new IllegalArgumentException("Missing salt rounds");
        }
        return Integer.parseInt(salt.substring(offset, offset + 2));
    }

}
