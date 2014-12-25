package com.indra.inplan.dao.impl.dao.impl.cache;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.indra.inplan.dao.impl.dao.AtvDao;
import com.indra.inplan.dao.impl.dao.DaoFieldList;
import com.indra.inplan.dao.impl.dao.DaoQuery;
import com.indra.inplan.dao.impl.dao.impl.cache.GenericHzDaoImpl;
import com.indra.inplan.model.AtvVO;

import java.util.Collection;

/**
 * Created by scaamano on 28/11/14.
 */
public class AtvHzDaoImpl extends GenericHzDaoImpl<AtvVO,String> implements AtvDao
{
  public AtvHzDaoImpl(HazelcastInstance persistenceManager,String collectionName)
  {
    super(persistenceManager, AtvVO.class,collectionName);
  }

  public Collection<AtvVO> getActiveAtvs()
  {
    try
    {
      EntryObject e = new PredicateBuilder().getEntryObject();
      PredicateBuilder predicate = e.get("active").equal(true);
      DaoQuery query=new DaoQuery(predicate);
      return getList(query);
    }
    catch (Exception e)
    {return null;}
  }

  public Collection<AtvVO> getFilteredAtvs(DaoFieldList fieldList)
  {return null;}


}
