package com.engineai.fice.infrastructure.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    @jakarta.annotation.PostConstruct
    public void dbg() {
        // Esto te servirá para confirmar en los Logs de Cloud Run que se está leyendo la variable
        System.out.println("FICE secret cargado correctamente");
    }

    public SecretKey key() {
        try {
            // Esto garantiza una llave de 32 bytes (256 bits) sin importar el largo del texto.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(secret.getBytes(StandardCharsets.UTF_8));
            return Keys.hmacShaKeyFor(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al configurar la llave JWT: " + e.getMessage());
        }
    }
}


