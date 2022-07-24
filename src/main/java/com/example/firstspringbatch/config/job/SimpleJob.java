package com.example.firstspringbatch.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.firstspringbatch.listener.FirstJobListener;
import com.example.firstspringbatch.listener.FirstStepListener;
import com.example.firstspringbatch.processor.FirstItemProcessor;
import com.example.firstspringbatch.reader.FirstItemReader;
import com.example.firstspringbatch.service.FirstTasklet;
import com.example.firstspringbatch.service.SecondTasklet;
import com.example.firstspringbatch.writer.FirstItemWriter;


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
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private FirstStepListener firstStepListener;
	
	@Autowired
	private FirstItemReader firstItemReader;
	
	@Autowired
	private FirstItemProcessor firstItemProcessor;
	
	@Autowired
	private FirstItemWriter firstItemWriter;
	
//	@Bean 
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
				.incrementer(new RunIdIncrementer())
				.start(firstStep())
				.next(secondStep())
				.listener(firstJobListener)
				.build();
		
	}
	

	public Step firstStep() {
		return stepBuilderFactory
				.get("First Step")
				.tasklet(firstTasklet)
				.listener(firstStepListener)
				.build();
	}
	

	

	private Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTasklet)
				.build();
	}
	
	
	

	@Bean
	public Job secondJob() {
		return jobBuilderFactory.get("Second Job")
				.incrementer(new RunIdIncrementer())
				.start(firstChuckStep())
				.build();
	}
	
	private Step firstChuckStep() {
		return stepBuilderFactory.get("First Chuck Step")
				.<Integer,Long>chunk(3)
				.reader(firstItemReader)
				.processor(firstItemProcessor)
				.writer(firstItemWriter)
				.build();
				
	}
	

	
	
}
