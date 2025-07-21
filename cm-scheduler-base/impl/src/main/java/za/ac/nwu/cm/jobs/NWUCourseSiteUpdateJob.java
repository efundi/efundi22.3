package za.ac.nwu.cm.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.sakaiproject.api.app.scheduler.events.TriggerEvent;
import org.sakaiproject.api.app.scheduler.events.TriggerEventManager;

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

	private TriggerEventManager triggerEventManager;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("NWUCourseSiteUpdateJob - execute start");
		
		JobKey jobKey = context.getJobDetail().getKey();		
		
		List<String> jobs = new ArrayList<>();
		jobs.add(jobKey.getName());
		
		TriggerEvent.TRIGGER_EVENT_TYPE evtTypes[] = new TriggerEvent.TRIGGER_EVENT_TYPE [] {TriggerEvent.TRIGGER_EVENT_TYPE.COMPLETE};
		List<TriggerEvent> events = triggerEventManager.getTriggerEvents(null, null, jobs, null, evtTypes, 1, 1);

		Date previousFireTime = null;
		for (Iterator iterator = events.iterator(); iterator.hasNext();) {
			TriggerEvent triggerEvent = (TriggerEvent) iterator.next();
			previousFireTime = triggerEvent.getTime();
			break;
		}

		try {
			getService().updateNWUeFundiCourseSites(previousFireTime == null ? null : previousFireTime);
		} catch (JobExecutionException e) {
			throw new JobExecutionException("NWUCourseManagementUpdateJob failed");
		}
		
		
		log.info("NWUCourseSiteUpdateJob - execute finished");
	}

	public NWUService getService() {
		return service;
	}

	public void setService(NWUService service) {
		this.service = service;
	}

	public TriggerEventManager getTriggerEventManager() {
		return triggerEventManager;
	}

	public void setTriggerEventManager(TriggerEventManager triggerEventManager) {
		this.triggerEventManager = triggerEventManager;
	}
}
