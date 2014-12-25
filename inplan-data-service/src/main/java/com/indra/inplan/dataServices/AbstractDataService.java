package com.indra.inplan.dataServices;

import com.indra.inplan.dao.impl.dao.GenericDao;
import com.indra.inplan.dataServices.DataService;
import com.indra.inplan.model.INplanBO;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by scaamano on 30/11/14.
 */
public abstract class AbstractDataService<E extends INplanBO,PK> implements DataService<E,PK>
{

}
