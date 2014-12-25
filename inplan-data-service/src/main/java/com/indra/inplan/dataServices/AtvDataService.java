package com.indra.inplan.dataServices;

import com.indra.inplan.model.AtvVO;
import com.indra.inplan.model.INplanBO;

import java.util.Collection;
import java.util.Date;

/**
 * Created by scaamano on 30/11/14.
 */
public interface AtvDataService extends DataService<AtvVO,String>
{

  public Collection<AtvVO> getAtvRangeInterval(Date initDate,Date enDate);

  public Collection<AtvVO> getAtvByArrAirport(String arrivalAirport);
}
