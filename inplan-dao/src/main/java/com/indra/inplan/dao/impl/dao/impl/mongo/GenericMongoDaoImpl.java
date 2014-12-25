package com.indra.inplan.dao.impl.dao.impl.mongo;

import com.indra.inplan.dao.impl.dao.DaoQuery;
import com.indra.inplan.dao.impl.dao.GenericDao;
import com.indra.inplan.model.INplanBO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;

/**
 * Created by scaamano on 28/11/14.
 */
public class GenericMongoDaoImpl<E extends INplanBO,PK> implements GenericDao<E,PK >
{
  //TODOO inyectar con spring

  protected MongoTemplate persistenceManager;
  protected String collectionName;
  protected Class<E> entityClass;

  public GenericMongoDaoImpl(MongoTemplate persistenceManager,Class<E> entityClass, String collectionName)
  {
    this.entityClass=entityClass;
    this.collectionName=collectionName;
    this.persistenceManager=persistenceManager;
  }

  @Override
  public E getById(PK id)
  {
    E entity=persistenceManager.findById(id,entityClass,collectionName);
    return entity;
  }

  @Override
  public E insert(PK id, E entity)
  {
    persistenceManager.insert(entity, collectionName);
    return entity;
  }

  @Override
  public E update(PK id, E entity)
  {
    persistenceManager.save(entity,collectionName);
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
  public E deleteById(PK id)
  {
    Query query=new Query();
    query.addCriteria(Criteria.where("_id").is(id));
    E entity=(E)persistenceManager.findAndRemove(query,entityClass,collectionName);
    return entity;
  }

  @Override
  public long count()
  {
    return persistenceManager.count(new Query(),collectionName);
  }

  @Override
  public long count(DaoQuery query)
  {
    return persistenceManager.count(query.getMongoQuery(),collectionName);
  }

  @Override
  public Collection<E> getList(DaoQuery query) throws Exception
  {
    Query mongoQuery=query.getMongoQuery();
    Collection<E> collection=persistenceManager.find(mongoQuery,entityClass,collectionName);
    return collection;
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
