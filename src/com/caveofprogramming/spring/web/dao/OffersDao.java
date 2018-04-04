package com.caveofprogramming.spring.web.dao;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("offersDao")
public class OffersDao {
	private BasicDataSource dataSource;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public OffersDao() {
		System.out.println("OffersDao instantiated");
	}
	
	@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Offer> getOffers() {
		return namedParameterJdbcTemplate.query("select * from offers", new RowMapper<Offer>() {
			public Offer mapRow(ResultSet rs, int i) throws SQLException {
				return offerFromResultSet(rs, i);
			}
		});
	}

	public Offer offerFromResultSet(ResultSet rs, int rowNum) throws SQLException{
		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setName(rs.getString("name"));
		offer.setEmail(rs.getString("email"));
		offer.setText(rs.getString("text"));
		return offer;
	}

	public Offer getOffer(int id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		
		return namedParameterJdbcTemplate.queryForObject("select * from offers where id = :id", parameterSource, new RowMapper<Offer>() {
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return offerFromResultSet(rs, rowNum);

			}
		});
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return namedParameterJdbcTemplate.update("delete from offers where id = :id", params) == 1;
	}
	
	
	public boolean create(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		String sql = "insert into offers (name, email, text) values (:name, :email, :text)";
		return namedParameterJdbcTemplate.update(sql, params) == 1;
	}
	
	//batch create
	public int[] create(List<Offer> offers) {
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(offers);
		String sql = "insert into offers (id, name, email, text) values (:id, :name, :email, :text)";
		return namedParameterJdbcTemplate.batchUpdate(sql, parameterSource);
	}
	
	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(offer);
		String sql = "update offers set name = :name, email = :email, text = :text where id = :id";
		return namedParameterJdbcTemplate.update(sql, parameterSource) == 1;
	}
	
}
