package com.rh.config;

import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.springframework.stereotype.Component;

@Component
public class ProcessHandler {

	private ProcessService processService;
	private RuntimeDataService runtimeDataService;

	public ProcessHandler(ProcessService processService, RuntimeDataService runtimeDataService) {
		this.processService = processService;
		this.runtimeDataService = runtimeDataService;
	}

	public ProcessService getProcessService() {
		return processService;
	}

	public RuntimeDataService getRuntimeDataService() {
		return runtimeDataService;
	}
}