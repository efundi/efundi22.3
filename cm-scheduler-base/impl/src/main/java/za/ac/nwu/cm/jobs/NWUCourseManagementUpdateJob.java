package za.ac.nwu.cm.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.service.NWUService;

/**
 * This is a quartz job that manages Course Management data for NWU
 *
 * @author JC Gillman
 * 
 */
@Slf4j
public class NWUCourseManagementUpdateJob implements Job {
	
	private NWUService service;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("NWUCourseManagementUpdateJob - execute start");
		
		try {
			getService().updateNWUCourseManagement();
		} catch (JobExecutionException e) {
			throw new JobExecutionException("NWUCourseManagementUpdateJob failed");
		}
		
		log.info("NWUCourseManagementUpdateJob - execute finished");
	}

	public NWUService getService() {
		return service;
	}

	public void setService(NWUService service) {
		this.service = service;
	}	
}