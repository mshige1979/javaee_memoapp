/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import java.util.List;
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
@Named(value = "memoBean")
@RequestScoped
public class MemoBean {
    
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<Memo> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<Memo> memoList) {
        this.memoList = memoList;
    }
    private List<Memo> memoList;
    
    @Inject
    private MemoFacade memoFacade;
    
    @PostConstruct
    public void init(){
        getAllMemo();
    }
    
    public void createMemo(){
        if(memo.length()>0){
            Memo newMemo = new Memo();
            newMemo.setMemo(memo);
            memoFacade.create(newMemo);
            getAllMemo();
        }
        memo = "";
    }
    
    public String updateMemo(Memo editMemo){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("editMemoId", editMemo.getId());
        return "memoEdit.xhtml";
    }
    
    public void deleteMemo(Memo delMemo){
        memoFacade.remove(delMemo);
        getAllMemo();
    }
    
    public void getAllMemo(){
        memoList = memoFacade.findAll();
    }
    
}
