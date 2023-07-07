/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.business.be;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.Set;
import java.util.HashSet;

import it.csi.dma.apiopsan.business.be.impl.AuditApiServiceImpl;
import it.csi.dma.apiopsan.business.be.impl.CittadiniApiServiceImpl;
import it.csi.dma.apiopsan.business.be.impl.OperatoreSanitarioApiServiceImpl;

//@ApplicationPath("/")
public class RestApplication extends Application {


//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet<Class<?>>();
//        resources.add(AuditApiServiceImpl.class);
//        resources.add(CittadiniApiServiceImpl.class);
//        resources.add(OperatoreSanitarioApiServiceImpl.class);
//
//        return resources;
//    }




}