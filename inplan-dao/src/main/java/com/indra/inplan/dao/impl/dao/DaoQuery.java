package com.indra.inplan.dao.impl.dao;

import com.hazelcast.query.Predicate;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by scaamano on 30/11/14.
 */
public class DaoQuery
{
  private Predicate hzPredicate = null;
  private Query mongoQuery = null;

  public DaoQuery()
  {
    this(null, null);
  }

  public DaoQuery(Predicate hzPredicate)
  {
    this(hzPredicate, null);
  }

  public DaoQuery(Query mongoQuery)
  {
    this(null, mongoQuery);
  }

  public DaoQuery(Predicate hzPredicate, Query mongoQuery)
  {
    this.hzPredicate = hzPredicate;
    this.mongoQuery = mongoQuery;
  }

  public Predicate getHzPredicate()
  {
    return hzPredicate;
  }

  public void setHzPredicate(Predicate hzPredicate)
  {
    this.hzPredicate = hzPredicate;
  }

  public Query getMongoQuery()
  {
    return mongoQuery;
  }

  public void setMongoQuery(Query mongoQuery)
  {
    this.mongoQuery = mongoQuery;
  }
}
