package com.glacier.authorization.server.oauth2;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-24 16:38
 */
@FrameworkEndpoint
public class JwkSetEndpoint {
    private static final Logger log = LoggerFactory.getLogger(JwkSetEndpoint.class);
    private final KeyPair keyPair;

    @Autowired
    public JwkSetEndpoint(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @GetMapping("/well-known/jwks")
    @ResponseBody
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) this.keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
