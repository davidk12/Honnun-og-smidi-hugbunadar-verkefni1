package is.ru.honn.ruber.rest;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 *
 */
public interface Reader {


	public void setReadHandler(ReadHandler handler);

	public void read(String importURL) throws ReadException;
}
