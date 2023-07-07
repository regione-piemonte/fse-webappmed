/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Converter {
	
	public static Date getData(XMLGregorianCalendar calendar){
		if (calendar == null) {
			return null;
		}
		Date date = calendar.toGregorianCalendar().getTime();
		return date;	
				
	}

	public static int getInt(String stringa) {
		if (Util.isValorizzato(stringa))
			return Integer.parseInt(stringa);
		else
			return 0;
	}
	
	public static Date getData(String data) {
		if (!Util.isValorizzato(data))
			return null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			
			Date dataDate = sdf.parse(data);

			return dataDate;
		} catch (ParseException pe) {
			// throw new ApplicationException("Errore di conversione da stringa a data.");
			return null;
		}
	}
	
	public static Date getDataWithoutTime(String data) {
		if (!Util.isValorizzato(data))
			return null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date dataDate = sdf.parse(data);

			return dataDate;
		} catch (ParseException pe) {
			// throw new ApplicationException("Errore di conversione da stringa a data.");
			return null;
		}
	}
	
	
	public static XMLGregorianCalendar convertToCalendar(String date) {

		Date data = getDataWithoutTime(date);
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		if(data!=null) {
			gc.setTime(data);

			try {
				xmlDate= DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			} catch (DatatypeConfigurationException e) {
				return null;
			} 
		}
		return xmlDate;
	}
	
	
	public static long getLong(String stringa) {
		if (Util.isValorizzato(stringa))
			return Long.parseLong(stringa);
		else
			return 0;
	}
	
	public static String getDataISO(Date data) {
		if (data == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataString = sdf.format(data);
		return dataString;
	}

}
