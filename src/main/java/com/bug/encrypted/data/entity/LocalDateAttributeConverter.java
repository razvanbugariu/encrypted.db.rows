package com.bug.encrypted.data.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Configuration
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {
    private static final String AES = "AES";
    private final Key key;

    private final Cipher cipher;

    public LocalDateAttributeConverter(@Value("${encryption.secret.key}") String secretKey) throws Exception {
        key = new SecretKeySpec(secretKey.getBytes(), AES);
        cipher = Cipher.getInstance(AES);
    }

    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            String value = attribute.format(DateTimeFormatter.ofPattern("yyyy-MM-d"));
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            String value = new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
            return LocalDate.parse(value);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
