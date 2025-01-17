package io.openindoormap.security;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptTest {

    /**
     * 암복호화 테스트
     */
    @Test
    public void 암복호화() {
        log.debug("url : " + Crypt.encrypt("jdbc:postgresql://localhost:15432/oim"));
        log.debug("url : " + Crypt.encrypt("jdbc:log4jdbc:postgresql://localhost:15432/oim"));
        log.debug("user : " + Crypt.encrypt("postgres"));
        log.debug("password : " + Crypt.encrypt("postgres"));
        log.debug("oim : " + Crypt.encrypt("oim"));

        log.debug(Crypt.decrypt("aXGA1cnLJzD59jgNtireIOiXHdZSWDuJpGcQWQsA9IWhrbm2T8q8v77hukB41JCvcIib4Xj9b2pn/rLBQ+tBXQ=="));
        log.debug(Crypt.decrypt("xHdbcNbEd+5jInX1S1Ju/g=="));
        log.debug(Crypt.decrypt("jrpiqDjDsXoAZPAWyJ9sxQ=="));
    }
}
