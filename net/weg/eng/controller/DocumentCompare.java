package net.weg.eng.controller;

import java.util.Comparator;

import net.weg.eng.document.bean.Document;

public class DocumentCompare implements Comparator<Document> {

	@Override
	public int compare(Document o1, Document o2) { 
		try {
			if (o1.equals(o2)) return 0;
			return o1.getDocumentTemplate().getDescription().compareTo(o2.getDocumentTemplate().getDescription());
		} catch (Exception e) {
			return 0;
		}
		
	}

}
