package com.gruponutresa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.Provider;
import com.gruponutresa.prisma.constants.Valores;
import com.gruponutresa.prisma.dao.ResponsableDao;
import com.gruponutresa.prisma.models.Responsable;

@RunWith(MockitoJUnitRunner.class)
public class ResponsableDaoTest {

	@InjectMocks
	private ResponsableDao responsableDao;

	@Mock
	private Provider<EntityManager> emf;

	@Mock
	private EntityManager entity;

	@Mock
	private TypedQuery<Responsable> query;

	@Mock
	private List<Responsable> listResponsable;

	@Before
	public void setUp() {

		Mockito.when(emf.get()).thenReturn(entity);
		Mockito.when(entity.createNamedQuery(Valores.ENCONTRAR_RESPONSABLE, Responsable.class)).thenReturn(query);

	}

	@Test
	public void debeConsultarResponsable() {

		Mockito.when(query.getResultList()).thenReturn(listResponsable);

		List<Responsable> listResponsableObtenida = responsableDao.consultarResponsable();

		assertEquals(listResponsable, listResponsableObtenida);
		Mockito.verify(emf).get();
		Mockito.verify(entity).createNamedQuery(Valores.ENCONTRAR_RESPONSABLE, Responsable.class);
		Mockito.verify(query).getResultList();

	}

	@Test
	public void noDebeConsultarResponsable() {

		List<Responsable> listResponsableNula = null;

		Mockito.when(query.getResultList()).thenReturn(listResponsableNula);

		List<Responsable> listResponsableObtenida = responsableDao.consultarResponsable();

		assertNull(listResponsableObtenida);
		Mockito.verify(emf).get();
		Mockito.verify(entity).createNamedQuery(Valores.ENCONTRAR_RESPONSABLE, Responsable.class);
		Mockito.verify(query).getResultList();

	}

}
