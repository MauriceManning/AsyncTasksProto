package edu.berkeley.path.next.ModelExecutionManager;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;

// use a configuration object instead of

@Configuration
@EnableAsync
public class ModelExecutionManagerConfiguration {

    @Bean
    public ModelResult modelResult(){
        return new ModelResult();
    }

    @Bean
    public ModelExecutionManager modelExecutionManager(){
        return new ModelExecutionManager();
    }


}


