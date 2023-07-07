/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTemplateMessage {

	private static final String SDF_DD_MM_YYYY = "dd-MM-yyyy";

	private CreateTemplateMessage() {
		throw new IllegalStateException("Utility class");
	}

	// Dato un template e un hashmap di parametri ritorna il messaggio di testo
	public static String generateTextByTemplateAndMap(String template, Map<String, Object> mappaParametri) {
		LinkedHashMap<String, Object> mappa = new LinkedHashMap<>();
		String templateToReturn = null;
		try {
			for (Map.Entry<String, Object> entry : mappaParametri.entrySet()) {
				mappa.put(Pattern.quote("{" + entry.getKey() + "}"), entry.getValue());
			}
			int cont = 0;
			int lastCreated = 0;
			int firstCreated = 0;
			String[] arrayText = new String[mappa.size()];
			for (Map.Entry<String, Object> entry : mappa.entrySet()) {
				Pattern pattern = Pattern.compile(entry.getKey(), Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(template);
				if (matcher.find()) {
					String value = "";
					if (entry.getValue() instanceof String) {
						value = (String) entry.getValue();
					} else if (entry.getValue() instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat(SDF_DD_MM_YYYY);
						value = sdf.format(entry.getValue());
					} else if (entry.getValue() instanceof Integer || entry.getValue() instanceof Long
							|| entry.getValue() instanceof Float || entry.getValue() instanceof Double
							|| entry.getValue() instanceof Boolean) {
						value = entry.getValue().toString();
					}
					if (firstCreated > 0) {
						arrayText[cont] = arrayText[lastCreated].replaceAll(entry.getKey(), value);
					} else {
						arrayText[cont] = matcher.replaceAll(value);
						firstCreated++;
					}
					lastCreated = cont;
				}
				cont++;
			}
			templateToReturn = firstCreated != 0 ? arrayText[lastCreated] : template;
		} catch (Exception ex) {
			
			return template;
		}
		return templateToReturn;
	}
}
