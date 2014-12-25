package com.indra.services.thread.demo;

import com.indra.inplan.dataServices.AtvDataService;
import com.indra.inplan.model.AtvVO;
import com.indra.inplan.springctx.ApplicationContextProvider;

import java.util.UUID;

/**
 * Created by scaamanho on 23/12/14.
 */
public class InserterThread implements Runnable {
    int id;

    public InserterThread()
    {this(0);}

    public InserterThread(int id)
    {this.id=id;}

    @Override
    public void run() {
        try
        {
            ApplicationContextProvider appContext = new ApplicationContextProvider();
            //TODO PersonDAO personDAO = appContext.getApplicationContext().getBean("personDAO", PersonDAO.class);
            AtvDataService atvDataService=appContext.getApplicationContext().getBean("atvService",AtvDataService.class);
            AtvVO atv=new AtvVO();
            atv.setId(UUID.randomUUID().toString());
            atv.setArrAirport("LEPA");
            atvDataService.insert(atv);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Finished:"+id);
    }
}
