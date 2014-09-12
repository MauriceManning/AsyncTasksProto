package edu.berkeley.path.next.ModelExecutionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.*;
import java.util.concurrent.Future;

public class modelRunnerApp {


    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(ModelExecutionManagerConfiguration.class);
        ctx.refresh();

        final Logger logger = LogManager.getLogger(modelRunnerApp.class.getName());

        ModelExecutionManager execMgr = ctx.getBean(ModelExecutionManager.class);

        logger.info("modelRunnerApp initialized ");

        //launch three model runs asynchronously
        Future<ModelResult> result1 = execMgr.runModel("ModelRun1");
        Thread.sleep(2000);
        Future<ModelResult> result2 = execMgr.runModel("ModelRun2");
        Thread.sleep(2000);
        Future<ModelResult> result3 = execMgr.runModel("ModelRun3");

        // Wait until they are all done
        while (!(result1.isDone() && result2.isDone() && result3.isDone())) {
            logger.info("modelRunnerApp waiting ");
            Thread.sleep(100); //millisecond pause between each check
        }

        // demonstrate that all three completed and the location of the model results was provided
        logger.info("modelRunnerApp result1: " + result1.get().getReference());
        logger.info("modelRunnerApp result2: " + result2.get().getReference());
        logger.info("modelRunnerApp result3: " + result3.get().getReference());

    }

}
