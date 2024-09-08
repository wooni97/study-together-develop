package dev.flab.studytogether.domain.member.repository;

import dev.flab.studytogether.domain.member.event.MemberV2SignUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberV2SignUpEventRepositoryImpl implements MemberV2SignUpEventRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(MemberV2SignUpEvent event) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("MEMBER_SIGN_UP_EVENT").usingGeneratedKeyColumns("SEQ_ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EMAIL", event.getEmail());
        parameters.put("AUTH_KEY", event.getAuthKey());
        parameters.put("PROCESSED_FLAG", false);

        jdbcInsert.execute(parameters);
    }

    @Override
    public List<MemberV2SignUpEvent> findUnprocessedEvents() {
        String query = "SELECT SEQ_ID, EMAIL, AUTH_KEY FROM MEMBER_SIGN_UP_EVENT WHERE PROCESSED_FLAG = FALSE";
        return jdbcTemplate.query(query, memberV2SignUpEventRowMapper());
    }

    @Override
    public void updateAllToProcessed(List<Long> succeedEventIDs) {
        if(succeedEventIDs.isEmpty()) return;

        String query = "UPDATE MEMBER_SIGN_UP_EVENT SET PROCESSED_FLAG = TRUE WHERE ID IN (:ids)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ids", succeedEventIDs);

        namedParameterJdbcTemplate.update(query, parameters);
    }

    private RowMapper<MemberV2SignUpEvent> memberV2SignUpEventRowMapper() {
        return (rs, rowNum) -> new MemberV2SignUpEvent(
                rs.getLong("SEQ_ID"),
                rs.getString("EMAIL"),
                rs.getString("AUTH_KEY"));
    }
}
