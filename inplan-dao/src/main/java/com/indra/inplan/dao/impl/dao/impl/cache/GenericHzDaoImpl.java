package com.indra.inplan.dao.impl.dao.impl.cache;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.indra.inplan.dao.impl.dao.DaoQuery;
import com.indra.inplan.dao.impl.dao.GenericDao;
import com.indra.inplan.model.INplanBO;

import java.util.Collection;
import java.util.Map;

/**
 * Created by scaamano on 28/11/14.
 */
public abstract class GenericHzDaoImpl<E extends INplanBO, PK> implements GenericDao<E,PK>
{

  //TODO Inyectar con spring
  protected HazelcastInstance persistenceManager =null;
  protected String collectionName;
  protected Class<E> entityClass;

  public GenericHzDaoImpl(HazelcastInstance instance,Class<E> entityClass, String collectionName)
  {
    this.entityClass=entityClass;
    this.collectionName=collectionName;
    this.persistenceManager =instance;
  }

  @Override
  public E getById(PK id)
  {
    persistenceManager.getName();
    Map map= persistenceManager.getMap(collectionName);
    E entity=(E)map.get(id);
    return entity;
  }

  @Override
  public E insert(PK id, E entity)
  {
    Map map= persistenceManager.getMap(collectionName);
    entity=(E)map.put(id,entity);
    return entity;
  }

  @Override
  public E delete(E entity)
  {
    return null;
  }

  @Override
  public Collection<E> delete(Collection<E> collection)
  {
    return null;
  }

  @Override
  public E update(PK id,E entity)
  {
    return insert(id,entity);
  }

  @Override
  public E deleteById(PK id)
  {
    Map map= persistenceManager.getMap(collectionName);
    E entity=(E)map.remove(id);
    return entity;
  }



  @Override
  public long count()
  {
    return persistenceManager.getMap(collectionName).values().size();
  }

  @Override
  public long count(DaoQuery query)
  {
    return persistenceManager.getMap(collectionName).values(query.getHzPredicate()).size();
  }

  @Override
  public Collection<E> getList(DaoQuery query) throws Exception
  {
    Predicate predicate=query.getHzPredicate();
    if(predicate==null)throw new Exception();
    IMap map= persistenceManager.getMap(collectionName);
    return map.values(predicate);
  }


  @Override
  public Collection<E> getList(DaoQuery query, int numElements, int page)
  {
    return null;
  }

  @Override
  public Class<E> getEntityClass()
  {
    return entityClass;
  }
  @Override
  public String getCollectionName()
  {
    return collectionName;
  }
}
