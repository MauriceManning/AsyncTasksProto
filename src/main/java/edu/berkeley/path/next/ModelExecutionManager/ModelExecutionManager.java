package edu.berkeley.path.next.ModelExecutionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.Future;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.AsyncResult;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ModelExecutionManager {


    @Async
    public Future<ModelResult>  runModel(String param)   {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ModelExecutionManagerConfiguration.class);
        ctx.refresh();

        final Logger logger = LogManager.getLogger(ModelExecutionManager.class.getName());

        logger.info("ModelExecutionManager: run the model ");

        ModelResult result = ctx.getBean(ModelResult.class);
        try {
            // the model is being run here, pause for a bit
            Thread.sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.setName(param);
        result.setReference("results in folder: " + param);

        //return the result in  the Future object
        return new AsyncResult<ModelResult>(result);

    }

}
