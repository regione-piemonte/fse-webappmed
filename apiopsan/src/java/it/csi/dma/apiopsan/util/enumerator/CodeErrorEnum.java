/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.enumerator;

public enum CodeErrorEnum {
	
	IntevalloDateSuperato("CC_ER_215"),
	TipoDocumentoNonValido("FSE_ER_504"),
	CategoriaTipoDocumentoNonValido("FSE_ER_573"),
	
	CC_ER_001("CC_ER_001"),
	CC_ER_007("CC_ER_007"),
	CC_ER_205("CC_ER_205"),
	CC_ER_125("CC_ER_125"),
	CC_ER_195("CC_ER_195"),
	FSE_ER_509("FSE_ER_509"),
	FSE_ER_100("FSE_ER_100"),
	FSE_ER_101("FSE_ER_101"),
	FSE_ER_529 ("FSE_ER_529"),
	CC_ER_002("CC_ER_002"),
	FSE_ER_562("FSE_ER_562"),
	FSE_COD_FATAL("FSE-COD-FATAL"),
	CC_LOG_002("CC_LOG_002"),
	FSE_ER_566("FSE_ER_566"),
	FSE_ER_062("FSE_ER_062"),
	FSE_ER_063("FSE_ER_063"),
	CC_ER_206("CC_ER_206"),
	FSE_ER_568("FSE_ER_568"),
	FSE_ER_505("FSE_ER_505"),
	FSE_ER_567("FSE_ER_567"),
	FSE_ER_570("FSE_ER_570"),
	CC_LOG_003("CC_LOG_003"),
	CC_ER_160("CC_ER_160"),
	CL_ER_107("CL_ER_107"),
	CC_ER_204("CC_ER_204"),
	CC_ER_175("CC_ER_175"),
	CC_ER_178("CC_ER_178");


	

	private final String code;
	
	CodeErrorEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	
}
