package com.example.util;

import com.example.repository.model.FileModel;
import com.example.repository.model.Metadata;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by cavayman on 01.10.2016.
 */
public class MainTest {
    private static SessionFactory factory;
    public static void main(String[] arg){
        factory=HibernateUtil.getSessionFactory();

       Session session= factory.openSession();
        Transaction tx = session.beginTransaction();

        FileModel f1= new FileModel();
        f1.setFilePath("xyi1");
        Metadata m1=new Metadata();
        FileModel f2= new FileModel();
        f1.setFilePath("xyi2");
        Metadata m2=new Metadata();
        FileModel f3= new FileModel();
        f1.setFilePath("xyi3");
        Metadata m3=new Metadata();
        FileModel f4= new FileModel();
        f1.setFilePath("xyi4");
        Metadata m4=new Metadata();
        FileModel f5= new FileModel();
        f1.setFilePath("xyi5");
        Metadata m5=new Metadata();

        f1.setMetadata(m1);
        f2.setMetadata(m2);
        f3.setMetadata(m3);
        f4.setMetadata(m4);
        f5.setMetadata(m5);
        session.save(f1);
        session.save(f2);
        session.save(f3);
        session.save(f4);
        session.save(f5);
        tx.commit();

        session.close();

    }
}
