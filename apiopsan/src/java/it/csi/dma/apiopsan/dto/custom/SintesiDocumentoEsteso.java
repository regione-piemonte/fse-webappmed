/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import it.csi.dma.apiopsan.dto.SintesiDocumento;

public class SintesiDocumentoEsteso extends  SintesiDocumento {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String classe = null;
 

  public String getClasse() {
	return classe;
  }
  public void setClasse(String classe) {
	this.classe = classe;
  }

}

