package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

@Component("usersDao")
public class UsersDao {
	private NamedParameterJdbcTemplate template;

	public UsersDao() {
		System.out.println("usersDao instantiated");
	}

	@Autowired
	private void setDataSourceTag(BasicDataSource dataSource) {
		this.template = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<User> getUsers() {
		return template.query("select * from users", new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return userFromResultSet(rs, rowNum);
			}
		});
	}

	public User getUser(String username) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("username", username);
		try {
			return template.queryForObject("select * from users where username = :username", parameterSource,
					new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return userFromResultSet(rs, rowNum);
				}
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public User userFromResultSet(ResultSet rs, int rowNum) throws SQLException{
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		return user;
	}

	public boolean delete(String username) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("username", username);
		return template.update("delete from users where username = :username", parameterSource) == 1;
	}

	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(username, email, password, enabled) values (:username, :email, :password, :enabled)";
		template.update(sql, parameterSource);
		return template.update("insert into authorities(username, authority) values (:username, :authority)",
				parameterSource) == 1;
	}

	public int[] create(List<User> users) {
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(users);
		String sql = "insert into users(username, email, password) values :username, :email, :password)";
		return template.batchUpdate(sql, parameterSource);
	}

	public boolean update(User user) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		String sql = "update users set username = :username, email = :email, password = :password where username = :username";
		return template.update(sql, parameterSource) == 1;
	}

}
