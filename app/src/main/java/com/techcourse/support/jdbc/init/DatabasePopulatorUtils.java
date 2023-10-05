package com.techcourse.support.jdbc.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class DatabasePopulatorUtils {

    private static final Logger log = LoggerFactory.getLogger(DatabasePopulatorUtils.class);

    public static void execute(DataSource dataSource) {
        try {
            URL url = DatabasePopulatorUtils.class.getClassLoader().getResource("schema.sql");
            File file = new File(url.getFile());
            String sql = Files.readString(file.toPath());

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute(sql);
        } catch (IOException | NullPointerException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    private DatabasePopulatorUtils() {}
}
