package org.chronotics.db.mybatis;

import org.springframework.dao.DataAccessException;

public class DbDataAccessException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DbDataAccessException(String msg) {
		super(msg);
	}
	
	public DbDataAccessException(String msg, Throwable e) {
		super(msg, e);
	}

	
}
