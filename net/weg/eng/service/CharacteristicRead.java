package net.weg.eng.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import net.weg.eng.bundle.ResourceBundleDataBase;
import net.weg.eng.xml.type.CharacteristicFullXmlType;
import net.weg.eng.xml.type.CharacteristicLanguageXmlType;

//@Service
@WebService(name = "CharacteristicRead", targetNamespace = "http://soa.weg.net/eng/maestro/characteristicRead")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CharacteristicRead {

	@Resource
	private CharacteristicSynchronizeService characteristicSynchronizeService;

	@Resource
	private ResourceBundleDataBase resource;

	@WebMethod
	public CharacteristicFullXmlType getCharacteristicByName(@WebParam(name = "characteristicName") String name) {
		CharacteristicFullXmlType charact = null;
		try {
			charact = this.characteristicSynchronizeService.findOnlyByName(name);
		} catch (javax.persistence.NoResultException nre) {
			charact = findLabel(name);
			if (charact == null)
				throw nre;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return charact;
	}

	private CharacteristicFullXmlType findLabel(String name) {
		CharacteristicFullXmlType charact = null;
		for (String lang : resource.getLanguages()) {
			String label = resource.getLabel(name, lang);
			if (label == null)
				return null;
			if (charact == null) {
				charact = new CharacteristicFullXmlType();
				charact.setName(name);
				charact.setType(net.weg.eng.bean.CharacteristicType.STRINGFIX);
			}
			CharacteristicLanguageXmlType cLang = new CharacteristicLanguageXmlType();
			cLang.setDescription(label);
			cLang.setLanguage(lang);
			charact.getCharacteristicLanguages().add(cLang);
		}
		return charact;
	}
}
