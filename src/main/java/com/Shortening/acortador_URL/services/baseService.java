package com.Shortening.acortador_URL.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class baseService {

    private static final String base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = base62.toCharArray();
    private int base = allowedCharacters.length;

    public String encode(){
        UUID uuid = UUID.randomUUID();
        var encodedString = new StringBuilder();

        long input = uuid.getMostSignificantBits();
        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += base62.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
    
}
