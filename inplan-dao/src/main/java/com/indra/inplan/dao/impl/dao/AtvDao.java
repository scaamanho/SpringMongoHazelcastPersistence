package com.indra.inplan.dao.impl.dao;

import com.indra.inplan.model.AtvVO;

import java.util.Collection;
import java.util.Date;

/**
 * Created by scaamano on 30/11/14.
 */
public interface AtvDao extends GenericDao<AtvVO,String>
{
  public Collection<AtvVO> getActiveAtvs();
  public Collection<AtvVO> getFilteredAtvs(DaoFieldList fieldList);
}
