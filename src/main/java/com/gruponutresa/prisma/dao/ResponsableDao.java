package com.gruponutresa.prisma.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.gruponutresa.prisma.constants.Valores;
import com.gruponutresa.prisma.models.Responsable;

@Transactional
public class ResponsableDao {

	@Inject
	private Provider<EntityManager> emf;

	private TypedQuery<Responsable> query;

	public List<Responsable> consultarResponsable() {

		query = emf.get().createNamedQuery(Valores.ENCONTRAR_RESPONSABLE, Responsable.class);
		return query.getResultList();

	}

}
