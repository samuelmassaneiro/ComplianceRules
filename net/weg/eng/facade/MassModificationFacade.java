package net.weg.eng.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.weg.eng.maestro.plm.MassModificationService;

@Component
public class MassModificationFacade {

	@Resource
	private MassModificationService massModificationService;

	public void updateMaterialsSap(List<Long> batchProcessId) {
		this.massModificationService.updateMaterialsSap(batchProcessId);
	}
}