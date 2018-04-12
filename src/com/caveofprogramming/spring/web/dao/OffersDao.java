package com.caveofprogramming.spring.web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component("offersDao")
public class OffersDao {
    private DataSource dataSource;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OffersDao() {
        System.out.println("OffersDao instantiated");
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Offer> getOffers() {
        return namedParameterJdbcTemplate.query("select * from offers inner join users on offers.username = users" +
				".username", new OfferRowMapper());
    }

    public Offer getOffer(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject("select * from offers o inner join users u on o.username = u" +
				".username where id = :id and u.enabled = true", parameterSource, new OfferRowMapper());
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update("delete from offers where id = :id", params) == 1;
    }


    public boolean create(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
        String sql = "insert into offers (username, text) values (:username, :text)";
        return namedParameterJdbcTemplate.update(sql, params) == 1;
    }

    //batch create
    public int[] create(List<Offer> offers) {
        SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(offers);
        String sql = "insert into offers (username, text) values (:username, :text)";
        return namedParameterJdbcTemplate.batchUpdate(sql, parameterSource);
    }

    public boolean update(Offer offer) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(offer);
        String sql = "update offers set text = :text, username = :username where id = :id";
        return namedParameterJdbcTemplate.update(sql, parameterSource) == 1;
    }

    public List<Offer> getOffers(String username) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("username", username);
        String sql = "select * from offers o inner join users u on o.username = u.username where o.username = :username";
        return namedParameterJdbcTemplate.query(sql, parameterSource, new OfferRowMapper());
    }

    public Offer getOffer(String username) {
        if (username == null) {
            return null;
        }
        List<Offer> offers = getOffers(username);
        if(offers.size() == 0) {
            return null;
        }
        return offers.get(0);
    }
}
