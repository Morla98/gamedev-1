package com.unihannover.gamedev.security;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unihannover.gamedev.models.User;

/**
 *
 *
 * @author Dominik Andrae
 */
public class UserPrincipal implements UserDetails {

	private Long id;

	private String username;

	@JsonIgnore
	private String email;


	/**
	 * Constructor.
	 *
	 * @param username The user name
	 * @param email The user emain (primary key)
	 */
	public UserPrincipal(String username, String email) {
		this.username = username;
		this.email = email;
	}

	/**
	 * Creates a UserPrincipal, given a User.
	 *
	 * @param user The Given User
	 * @return The principal of the User
	 */
	public static UserPrincipal create(User user) {
		return new UserPrincipal(user.getUserName(), user.getEmail());
	}

	/**
	 * Returns the name of a User.
	 * Currently returns null every time(?)
	 *
	 * @return
	 */
	@Override
	public String getUsername() {
		return null;
	}

	/**
	 * Checks, if this UserPrincipal is the same object or contains the same information than the given object.
	 *
	 * @param o The object to compare
	 * @return If this object and the given object are equal
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * Returns the hash code of the UserPrincipals id.
	 *
	 * @return The hash
	 */
	@Override
    public int hashCode() {

        return Objects.hash(id);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}