package com.indra.inplan.dataServices.impl;

import com.hazelcast.query.EntryObject;
import com.hazelcast.query.PredicateBuilder;
import com.indra.inplan.dao.impl.dao.AtvDao;
import com.indra.inplan.dao.impl.dao.DaoQuery;
import com.indra.inplan.dataServices.AtvDataService;
import com.indra.inplan.model.AtvVO;

import java.util.Collection;
import java.util.Date;

/**
 * Created by scaamano on 30/11/14.
 */
public class AtvDataServiceImpl implements AtvDataService
{
  AtvDao atvHzDao;
  AtvDao atvHistoricMongoDao;

  public AtvDataServiceImpl(AtvDao atvHzDao,AtvDao atvHistoricMongoDao)
  {
    this.atvHzDao=atvHzDao;
    this.atvHistoricMongoDao=atvHistoricMongoDao;
  }


  @Override
  public Collection<AtvVO> getAtvRangeInterval(Date initDate, Date enDate)
  {
    return null;
  }

  @Override
  public Collection<AtvVO> getAtvByArrAirport(String arrivalAirport)
  {
    //No usar asi -->Implementar metodo en dao
    DaoQuery query=new DaoQuery();
    EntryObject e = new PredicateBuilder().getEntryObject();
    PredicateBuilder predicate = e.get("arrAirport").equal(arrivalAirport);
    query.setHzPredicate(predicate);
    try
    {
      return atvHzDao.getList(query);
    } catch (Exception e1)
    {
      e1.printStackTrace();
      return null;
    }
  }

  @Override
  public AtvVO getById(String id)
  {
    return atvHzDao.getById(id);
  }

  @Override
  public AtvVO insert(AtvVO entity)
  {

    atvHistoricMongoDao.insert(entity.getId(),entity);
    return atvHzDao.insert(entity.getId(), entity);
  }

  @Override
  public AtvVO update(AtvVO entity)
  {
    atvHistoricMongoDao.update(entity.getId(), entity);
    return atvHzDao.update(entity.getId(), entity);
  }

  @Override
  public AtvVO deleteById(String id)
  {
    return atvHzDao.deleteById(id);
  }

  @Override
  public AtvVO delete(AtvVO entity)
  {
    return atvHzDao.delete(entity);
  }

  @Override
  public Collection<AtvVO> delete(Collection<AtvVO> collection)
  {
    return atvHzDao.delete(collection);
  }

  @Override
  public long count()
  {
    return atvHzDao.count();
  }
}
