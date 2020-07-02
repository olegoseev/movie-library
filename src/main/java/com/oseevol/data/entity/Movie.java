package com.oseevol.data.entity;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message = "Movie title is required")
	private String title;
	@NotEmpty(message = "Movie description is required")
	private String description;
	@NotEmpty(message = "Movie trailer is required")
	private String trailer;
	@NotEmpty(message = "Movie poster is required")
	private String poster;
	@NotNull(message = "Movie release date is required")
	private LocalDate releaseDate;
	
	private boolean inTheater = false;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "movie_actor", joinColumns=@JoinColumn(name="movie_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="id"))
	private List<Actor> actors = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="movie_genre", joinColumns=@JoinColumn(name="movie_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="genre_id", referencedColumnName="id"))
	private List<Genre> genres = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "movie_id", nullable=false, insertable=false)
	private List<MovieCharacter> characters = new ArrayList<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public boolean isInTheater() {
		return inTheater;
	}
	public void setInTheater(boolean inTheater) {
		this.inTheater = inTheater;
	}
	
	@Transient
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	@Transient
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (inTheater ? 1231 : 1237);
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((trailer == null) ? 0 : trailer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (id != other.id)
			return false;
		if (inTheater != other.inTheater)
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (trailer == null) {
			if (other.trailer != null)
				return false;
		} else if (!trailer.equals(other.trailer))
			return false;
		return true;
	}
	
}
