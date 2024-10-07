package za.ac.nwu.cm.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.service.NWUService;

/**
 * This is a quartz job that updates/creates lesson grades in eFundi
 *
 * @author BMcL
 * 
 */
@Slf4j
public class NWULessonGradesUpdateJob implements Job {

	private NWUService service;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("NWULessonGradesUpdateJob - execute start");
		getService().updateNWULessonGradesUpdateJob();
		log.info("NWULessonGradesUpdateJob - execute finished");
	}

	public NWUService getService() {
		return service;
	}

	public void setService(NWUService service) {
		this.service = service;
	}	
}
