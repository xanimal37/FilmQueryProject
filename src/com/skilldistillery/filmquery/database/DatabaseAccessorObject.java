package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Central";
	private static final String user = "student";
	private static final String pw = "student";

	// constructor, throws exception
	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// Create the object
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, "
				+ "f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, language.name "
				+ "FROM film f JOIN language ON f.language_id = language.id "
				+ "WHERE f.id=?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// Create the object
				film = new Film();
				// set variables from result
				film.setId(rs.getInt("f.id"));
				film.setTitle(rs.getString("f.title"));
				film.setDescription(rs.getString("f.description"));
				film.setReleaseYear(rs.getInt("f.release_year"));
				film.setLanguageId(rs.getInt("f.language_id"));
				film.setRentalDuration(rs.getInt("f.rental_duration"));
				film.setRentalRate(rs.getDouble("f.rental_rate"));
				film.setLength(rs.getInt("f.length"));
				film.setReplacementCost(rs.getDouble("f.replacement_cost"));
				film.setRating(rs.getString("f.rating"));
				film.setFeatures(rs.getString("f.special_features"));
				film.setLanguage(rs.getString("language.name"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// if a film is found add a list of the actors
		if (film != null) {
			List<Actor> actors = findActorsByFilmId(filmId);
			film.setActors(actors);
		}
		return film;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {

		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film_actor.film_id = film.id " + "WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Create the object
				Actor actor = new Actor();
				// set variables from result
				actor.setId(rs.getInt("actor.id"));
				actor.setFirstName(rs.getString("actor.first_name"));
				actor.setLastName(rs.getString("actor.last_name"));

				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		// list of film objects
		List<Film> films = new ArrayList<>();
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, "
				+ "f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, language.name "
				+ "FROM film f JOIN language ON language.id = f.language_id "
				+ "WHERE title LIKE ? OR description LIKE ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pw);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Create the object
				Film film = new Film();
				// set variables from result
				film.setId(rs.getInt("f.id"));
				film.setTitle(rs.getString("f.title"));
				film.setDescription(rs.getString("f.description"));
				film.setReleaseYear(rs.getInt("f.release_year"));
				film.setLanguageId(rs.getInt("f.language_id"));
				film.setRentalDuration(rs.getInt("f.rental_duration"));
				film.setRentalRate(rs.getDouble("f.rental_rate"));
				film.setLength(rs.getInt("f.length"));
				film.setReplacementCost(rs.getDouble("f.replacement_cost"));
				film.setRating(rs.getString("f.rating"));
				film.setFeatures(rs.getString("f.special_features"));
				film.setLanguage(rs.getString("language.name"));
				// add it to the list
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

}
