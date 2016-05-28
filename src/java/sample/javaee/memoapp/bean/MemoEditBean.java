/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import sample.javaee.memoapp.ejb.MemoFacade;
import sample.javaee.memoapp.entity.Memo;

/**
 *
 * @author hoge
 */
@Named(value = "memoEditBean")
@RequestScoped
public class MemoEditBean {

    private Memo editMemo;

    public Memo getEditMemo() {
        return editMemo;
    }
    
    @Inject
    private MemoFacade memoFacade;
    
    @PostConstruct
    public void init(){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Integer key = (Integer)flash.get("editMemoId");
        editMemo = memoFacade.find(key);
        flash.put("editMemoId", editMemo.getId());
    }
    
    public String updateMemo(){
        memoFacade.edit(editMemo);
        return "memo.xhtml";
    }
}
