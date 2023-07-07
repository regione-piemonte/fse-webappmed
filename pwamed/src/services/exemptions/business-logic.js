/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */


const ICON_MAP = {
  IN: 'disabilita',
  MC: 'virus',
  MR: 'dna',
}
export const getIconMap = (code) =>{

 const DISABILITY_REGEX =  /^[A-Za-z][0-9]{2}$/;
 const PATHOLOGY_REGEX = /^[0-9]{3}$/

  if( DISABILITY_REGEX.test(code)){
    return ICON_MAP.IN
  }else if(PATHOLOGY_REGEX.test(code)){
    return ICON_MAP.MC
  }else{
    return ICON_MAP.MR
  }


}


