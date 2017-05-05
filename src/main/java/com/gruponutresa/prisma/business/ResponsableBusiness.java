package com.gruponutresa.prisma.business;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.gruponutresa.prisma.dao.ResponsableDao;
import com.gruponutresa.prisma.exception.TechnicalException;
import com.gruponutresa.prisma.models.Responsable;

@Transactional
public class ResponsableBusiness {

	@Inject
	ResponsableDao responsable;

	public List<Responsable> obtenerResponsable() throws TechnicalException{
		List<Responsable> listResponsable = responsable.consultarResponsable();
		if (listResponsable == null) {
			throw new TechnicalException("No hay responsables");
		}
		return listResponsable;
	}

}
