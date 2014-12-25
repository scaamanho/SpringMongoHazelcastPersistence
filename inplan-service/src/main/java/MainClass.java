import com.indra.inplan.dataServices.AtvDataService;
import com.indra.inplan.model.AtvVO;
import com.indra.inplan.springctx.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by scaamano on 28/11/14.
 */
public class MainClass
{
  public static void main(String args[])
  {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    //Using context reference
    ApplicationContextProvider appContext = new ApplicationContextProvider();
    //TODO PersonDAO personDAO = appContext.getApplicationContext().getBean("personDAO", PersonDAO.class);
    AtvDataService atvDataService=appContext.getApplicationContext().getBean("atvService",AtvDataService.class);
    AtvVO atv=new AtvVO();
    atv.setId(UUID.randomUUID().toString());
    atv.setArrAirport("LEPA");
    atvDataService.insert(atv);
    Collection col=atvDataService.getAtvByArrAirport("LEPA");
    System.out.println("Size:"+col.size());                                                                                                                                                                                                                                                                                                                                      System.out.println("Size:"+col.size());
  }
}
