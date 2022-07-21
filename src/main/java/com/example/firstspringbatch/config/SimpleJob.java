package com.example.firstspringbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.firstspringbatch.service.FirstTasklet;
import com.example.firstspringbatch.service.SecondTasklet;

@Component
public class SimpleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstTasklet firstTasklet;
	
	@Bean 
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
				.start(firstStep())
				.next(secondStep())
				.build();
		
	}
	

	public Step firstStep() {
		return stepBuilderFactory
				.get("First Step")
				.tasklet(firstTasklet)
				.build();
	}
	

	

	private Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTasklet)
				.build();
	}
	
	
	

	
	

	
	
}
