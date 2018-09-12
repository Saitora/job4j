package ru.job4j.junior.multithreading.pool;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Random;

public class EmailNotificationTest {

    private static final String EMAIL_TMPLT = "{0}@{1}"; // 0 - id | 1 - domain
    private enum Domains {
        mail("mail.ru"),
        gmail("gmail.ru");

        private final String domain;

        Domains(String s) {
            domain = s;
        }

        public String getDomain() {
            return domain;
        }
    }

    private String generateRandomString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private String generateRandomEmail() {
        return MessageFormat.format(EMAIL_TMPLT, generateRandomString(10), Domains.values()[(int) (Math.random() * Domains.values().length)].getDomain());
    }

    @Test
    public void whenAddEmailShouldSendItWithoutErrors() {
        EmailNotification emn = new EmailNotification();
        EmailNotification.User user = new EmailNotification.User(generateRandomString(10), generateRandomEmail());
        emn.emailTo(user);
        emn.close();
    }

}