package ru.job4j.junior.multithreading.pool;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    public static final String SUBJECT_TMPLT = "Notification {0} to email {1}."; // 0 - username 1 - email
    public static final String BODY_TMPLT = "Add a new event to {0}"; // 0 - username

    private final ExecutorService pool;

    public EmailNotification() {
        pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        pool.submit(new Worker(user));
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Worker implements Runnable {

        private final User user;

        public Worker(User user) {
            this.user = user;
        }

        private void send(String subject, String body, String email) {
            System.out.println(email.concat("\n\n").concat(subject).concat("\n\n").concat(body));
        }

        @Override
        public void run() {
            String subject = MessageFormat.format(SUBJECT_TMPLT, user.getUsername(), user.getEmail());
            String body = MessageFormat.format(BODY_TMPLT, user.getUsername());
            send(subject, body, user.getEmail());
            String abc = null;
            abc.concat("");
        }
    }

    static public class User {

        private String username = null;
        private String email = null;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" + "username='" + username + '\''
                    + ", email='" + email + '\'' + '}';
        }
    }

}
