package com.indra.inplan.springctx;

/**
 * Created by scaamano on 28/11/14.
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware{

  private static ApplicationContext context;

  public ApplicationContext getApplicationContext() {
    return context;
  }

  @Override
  public void setApplicationContext(ApplicationContext ac)
      throws BeansException {
    context = ac;
  }
}
