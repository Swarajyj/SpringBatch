package com.example.firstspringbatch.listener;

import javax.batch.runtime.StepExecution;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;



@Component
public class FirstStepListener  implements StepExecutionListener{
	


	@Override
	public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
		System.out.println("Before step :"+ stepExecution.getStepName());
		System.out.println("Job exceution context"+ stepExecution.getJobExecution().getExecutionContext());
		System.out.println("Step exceution context"+stepExecution.getExecutionContext());
		
		stepExecution.getExecutionContext().put("sec","sec value");
		
	}

	@Override
	public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
		System.out.println("After step :"+ stepExecution.getStepName());
		System.out.println("Job exceution context"+ stepExecution.getJobExecution().getExecutionContext());
		System.out.println("step exceution context"+stepExecution.getExecutionContext());
		
		return null;
	}


}
