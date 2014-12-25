package com.indra.inplan.dataServices;

import com.indra.inplan.dao.impl.dao.DaoQuery;
import com.indra.inplan.model.AtvVO;
import com.indra.inplan.model.INplanBO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by scaamano on 30/11/14.
 */
public interface DataService<E extends INplanBO,PK>
{
  public E getById(PK id);
  public E insert( E entity);
  public E update(E entity);
  public E deleteById (PK id);
  public E delete(E entity);
  public Collection<E> delete(Collection<E> collection);
  public long count();
}
