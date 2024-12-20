package za.ac.nwu.cm.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.service.NWUService;

/**
 * This is a quartz job that updates/creates Course sites in eFundi
 *
 * @author JC Gillman
 * 
 */
@Slf4j
public class NWUCourseSiteUpdateJob implements Job {

	private NWUService service;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("NWUCourseSiteUpdateJob - execute start");
		getService().updateNWUeFundiCourseSites();
		log.info("NWUCourseSiteUpdateJob - execute finished");
	}

	public NWUService getService() {
		return service;
	}

	public void setService(NWUService service) {
		this.service = service;
	}	
}
