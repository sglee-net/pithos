package org.chronotics.db.mybatis;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.apache.ibatis.executor.BatchExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

/**
 * DbPersistenceExceptionTranslator
 * @author sglee
 * @since 2018
 * @description
 */
public class DbPersistenceExceptionTranslator implements PersistenceExceptionTranslator {

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Throwable e) {
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(ostream);
		e.printStackTrace(writer);
		writer.flush();
		return ostream.toString();
	}
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public DataAccessException translateExceptionIfPossible(RuntimeException _e) {
		DbDataAccessException exception = new DbDataAccessException(_e.getMessage(), _e.getCause());
		log.error("DBException in the class: ", _e.getCause().getClass());
		
		if(_e.getCause() instanceof SQLException) {
			log.error(">>> SQLException begin [{}]", _e.getMessage());
			log.error(getStackTrace(_e));
			log.error(">>> SQLException end");

			SQLException e = (SQLException) _e.getCause();
			log.error("error code: ", e.getErrorCode());
			log.error("error message: ", e.getMessage());
		} else if (_e.getCause() instanceof org.apache.ibatis.executor.BatchExecutorException) {
			BatchExecutorException e = (BatchExecutorException) _e.getCause();
			log.error("error code: {}", e.getBatchUpdateException().getErrorCode());
			log.error("error message: ", e.getMessage());
		} else {
			log.error(getStackTrace(_e));
		}
		return exception;
	}
}
