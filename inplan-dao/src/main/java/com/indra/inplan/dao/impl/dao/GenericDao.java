package com.indra.inplan.dao.impl.dao;

import com.indra.inplan.model.INplanBO;

import java.util.Collection;

/**
 * Created by scaamano on 28/11/14.
 */
public interface GenericDao<E extends INplanBO, PK>
{
  public E getById(PK id);
  public E insert(PK id, E entity);
  public E update(PK id,E entity);
  public E deleteById (PK id);
  public E delete(E entity);
  public Collection<E> delete(Collection<E> collection);
  public long count();
  public long count(DaoQuery query);
  public Collection<E> getList(DaoQuery query) throws Exception;
  public Collection<E> getList(DaoQuery query,int numElements, int page);
  public Class<E> getEntityClass();
  public String getCollectionName();
}
