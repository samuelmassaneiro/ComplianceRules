package net.weg.eng.facade;

import java.util.List;

import net.weg.maestro.plm.bean.Material;
import net.weg.maestro.plm.service.MaterialService;
import net.weg.eng.maestro.ee.service.object.ObjectEEService;
import net.weg.eng.service.SerializeType;
import net.weg.eng.service.Synchronizer;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.report.ReportService;

//@Component
public class ObjectEEFacade {

	//@Resource
	private MaterialService materialService;
	//@Resource
	private ObjectEEService objectEEService;
	//@Resource
	private ReportService reportService;



	public List<Material> alterUse(List<Material> materials) {
		return this.materialService.alterMaterial(materials);
	}

	@Synchronizer(serializable = { SerializeType.IN, SerializeType.OUT })
	public ObjectContext undoAndChange(ObjectContext objectContext) {
		return objectEEService.nulifyAndReplaceWithReload(objectContext);
	}

	@Synchronizer(serializable = { SerializeType.IN, SerializeType.OUT })
	public ObjectContext updateWindingsAndWires(ObjectContext objectContextTransient, int bobinagem) {
		return objectEEService.updateWindingsAndWires(objectContextTransient, bobinagem);
	}

	public String generatePdfReport(ObjectContext objectContext, String template) {
		return this.reportService.generateReport(objectContext, template);
	}

	public List<Material> findMaterialByObjectHeader(ObjectContext objectContext) {
		return this.objectEEService.findMaterialByObjectHeader(objectContext);
	}

	@Synchronizer(serializable = { SerializeType.IN , SerializeType.OUT })
	public ObjectContext importer(ObjectContext objectContext) {
		return this.objectEEService.importer(objectContext);
	}
}
