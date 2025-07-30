package com.example.vts.Activity.Adapter;
public class PermissionGetset {
    String tiltle,scopevalueadd,scopevalueview,scopevaluedelete,addid,viewid,deleteid,Permissionid;
    boolean Add,View,Delete;
    public PermissionGetset(String tiltle, String scopevalueadd, String scopevalueview, String scopevaluedelete, String addid, String viewid, String deleteid,  boolean add, boolean view, boolean delete,String permissionid) {
        this.tiltle = tiltle;
        this.scopevalueadd = scopevalueadd;
        this.scopevalueview = scopevalueview;
        this.scopevaluedelete = scopevaluedelete;
        this.addid = addid;
        this.viewid = viewid;
        this.deleteid = deleteid;
        Add = add;
        View = view;
        Delete = delete;
        Permissionid = permissionid;
    }
    public String getTiltle() {
        return tiltle;
    }
    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }
    public String getScopevalueadd() {
        return scopevalueadd;
    }
    public void setScopevalueadd(String scopevalueadd) {
        this.scopevalueadd = scopevalueadd;
    }
    public String getScopevalueview() {
        return scopevalueview;
    }
    public void setScopevalueview(String scopevalueview) {
        this.scopevalueview = scopevalueview;
    }
    public String getScopevaluedelete() {
        return scopevaluedelete;
    }
    public void setScopevaluedelete(String scopevaluedelete) {
        this.scopevaluedelete = scopevaluedelete;
    }
    public String getAddid() {
        return addid;
    }
    public void setAddid(String addid) {
        this.addid = addid;
    }
    public String getViewid() {
        return viewid;
    }
    public void setViewid(String viewid) {
        this.viewid = viewid;
    }
    public String getDeleteid() {
        return deleteid;
    }
    public void setDeleteid(String deleteid) {
        this.deleteid = deleteid;
    }
    public String getPermissionid() {
        return Permissionid;
    }
    public void setPermissionid(String permissionid) {
        Permissionid = permissionid;
    }
    public boolean isAdd() {
        return Add;
    }
    public void setAdd(boolean add) {
        Add = add;
    }
    public boolean isView() {
        return View;
    }
    public void setView(boolean view) {
        View = view;
    }
    public boolean isDelete() {
        return Delete;
    }
    public void setDelete(boolean delete) {
        Delete = delete;
    }
}
