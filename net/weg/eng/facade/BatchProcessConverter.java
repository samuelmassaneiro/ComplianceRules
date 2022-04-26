package net.weg.eng.facade;

import org.directwebremoting.extend.InboundContext;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.hibernate.H3BeanConverter;

import net.weg.eng.bean.process.BatchProcess;
import net.weg.eng.bean.process.ProcessItem;

public class BatchProcessConverter extends H3BeanConverter {
	public Object convertInbound(Class<?> paramType, InboundVariable data, InboundContext inboundContext) throws MarshallException {
		Object object = super.convertInbound(paramType, data, inboundContext);
		if (object instanceof BatchProcess) {
			BatchProcess bp = (BatchProcess) object;
			if (bp.getProcessItens() != null)
				setPersistentBatchProcess(bp);
		}
		return object;
	}

	private void setPersistentBatchProcess(BatchProcess bp) {
		for (ProcessItem pi : bp.getProcessItens()) {
			pi.setBatchProcess(bp);
		}
	}
}