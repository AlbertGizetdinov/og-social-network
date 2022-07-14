package ru.itis.og.repository;

public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}

