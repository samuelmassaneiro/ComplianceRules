package net.weg.eng.facade;

import net.weg.eng.bean.process.BatchProcess;
import net.weg.eng.bean.process.ProcessItem;
import net.weg.eng.dao.BatchProcessDao;
import net.weg.eng.service.ExportProcessService;
import net.weg.eng.service.process.ProcessService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ProcessFacade {

	@Resource
	private ProcessService processService;
	@Resource
	private ExportProcessService exportProcessService;

	@Resource
	private BatchProcessDao batchProcessDao;

	public ListTransport<BatchProcess> getAllProcess() {
		return new ListTransport<BatchProcess>(this.processService.getAll(0, 999));
	}

	public ListTransport<BatchProcess> getProcessPaginated(int start, int limit) {
		return new ListTransport<BatchProcess>(this.processService.getAll(start, limit), this.batchProcessDao.getCount());
	}

	public ListTransport<BatchProcess> getAllByEcm(int firstResult, int maxResult, Long ecm) {
		if (ecm == null)
			return new ListTransport<BatchProcess>(this.processService.getAll(firstResult, maxResult), this.batchProcessDao.getCount());
		return new ListTransport<BatchProcess>(this.processService.getAllByEcm(firstResult, maxResult, ecm), this.batchProcessDao.getCountByEcm(ecm));
	}

	public ListTransport<String> getBatchTypes() {
		return new ListTransport<String>(this.processService.getBatchTypes());
	}

	public List<Number> getObjectVariantIdByEcm(String ecmId) {
		return this.processService.searchObjectVariantIdByEcmAndRuleApplied(ecmId);
	}

	public ListTransport<ProcessItem> getObjectVariantIds(Long batchProcessId) {
		return this.processService.findProcessItemsByProcessById(batchProcessId);
	}

	public boolean saveBatchProcess(BatchProcess batchProcess) {
		return this.processService.saveBatchProcess(batchProcess);
	}

	public void alterStatus(List<Long> ids) {
		this.processService.startSelectedProcesses(ids);
	}
	

	public boolean remove(List<Long> ids) {
		return this.processService.remove(ids);
	}

	public List<String> getObjectCharacteristicByBatchProcessOrObject(BatchProcess batchProcess) {
		return this.processService.getObjectCharacteristicByBatchProcess(batchProcess);
	}

	public String filterExport(BatchProcess process, List<String> characteristics, String type) {
		return this.exportProcessService.filterExport(process, characteristics);
	}

	public String filterExportWithMotor(BatchProcess process) {
		return this.exportProcessService.filterExportByMotor(process);
	}

	public String filterExportWithEb(BatchProcess process) {
		return this.exportProcessService.filterExportByEb(process);
	}
}
