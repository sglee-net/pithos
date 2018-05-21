package org.chronotics.db.mybatis;

/**
 * DBException 
 * @author sglee
 * @since 2018
 * @description
 * for PersistenceExceptionTranslator
 */
public class DbRuntimeException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	DbRuntimeException() {
		super();
	}
	
	DbRuntimeException(String msg) {
		super(msg);
	}
	
	DbRuntimeException(Throwable e) {
		super(e);
	}
	
	/**
	 * 
	 * @param msg
	 * @param cause
	 */
	DbRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
