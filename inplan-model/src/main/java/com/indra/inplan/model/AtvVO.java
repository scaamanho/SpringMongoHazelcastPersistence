package com.indra.inplan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by scaamano on 28/11/14.
 */
public class AtvVO implements INplanBO
{
  @Id //id will be used for storing MongoDB _id
  private String id;
  private Date date;
  private String arrFlightNumber;
  private String depFlightNumber;
  private String depAirport;
  private String arrAirport;
  private String airport;
  private Date tobt;
  private Boolean active;

  public AtvVO()
  {}

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public String getArrFlightNumber()
  {
    return arrFlightNumber;
  }

  public void setArrFlightNumber(String arrFlightNumber)
  {
    this.arrFlightNumber = arrFlightNumber;
  }

  public String getDepFlightNumber()
  {
    return depFlightNumber;
  }

  public void setDepFlightNumber(String depFlightNumber)
  {
    this.depFlightNumber = depFlightNumber;
  }

  public String getDepAirport()
  {
    return depAirport;
  }

  public void setDepAirport(String depAirport)
  {
    this.depAirport = depAirport;
  }

  public String getArrAirport()
  {
    return arrAirport;
  }

  public void setArrAirport(String arrAirport)
  {
    this.arrAirport = arrAirport;
  }

  public String getAirport()
  {
    return airport;
  }

  public void setAirport(String airport)
  {
    this.airport = airport;
  }

  public Date getTobt()
  {
    return tobt;
  }

  public void setTobt(Date tobt)
  {
    this.tobt = tobt;
  }

  public Boolean isActive()
  {
    return active;
  }

  public void setActive(Boolean active)
  {
    this.active = active;
  }
}
