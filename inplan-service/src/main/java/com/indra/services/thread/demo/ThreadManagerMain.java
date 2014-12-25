package com.indra.services.thread.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by scaamanho on 23/12/14.
 */
public class ThreadManagerMain
{
    public static void main(String args[])
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ThreadFactory factory= Executors.defaultThreadFactory();
        Thread thread;
        for(int i=0;i<50000;i++)
        {
            thread=factory.newThread(new InserterThread(i));

            try {
                thread.start();
                if(i%500==0)
                    Thread.sleep(100l);
            }
            catch (Exception e)
            {

            }
        }
        System.out.println("All threads started");
    }
}
