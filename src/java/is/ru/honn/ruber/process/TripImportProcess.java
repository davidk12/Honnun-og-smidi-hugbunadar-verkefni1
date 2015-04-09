package is.ru.honn.ruber.process;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.rest.ContentReader;

import is.ru.honn.ruber.rest.ReadException;
import is.ru.honn.ruber.rest.ReadHandler;
import is.ru.honn.ruber.service.RuberService;
import is.ruframework.process.RuAbstractProcess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

/**
 * @author Unnar/Davíð
 * @version 1.0.0
 * @since September 28, 2014
 * --
 * Process for importing Trips
 */
public class TripImportProcess extends RuAbstractProcess implements ReadHandler {

	Logger log = Logger.getLogger(this.getClass().getName());
	RuberService ruberService;

	/**
	 * The active content reader
	 */
	ContentReader reader;


	/**
	 * Processes given Trip content
	 * --
	 * @param newTrip New trip to add
	 */
		@Override
	public void processContent(Trip newTrip) {

		// Giving the newly added trip to the service
		ruberService.addTrip(newTrip);
	}


	/**
	 *
	 * @param strings
	 */
	/*
	@Override
	public void setParameters(String[] strings) {
	}
	*/

	/**
	 * Run at the start of the process
	 */
	@Override
	public void startProcess(){
		log.info("startprocess");

		try{
				reader.read(getProcessContext().getImportURL());
			}catch (ReadException ex){
				log.severe("TripImportProcess: startProcess" + ex.getMessage());
		}
	}

	/**
	 * Run before the process is started
	 */
	@Override
	public void beforeProcess()
	{

		ApplicationContext ctx = new FileSystemXmlApplicationContext("app.xml");
		ruberService = (RuberService)ctx.getBean("RuberService");

		// Fetching the reader through the app.xml file
		reader = (ContentReader)ctx.getBean("ContentReader");

		// Setting the read handler as the current process
		reader.setReadHandler(this);
	}

	@Override
	public void afterProcess()
	{
		log.info("afterprocess");
	}
}