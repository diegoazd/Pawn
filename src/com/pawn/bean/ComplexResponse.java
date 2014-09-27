/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pawn.bean;

import java.util.ArrayList;

/**
 *
 * @author rodolfocasanova
 */
public class ComplexResponse extends BasicResponse{
  
    private Object single;
    private Integer type;
    private ArrayList<?> collection;
    static final public Integer SINGLERESPONSE=0x01;
    static final public Integer COLLECTIONRESPONSE=0x02;

    /**
     * @return the single
     */
    public Object getSingle() {
        return single;
    }

    /**
     * @param single the single to set
     */
    public void setSingle(Object single) {
        this.single = single;
    }

    /**
     * @return the collection
     */
    public ArrayList<?> getCollection() {
        return collection;
    }

    /**
     * @param collection the collection to set
     */
    public void setCollection(ArrayList<?> collection) {
        this.collection = collection;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
}
