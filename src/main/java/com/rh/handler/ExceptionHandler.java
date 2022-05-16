package com.rh.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rh.config.ProcessHandler;


@Component("ExceptionHandler")
public class ExceptionHandler  implements WorkItemHandler {
	
	@Autowired 
	ProcessHandler handler;
	
	public ExceptionHandler() {
	}
	
    private ProcessService processService;
	private RuntimeDataService runtimeDataService;

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {

    	processService = handler.getProcessService();
    	
        // System.out.println("Received parameters inside handler [ " + workItem.getParameters() + "]");
        ProcessInstance pi =  processService.getProcessInstance(workItem.getProcessInstanceId());
        Long parentId = pi.getParentProcessInstanceId();
        Integer retryCount = (Integer) processService.getProcessInstanceVariable(parentId, "retryCount");
        String timeISO = "PT" + (retryCount * 5) +"S";
        Integer newcount = retryCount + 1;
        System.out.println("Parent Process Id [" + parentId + "], Current Process Id [" + workItem.getProcessInstanceId() + "],"
        		+ " current retryCount : [" + retryCount + "], timeISO [" + timeISO + "]");
        
        processService.setProcessVariable(parentId, "retryCount", newcount);
       
        HashMap<String, Object> parms = new HashMap<>();
        
        parms.put("Result", retryCount);
        parms.put("timeISO", timeISO);
        
        
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        String currentDate = sdfDate.format(new Date());
        System.out.println("Current time >>>>>" + currentDate);
        workItemManager.completeWorkItem(workItem.getId(), parms);
    }


	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		
	}

}