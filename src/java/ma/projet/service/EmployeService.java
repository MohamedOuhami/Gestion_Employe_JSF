/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author LACHGAR
 */
public class EmployeService implements IDao<Employe>{

    @Override
    public boolean create(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(int id) {
        Employe employe  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employe  = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        List <Employe> employes = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return employes;
    }
    
    public List<Object[]> nbemploye(){
        List<Object[]> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(m.nom), m.nom from Employe m group by m.nom").list();
        session.getTransaction().commit();
        return employes;
    }
    
    
    
    public List<Employe> getbydates(Date d1 , Date d2){
        List <Employe> employes = new ArrayList<>();
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
         employes  = session.createQuery("from Employe m where m.dateNaissance between :d1 and :d2").setParameter("d1", d1).setParameter("d2", d2).list();
        session.getTransaction().commit();
        return employes;
        
    }
    
    public List<Employe> getEmployeesByService(int serviceId) {
        List<Employe> employes = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        // Assuming you have a Service entity with a field named 'id'
        employes = session.createQuery("from Employe e where e.service.id = :serviceId").setParameter("serviceId", serviceId)
                .list();
                
        session.getTransaction().commit();
        return employes;
    }
    
}
