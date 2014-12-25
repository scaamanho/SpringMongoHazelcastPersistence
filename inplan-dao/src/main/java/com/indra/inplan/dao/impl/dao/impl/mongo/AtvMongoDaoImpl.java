package com.indra.inplan.dao.impl.dao.impl.mongo;

import com.indra.inplan.dao.impl.dao.AtvDao;
import com.indra.inplan.dao.impl.dao.DaoFieldList;
import com.indra.inplan.dao.impl.dao.DaoQuery;
import com.indra.inplan.dao.impl.dao.impl.mongo.GenericMongoDaoImpl;
import com.indra.inplan.model.AtvVO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;

/**
 * Created by scaamano on 28/11/14.
 */
public class AtvMongoDaoImpl extends GenericMongoDaoImpl<AtvVO,String> implements AtvDao
{
  public AtvMongoDaoImpl(MongoTemplate persistenceManager,String collectionName)
  {
    super(persistenceManager,AtvVO.class,collectionName);
  }

  @Override
  public Collection<AtvVO> getActiveAtvs()
  {
    try
    {
      Query mongoQuery=new Query();
      mongoQuery.addCriteria(Criteria.where("active").is(Boolean.TRUE));
      DaoQuery query=new DaoQuery(mongoQuery);
      return getList(query);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  @Override
  public Collection<AtvVO> getFilteredAtvs(DaoFieldList fieldList)
  {
    return null;
  }
}
