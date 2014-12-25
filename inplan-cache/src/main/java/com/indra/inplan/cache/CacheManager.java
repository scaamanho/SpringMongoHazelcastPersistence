package com.indra.inplan.cache;

import java.util.HashMap;

import com.hazelcast.core.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;


@SuppressWarnings("unchecked")
public class CacheManager implements ClientListener, DistributedObjectListener, EntryListener<Object, Object>
{
  private static Logger log = Logger.getLogger(CacheManager.class);


  // private static CacheManager instance=null;
  private HazelcastInstance hazelcast = null;
  private boolean logEntrys = false;

  private HashMap<String, String> listenerKeys;

  private static final String cacheNames[]={"inplan.cache.atv"};


  public CacheManager(boolean enableMongo,boolean logClients, boolean logInstances, boolean logEntrys)
  {
    Config cfg;
    try
    {
      log.info("Initializing Cache...");

      if(enableMongo)
      {
        //Inicializacion con Spring
        //ApplicationContext ctx = new GenericXmlApplicationContext("classpath*:**/applicationContext*.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        hazelcast = (HazelcastInstance)ctx.getBean("instance");
      }
      else
      {
        //Inicializacion clasica
        cfg = new XmlConfigBuilder(getClass().getResourceAsStream("/hazelcast.xml")).build();
        hazelcast = Hazelcast.newHazelcastInstance(cfg);
      }

      if (logClients)
        hazelcast.getClientService().addClientListener(this);
      if (logInstances)
      {
        hazelcast.addDistributedObjectListener(this);
        this.logEntrys = logEntrys;
      }
      log.info("Cache initialized");
      listenerKeys = new HashMap<String, String>();

      //TODO Inicializamos caches
      long t1,t2;
      long tt1=System.currentTimeMillis();
      for(String cacheName:cacheNames)
      {
        try
        {
          t1=System.currentTimeMillis();
          hazelcast.getMap(cacheName).values();
          t2=System.currentTimeMillis();
          log.debug("initializing:"+cacheName+" in "+((t2-t1)/1000l)+"s.");
        }catch(Exception e)
        {log.error(e,e);}
      }
      long tt2=System.currentTimeMillis();
      //hazelcast.getMap("inplan.cache.atv").values();
      log.info("Maps Loaded in "+((tt2-tt1)/1000)+" s.");
    }
    catch (Exception e)
    {
      log.error(e, e);
    }
  }

  @SuppressWarnings("unused")
  private void stop()
  {
    hazelcast.getLifecycleService().shutdown();
  }

  public static void main(String args[])
  {
    BasicConfigurator.configure();
    //boolean enableMongo=true;
    boolean enableMongo=true;
    boolean logClients = false;
    boolean logInstances = false;
    boolean logEntrys = false;

    if(args!=null)
    {
      if(args.length==1)
      {
        enableMongo = Boolean.valueOf(args[0]);
      }
      else if(args.length==2)
      {
        enableMongo = Boolean.valueOf(args[0]);
        logClients = Boolean.valueOf(args[1]);
      }
      else if(args.length==3)
      {
        enableMongo = Boolean.valueOf(args[0]);
        logClients = Boolean.valueOf(args[1]);
        logInstances = Boolean.valueOf(args[2]);
      }
      else if(args.length == 4)
      {
        enableMongo = Boolean.valueOf(args[0]);
        logClients = Boolean.valueOf(args[1]);
        logInstances = Boolean.valueOf(args[2]);
        logEntrys = Boolean.valueOf(args[3]);
      }
    }
    new CacheManager(enableMongo,logClients, logInstances, logEntrys);
  }

  // Metodos de la interfaz clientLienstener
  public void clientConnected(Client client)
  {
    log.debug("Client Connected:" + client.getClientType().name());
    log.debug("Num Clients connected:" + hazelcast.getClientService().getConnectedClients().size());
  }

  public void clientDisconnected(Client client)
  {
    log.debug("Client Disconnected:" + client.getClientType().name());
    log.debug("Num Clients connected:" + hazelcast.getClientService().getConnectedClients().size());
  }

  // Metodos de la interfaz InstanceListener

  public void distributedObjectCreated(DistributedObjectEvent event)
  {
    log.debug("Event:" + event.getEventType().name());
    log.debug("Instance " + event.getDistributedObject().getName() + " created");
    if (logEntrys && event.getDistributedObject() instanceof IMap)
    {
      String id = ((IMap<Object, Object>) event.getDistributedObject()).addEntryListener(this, false);
      listenerKeys.put(event.getDistributedObject().getName(), id);
    }

  }

  public void distributedObjectDestroyed(DistributedObjectEvent event)
  {
    log.debug("Event:" + event.getEventType().name());
    log.debug("Instance " + event.getDistributedObject().getName() + "destroyed");
    if (logEntrys && event.getDistributedObject() instanceof IMap)
    {
      String id = listenerKeys.get(event.getDistributedObject().getName());
      listenerKeys.remove(event.getDistributedObject().getName());
      ((IMap<Object, Object>) event.getDistributedObject()).removeEntryListener(id);
    }
  }

  // Metodos de la interfaz EntryListener
  public void entryAdded(EntryEvent<Object, Object> event)
  {
    log.debug(event.getKey() + " added on " + event.getSource());
  }

  public void entryRemoved(EntryEvent<Object, Object> event)
  {
    log.debug(event.getKey() + " removed on " + event.getSource());
  }

  public void entryUpdated(EntryEvent<Object, Object> event)
  {
    log.debug(event.getKey() + " updated on " + event.getSource());
  }

  public void entryEvicted(EntryEvent<Object, Object> event)
  {
    log.debug(event.getKey() + " evicted on " + event.getSource());
  }



}