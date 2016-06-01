/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sample.javaee.memoapp.entity.Memo;

/**
 *
 * @author matsumotoshigeharu
 */
@Stateless
public class MemoFacade extends AbstractFacade<Memo> {

    @PersistenceContext(unitName = "com.mycompany_MemoApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemoFacade() {
        super(Memo.class);
    }
    
}
