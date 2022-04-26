package net.weg.eng.service;

import net.weg.eng.bean.process.BatchProcess;
import net.weg.eng.maestro.massmodification.MassModificationProcessExportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExportProcessService {

    @Resource
    private MassModificationProcessExportService massModificationProcessExportService;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String filterExport(BatchProcess process, List<String> characteristics) {
        return massModificationProcessExportService.filterExport(process, characteristics);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String filterExportByMotor(BatchProcess process) {
        return massModificationProcessExportService.filterExportByMotor(process);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String filterExportByEb(BatchProcess process) {
        return massModificationProcessExportService.filterExportByEb(process);
    }

}