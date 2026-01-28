package com.MTube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String mname;
	@Column(length = 2000)
	String link;
	String genre;
	String cast;
	String director;
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(int id, String mname, String link, String genre, String cast, String director) {
		super();
		this.id = id;
		this.mname = mname;
		this.link = link;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", mname=" + mname + ", link=" + link + ", genre=" + genre + ", cast=" + cast
				+ ", director=" + director + "]";
	}

}
