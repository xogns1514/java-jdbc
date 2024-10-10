package com.techcourse.dao;

import com.interface21.jdbc.core.JdbcTemplate;
import com.techcourse.domain.UserHistory;
import java.sql.Connection;
import javax.sql.DataSource;

public class UserHistoryDao {

    private final JdbcTemplate jdbcTemplate;

    public UserHistoryDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserHistoryDao(final DataSource dataSource) {
        this(new JdbcTemplate(dataSource));
    }

    public void log(final Connection connection, final UserHistory userHistory) {
        final var sql = """
                        insert into user_history (user_id, account, password, email, created_at, created_by) 
                        values (?, ?, ?, ?, ?, ?)
                        """;
        jdbcTemplate.update(
                connection,
                sql,
                userHistory.getUserId(), userHistory.getAccount(),
                userHistory.getPassword(), userHistory.getEmail(),
                userHistory.getCreatedAt(), userHistory.getCreateBy()
        );
    }
}
