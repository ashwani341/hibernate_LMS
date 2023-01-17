package com.cdac.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {

  private static SessionFactory sf;

  public static SessionFactory getSessionFactory() {
    try {
      sf = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      System.err.println(e);
    }
    
    return sf;
  }

}
