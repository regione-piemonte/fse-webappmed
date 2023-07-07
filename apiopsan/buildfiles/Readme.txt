Generazione classi ConsensoINIExtService con apache-cxf-3.5.3
comando shell
wsdl2java -p it.csi.dma.apiopsan.consensoextservice -client -encoding UTF8 ConsensoINIExtService.wsdl


Generazione classi TaccuinoService con apache-cxf-3.5.3
comando shell
wsdl2java -p it.csi.dma.apiopsan.taccuinoservice -client -encoding UTF8 TaccuinoService.wsdl

Generazione classi servizio in componente centrale RicercaDocumentiINIService con apache-cxf-3.5.3
wsdl2java -encoding UTF8  -p http://dmacc.csi.it/=it.csi.dma.apiopsan.ricercadocumentiiniservice -p http://dma.csi.it/=it.csi.dma.apiopsan.ricercadocumentiiniservice  -p http://tipodatiricercadocumenti.xsd.fse.ini.finanze.it=it.csi.dma.apiopsan.ricercadocumentiiniservice.fse  -d ..\src\java\ RicercaDocumentiINIService.wsdl 
