package is.ru.honn.ruber.rest;

import java.util.logging.Logger;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 * Abstract content reading data
 */
public abstract class ContentReader implements Reader{

	/**
	 * Error logger
	 */
	Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * The handler tied to the reader
	 */
	protected ReadHandler handler;

	/**
	 * Sets the read Handler
	 * --
	 * @param handler
	 */
	@Override
	public void setReadHandler(ReadHandler handler){
		this.handler = handler;
	}

	/**
	 * Abstract read function
	 * --
	 * @param importURL
	 * @throws ReadException
	 */
	@Override
	public abstract void read(String importURL) throws ReadException;
}
