package net.weg.eng.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.weg.eng.MaestroException;
import net.weg.eng.bean.object.Method;
import net.weg.eng.resource.MethodResource;
import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.xml.adapter.ObjectContextRecursiveXmlAdapter;
import net.weg.eng.xml.type.MaestroMethodRequest;
import net.weg.eng.xml.type.MaestroMethodResponse;

@Service("methodServiceProvider")
public class MethodServiceProviderImp extends ServiceBase {
	private static final String USER_IS_NULL_IN_REQUEST = "Usuário vazio na requisição!";

	@Resource
	private MethodService methodService;

	@Resource
	private ManagerAuthenticator managerAuthenticator;

	@Resource
	private MethodResource methodResource;
	@Resource
	private ObjectContextRecursiveXmlAdapter objectContextRecursiveXmlAdapter;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public MaestroMethodResponse execute(MaestroMethodRequest maestroMethodRequest) throws Exception {
		MaestroMethodResponse maestroMethodResponse = null;
		try {
			this.validateUser(maestroMethodRequest);
			ObjectContext objectContextRoot = null;
			if (maestroMethodRequest.getObjectContext()!=null) {
				objectContextRoot = objectContextRecursiveXmlAdapter.unmarshal(maestroMethodRequest.getObjectContext());
				objectContextRoot.getObjectContextTree().getTreeHash().put(objectContextRoot.getObjectTypeName(), objectContextRoot);
			}
			Method method = methodResource.prepare(objectContextRoot, maestroMethodRequest.getMethod());
			Object result = methodService.execute(method);
			maestroMethodResponse = methodResource.prepareResponse(maestroMethodRequest, objectContextRoot, result);
		} finally {
			this.managerAuthenticator.removeProfile();
		}
		return maestroMethodResponse;
	}

	private void validateUser(MaestroMethodRequest maestroMethodRequest) {
		String user = maestroMethodRequest.getUser();
		if (user == null)
			throw new MaestroException(USER_IS_NULL_IN_REQUEST);
		this.managerAuthenticator.authenticate(user);
	}

}
